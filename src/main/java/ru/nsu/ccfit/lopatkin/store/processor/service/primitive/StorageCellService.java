package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.StorageCellMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageCellDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCell;
import ru.nsu.ccfit.lopatkin.store.common.repository.StorageCellRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageCellService {

    private final StorageCellMapper storageCellMapper;

    private final StorageCellRepository storageCellRepository;


    public List<StorageCellDTO> getAllStorageCells() {
        return storageCellRepository.findAll().stream().map(storageCellMapper::storageCellToStorageCellDTO).toList();
    }

    public StorageCellDTO getStorageCellById(Long id) {
        Optional<StorageCell> storage = storageCellRepository.findById(id);
        if (storage.isPresent()) {
            return storageCellMapper.storageCellToStorageCellDTO(storage.get());
        }
        throw new LogicException("Не найдена ячейка склада с id: " + id);
    }

    public StorageCellDTO createCellStorage(StorageCellDTO storageCellDTO) {
        StorageCell newStorageCell = storageCellRepository.save(storageCellMapper.storageCellDTOToStorageCell(storageCellDTO));
        return storageCellMapper.storageCellToStorageCellDTO(newStorageCell);
    }

    public Long deleteStorage(Long id) { // переписать логику так, чтобы все товары вернуть
        storageCellRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }

}
