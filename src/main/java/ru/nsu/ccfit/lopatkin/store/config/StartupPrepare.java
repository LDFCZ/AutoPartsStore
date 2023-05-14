package ru.nsu.ccfit.lopatkin.store.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCell;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProductId;
import ru.nsu.ccfit.lopatkin.store.common.repository.ProductRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellsProductsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartupPrepare {

    private final ProductRepository productRepository;

    private final StorageCellRepository storageCellRepository;

    private final StorageCellsProductsRepository storageCellsProductsRepository;


    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void unpackProducts() {
        log.info("Starting products unpacking");
        List<Product> products = productRepository.findAllByOrderByProductSizeDesc();

        products.forEach(p -> {
            Optional<StorageCell> storageCellOptional = storageCellRepository.findFirstByFreeSpaceIsGreaterThanOrderByFreeSpaceDesc(0);
            if (storageCellOptional.isEmpty()) {
                log.error("No storage cells on application start");
                return;
            }
            StorageCell storageCell = storageCellOptional.get();
            if (isProductAlreadyUnpacked(p)) {
                log.info("Product {} is already unpacked", p.getProductName());
                return;
            }
            log.info("Unpacking {} with size {} in storage {}", p.getProductName(), p.getProductSize(), storageCell.getStorage().getAddress());
            StorageCellProduct storageCellProduct = unpackSingleProduct(p, storageCell);
            storageCellsProductsRepository.save(storageCellProduct);

            storageCell.setFreeSpace(storageCell.getFreeSpace() - p.getProductSize() * storageCellProduct.getCount());
            log.info("Unpacked {} with size {} count {} in cell with id {} free space left {}",
                    p.getProductName(), p.getProductSize(), storageCellProduct.getCount(), storageCell.getId(),
                    storageCell.getFreeSpace());
            storageCellRepository.save(storageCell);
        });
    }

    private StorageCellProduct unpackSingleProduct(Product product, StorageCell storageCell) {
        StorageCellProduct storageCellProduct = new StorageCellProduct();
        product.setArrivalDate(LocalDateTime.now());

        StorageCellProductId storageCellProductId = new StorageCellProductId(storageCell.getId(), product.getId());
        storageCellProduct.setId(storageCellProductId);
        storageCellProduct.setProduct(product);
        storageCellProduct.setStorageCell(storageCell);
        storageCellProduct.setCount(storageCell.getFreeSpace() / product.getProductSize());
        storageCell.getStorageCellProducts().add(storageCellProduct);
        return storageCellProduct;
    }

    private boolean isProductAlreadyUnpacked(Product product) {
        return storageCellsProductsRepository.findFirstByProductIs(product).isPresent();
    }
}
