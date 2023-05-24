package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.SupplierMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Supplier;
import ru.nsu.ccfit.lopatkin.store.common.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;

    private final SupplierRepository supplierRepository;

    public Page<SupplierDTO> getPageWithSuppliers(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return supplierRepository.findAll(pageable).map(supplierMapper::supplierToSupplierDTO);
    }

    public List<SupplierDTO> getSuppliers() {
        return supplierRepository.findAll().stream().map(supplierMapper::supplierToSupplierDTO).toList();
    }

    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return supplierMapper.supplierToSupplierDTO(supplier.get());
        }
        throw new LogicException("Не найден поставщик с id: " + id);
    }

    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.findFirstByAddressAndName(supplierDTO.getAddress(), supplierDTO.getName()).isPresent()) {
            throw new LogicException("Поставщик с названием=" + supplierDTO.getName() + " уже существует");
        }
        Supplier newSupplier = supplierRepository.save(supplierMapper.supplierDTOToSupplier(supplierDTO));
        return supplierMapper.supplierToSupplierDTO(newSupplier);
    }

    public SupplierDTO updateSupplier(SupplierDTO updatedSupplierDTO) {
        Optional<Supplier> oldSupplierOptional = supplierRepository.findById(updatedSupplierDTO.getId());
        if (oldSupplierOptional.isEmpty()) {
            throw new LogicException("Не найден поставщик с id: " + updatedSupplierDTO.getId());
        }
        if (supplierRepository
                .findFirstByAddressAndNameAndIdNot(updatedSupplierDTO.getAddress(), updatedSupplierDTO.getName(), updatedSupplierDTO.getId())
                .isPresent()) {
            throw new LogicException("Поставщик с email=" + updatedSupplierDTO.getName() + " уже существует");
        }

        Supplier oldSupplier = oldSupplierOptional.get();
        oldSupplier.setName(updatedSupplierDTO.getName());
        oldSupplier.setAddress(updatedSupplierDTO.getAddress());
        Supplier updatedSupplier = supplierRepository.save(oldSupplier);
        return supplierMapper.supplierToSupplierDTO(updatedSupplier);
    }

    public Long deleteSupplier(Long id) {
        supplierRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }
}
