package ru.nsu.ccfit.lopatkin.store.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ClientOrderMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Buffer;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrder;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProductId;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCell;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProductId;
import ru.nsu.ccfit.lopatkin.store.common.repository.BufferRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.ClientOrderProductRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.ClientOrderRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellsProductsRepository;
import ru.nsu.ccfit.lopatkin.store.processor.service.messaging.OrderMessagingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsDecomposeService {

    private final BufferRepository bufferRepository;

    private final StorageCellsProductsRepository storageCellsProductsRepository;

    private final StorageCellRepository storageCellRepository;

    private final ClientOrderRepository clientOrderRepository;

    private final ClientOrderProductRepository clientOrderProductRepository;

    private final ClientOrderMapper clientOrderMapper;

    private final OrderMessagingService orderMessagingService;

    @Scheduled(fixedDelay = 20000)
    @Transactional
    public void decomposeBuffer() {
        try {
            log.info("Начинаем раскладывать товар.");
            List<Buffer> buffer = bufferRepository.findAll();
            for (Buffer buffer_part : buffer) {
                Optional<StorageCell> storageCellOptional = storageCellRepository
                        .findFirstByFreeSpaceIsGreaterThanEqualOrderByFreeSpaceDesc(
                                buffer_part.getCount() * buffer_part.getProduct().getProductSize()
                        );
                if (storageCellOptional.isPresent()) {
                    addProductToStorageCell(buffer_part, storageCellOptional.get());
                    continue;
                }
                decomposeToStorageCells(buffer_part);
            }
            if (!buffer.isEmpty()) {
                checkUncompletedOrders();
            }
        } catch (Exception e) {
            log.error("Во время распределения товара произошли проблемы", e);
        }
    }

    private void checkUncompletedOrders() {
        log.info("Проверяем незавершенные заказы");
        List<ClientOrder> uncompletedClientOrders = clientOrderRepository.findAllByIsCompletedIsFalse();
        for (ClientOrder order : uncompletedClientOrders) {
            ClientOrder clientOrder = new ClientOrder(null, order.getOrderDate(), LocalDateTime.now(), order.getClient(), true, new ArrayList<>());
            for (ClientOrderProduct clientOrderProduct : order.getProducts()) {
                if (clientOrderProduct.getCount() > storageCellsProductsRepository.getAmountOfProductById(clientOrderProduct.getId().getProductId())) {
                    continue;
                }
                clientOrderProductRepository.delete(clientOrderProduct);
                ClientOrderProductId clientOrderProductId = new ClientOrderProductId();
                clientOrderProductId.setProductId(clientOrderProduct.getId().getProductId());
                clientOrder.getProducts().add(new ClientOrderProduct(
                        clientOrderProductId,
                        clientOrder,
                        clientOrderProduct.getProduct(),
                        clientOrderProduct.getCount()
                ));

                List<StorageCellProduct> storageCellProducts = storageCellsProductsRepository
                        .findAllByProduct_IdIsOrderByCountAsc(clientOrderProduct.getProduct().getId());
                int readyProductsCount = 0;
                for (StorageCellProduct storageCellProduct : storageCellProducts) {
                    if (storageCellProduct.getCount() >= clientOrderProduct.getCount()) {
                        storageCellProduct.setCount(storageCellProduct.getCount() - clientOrderProduct.getCount());
                        if (storageCellProduct.getCount() == 0) {
                            storageCellsProductsRepository.delete(storageCellProduct);
                        }
                        break;
                    }
                    if (readyProductsCount + storageCellProduct.getCount() > clientOrderProduct.getCount()) {
                        storageCellProduct.setCount(storageCellProduct.getCount() - (clientOrderProduct.getCount() - readyProductsCount));
                        storageCellsProductsRepository.save(storageCellProduct);
                        break;
                    }
                    storageCellsProductsRepository.delete(storageCellProduct);
                    readyProductsCount += storageCellProduct.getCount();
                }
            }
            if (!clientOrder.getProducts().isEmpty()) {
                ClientOrder readyOrder = clientOrderRepository.save(clientOrder);
                orderMessagingService.sendOrderForProcessing(clientOrderMapper.clientOrderToClientOrderDTO(readyOrder));
                Optional<ClientOrder> tmp = clientOrderRepository.findById(order.getId());
                if (tmp.isPresent() && tmp.get().getProducts().isEmpty()) {
                    clientOrderRepository.delete(tmp.get());
                }
            }
        }
    }

    private void decomposeToStorageCells(Buffer buffer_part) {
        List<StorageCell> storageCells = storageCellRepository.findAllByOrderByFreeSpaceDesc();
        for (StorageCell storageCell : storageCells) {
            int maxCountForCell = storageCell.getFreeSpace() / buffer_part.getProduct().getProductSize();
            if (maxCountForCell > 0) {
                StorageCellProduct storageCellProduct = new StorageCellProduct(
                        new StorageCellProductId(storageCell.getId(), buffer_part.getProduct().getId()),
                        storageCell,
                        buffer_part.getProduct(),
                        maxCountForCell > buffer_part.getCount() ? buffer_part.getCount() : maxCountForCell
                );
                storageCell.setFreeSpace(storageCell.getFreeSpace() - maxCountForCell * buffer_part.getProduct().getProductSize());
                buffer_part.setCount(buffer_part.getCount() - maxCountForCell);
                storageCell.getStorageCellProducts().add(storageCellProduct);
                storageCellRepository.save(storageCell);
                storageCellsProductsRepository.save(storageCellProduct);
                if (buffer_part.getCount() <= 0) {
                    bufferRepository.delete(buffer_part);
                    return;
                }
            }
        }
    }

    private void addProductToStorageCell(Buffer buffer_part, StorageCell storageCell) {
        StorageCellProduct storageCellProduct = new StorageCellProduct(
                new StorageCellProductId(storageCell.getId(), buffer_part.getProduct().getId()),
                storageCell,
                buffer_part.getProduct(),
                buffer_part.getCount()
        );
        storageCell.getStorageCellProducts().add(storageCellProduct);
        storageCell.setFreeSpace(storageCell.getFreeSpace() - buffer_part.getCount() * buffer_part.getProduct().getProductSize());
        storageCellRepository.save(storageCell);
        storageCellsProductsRepository.save(storageCellProduct);
        bufferRepository.delete(buffer_part);
    }
}
