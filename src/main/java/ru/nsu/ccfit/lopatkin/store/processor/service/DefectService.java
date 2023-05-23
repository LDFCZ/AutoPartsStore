package ru.nsu.ccfit.lopatkin.store.processor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.DefectMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.DefectDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.DefectRegister;
import ru.nsu.ccfit.lopatkin.store.common.repository.DefectRegisterRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefectService {

    private final DefectMapper defectMapper;

    private final DefectRegisterRepository defectRegisterRepository;

    public Page<DefectDTO> getPageWithDefects(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return defectRegisterRepository.findAll(pageable).map(defectMapper::defectRegisterToDefectDTO);
    }

    public DefectDTO getDefectById(Long id) {
        Optional<DefectRegister> defectRegister = defectRegisterRepository.findById(id);
        if (defectRegister.isPresent()) {
            return defectMapper.defectRegisterToDefectDTO(defectRegister.get());
        }
        throw new LogicException("Не найдено обращение с id: " + id);
    }

    public DefectDTO createDefect(DefectDTO defectDTO) {
        if (defectRegisterRepository.findFirstByOrder_IdAndProduct_Id(defectDTO.getOrder().getId(), defectDTO.getProduct().getId()).isPresent()) {
            throw new LogicException("Обращение по этому продукту уже существует");
        }
        DefectRegister newDefect = defectRegisterRepository.save(defectMapper.defectDTOToDefectRegister(defectDTO));
        return defectMapper.defectRegisterToDefectDTO(newDefect);
    }
}
