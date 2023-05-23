package ru.nsu.ccfit.lopatkin.store.processor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ClientOrderMapper;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ShopOrderMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ShopOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrder;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ShopOrder;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.repository.ClientOrderRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.ShopOrderRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellsProductsRepository;
import ru.nsu.ccfit.lopatkin.store.processor.service.messaging.OrderMessagingService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ClientOrderMapper clientOrderMapper;

    private final ShopOrderMapper shopOrderMapper;

    private final ClientOrderRepository clientOrderRepository;

    private final ShopOrderRepository shopOrderRepository;

    private final StorageCellsProductsRepository storageCellsProductsRepository;

    private final OrderMessagingService messagingService;


    public Page<ClientOrderDTO> getClientOrdersPage(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return clientOrderRepository.findAll(pageable).map(clientOrderMapper::clientOrderToClientOrderDTO);
    }

    public Page<ShopOrderDTO> getShopOrdersPage(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return shopOrderRepository.findAll(pageable).map(shopOrderMapper::shopOrderToShopOrderDTO);
    }

    public ClientOrderDTO getClientOrderById(Long id) {
        Optional<ClientOrder> clientOrder = clientOrderRepository.findById(id);
        if (clientOrder.isPresent()) {
            return clientOrderMapper.clientOrderToClientOrderDTO(clientOrder.get());
        }
        throw new LogicException("Не найден клиентский заказ с id: " + id);
    }

    public ShopOrderDTO getShopOrderById(Long id) {
        Optional<ShopOrder> shopOrder = shopOrderRepository.findById(id);
        if (shopOrder.isPresent()) {
            return shopOrderMapper.shopOrderToShopOrderDTO(shopOrder.get());
        }
        throw new LogicException("Не найден заказ магазина с id: " + id);
    }

    @Transactional
    public ClientOrderDTO createClientOrder(ClientOrderDTO clientOrderDTO) {
        Map<ProductDTO, Integer> readyProducts = new HashMap<>();
        for (ProductDTO productDTO : clientOrderDTO.getProducts().keySet()) {
            if (isProductReady(productDTO, clientOrderDTO.getProducts().get(productDTO))) {
                readyProducts.put(productDTO, clientOrderDTO.getProducts().get(productDTO));
                clientOrderDTO.getProducts().remove(productDTO);
            }
        }
        ClientOrderDTO completedOrderDTO = new ClientOrderDTO(clientOrderDTO.getOrderDate(),
                LocalDateTime.now(),
                clientOrderDTO.getClient(),
                true,
                readyProducts
        );
        ClientOrder completedClientOrder = clientOrderRepository.save(clientOrderMapper.clientOrderDTOToClientOrder(completedOrderDTO));
        collectOrder(completedClientOrder);
        ClientOrder uncompletedClientOrder = clientOrderRepository.save(clientOrderMapper.clientOrderDTOToClientOrder(clientOrderDTO));
        messagingService.sendOrderForProcessing(clientOrderMapper.clientOrderToClientOrderDTO(uncompletedClientOrder));
        messagingService.sendOrderForProcessing(clientOrderMapper.clientOrderToClientOrderDTO(completedClientOrder));
        return clientOrderMapper.clientOrderToClientOrderDTO(completedClientOrder);
    }

    public ShopOrderDTO createShopOrder(ShopOrderDTO shopOrderDTO) {
        ShopOrder shopOrder = shopOrderRepository.save(shopOrderMapper.shopOrderDTOToShopOrder(shopOrderDTO));
        return shopOrderMapper.shopOrderToShopOrderDTO(shopOrder);
    }

    private boolean isProductReady(ProductDTO productDTO, Integer count) {
        List<StorageCellProduct> storageCellProducts = storageCellsProductsRepository.findAllByProduct_IdIsOrderByCountAsc(productDTO.getId());

        int totalPartCount = 0;
        for (StorageCellProduct storageCellProduct : storageCellProducts) {
            totalPartCount += storageCellProduct.getCount();
        }
        return totalPartCount >= count;
    }

    private void collectOrder(ClientOrder clientOrder) {
        for (ClientOrderProduct clientOrderProduct : clientOrder.getProducts()) {
            List<StorageCellProduct> storageCellProducts = storageCellsProductsRepository.findAllByProduct_IdIsOrderByCountAsc(clientOrderProduct.getProduct().getId());
            int totalCount = clientOrderProduct.getCount();
            for (StorageCellProduct storageCellProduct : storageCellProducts) {
                if (storageCellProduct.getCount() <= totalCount) {
                    totalCount -= storageCellProduct.getCount();
                    storageCellsProductsRepository.delete(storageCellProduct);
                    continue;
                }
                storageCellProduct.setCount(storageCellProduct.getCount() - totalCount);
                storageCellsProductsRepository.save(storageCellProduct);
            }
        }
    }
}
