package ru.nsu.ccfit.lopatkin.store.common.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Storage;

@Component
public class StorageMapper {

    public StorageDTO storageToStorageDTO(Storage storage) {
        return new StorageDTO(storage.getId(), storage.getAddress(), storage.getMaxSize());
    }

    public Storage storageDTOToStorage(StorageDTO storageDTO) {
        return new Storage(storageDTO.getId(), storageDTO.getAddress(), storageDTO.getMaxSize());
    }
}
