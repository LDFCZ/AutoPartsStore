package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.StorageMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Storage;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageMapper storageMapper;

    private final StorageRepository storageRepository;


    public List<StorageDTO> getAllStorages() {
        return storageRepository.findAll().stream().map(storageMapper::storageToStorageDTO).toList();
    }

    public StorageDTO getStorageById(Long id) {
        Optional<Storage> storage = storageRepository.findById(id);
        if (storage.isPresent()) {
            return storageMapper.storageToStorageDTO(storage.get());
        }
        throw new LogicException("Не найден склад с id: " + id);
    }

    public StorageDTO createStorage(StorageDTO storageDTO) {
        Storage newStorage = storageRepository.save(storageMapper.storageDTOToStorage(storageDTO));
        return storageMapper.storageToStorageDTO(newStorage);
    }

    public StorageDTO updateStorage(StorageDTO updatedStorageDTO) {
        Optional<Storage> oldStorageOptional = storageRepository.findById(updatedStorageDTO.getId());
        if (oldStorageOptional.isEmpty()) {
            throw new LogicException("Не найден склад с id: " + updatedStorageDTO.getId());
        }

        Storage oldStorage = oldStorageOptional.get();
        oldStorage.setAddress(updatedStorageDTO.getAddress());
        oldStorage.setMaxSize(updatedStorageDTO.getMaxSize());
        Storage updatedStorage = storageRepository.save(oldStorage);
        return storageMapper.storageToStorageDTO(updatedStorage);
    }

    public Long deleteStorage(Long id) {
        storageRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }

}
