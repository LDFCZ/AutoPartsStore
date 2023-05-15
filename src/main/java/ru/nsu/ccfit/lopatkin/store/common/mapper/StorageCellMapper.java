package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageCellDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageCellProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCell;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProductId;

@Component
@RequiredArgsConstructor
public class StorageCellMapper {

    private final ProductMapper productMapper;

    private final StorageMapper storageMapper;

    StorageCellDTO storageCellToStorageCellDTO(StorageCell storageCell) {
        StorageCellDTO storageCellDTO = new StorageCellDTO(storageCell.getId(), storageCell.getSize(), storageCell.getFreeSpace());
        storageCellDTO.setStorage(storageMapper.storageToStorageDTO(storageCell.getStorage()));
        storageCell.getStorageCellProducts().forEach(storageCellProduct -> {
            storageCellDTO.getStorageCellProducts().add(
                    new StorageCellProductDTO(productMapper.productToProductDTO(storageCellProduct.getProduct()), storageCellProduct.getCount()));
        });
        return storageCellDTO;
    }

    StorageCell storageCellDTOToStorageCell(StorageCellDTO storageCellDTO) {
        StorageCell storageCell = new StorageCell();
        storageCell.setId(storageCellDTO.getId());
        storageCell.setSize(storageCellDTO.getSize());
        storageCell.setFreeSpace(storageCellDTO.getFreeSpace());
        storageCell.setStorage(storageMapper.storageDTOToStorage(storageCellDTO.getStorage()));
        storageCellDTO.getStorageCellProducts().forEach(storageCellProductDTO -> {
            storageCell.getStorageCellProducts().add(new StorageCellProduct(
                    new StorageCellProductId(storageCell.getId(), storageCellProductDTO.getProduct().getId()),
                    storageCell,
                    productMapper.ProductDTOToProduct(storageCellProductDTO.getProduct()),
                    storageCellProductDTO.getCount()
            ));
        });
        return storageCell;
    }
}
