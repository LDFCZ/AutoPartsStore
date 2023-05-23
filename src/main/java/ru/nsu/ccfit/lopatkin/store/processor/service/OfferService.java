package ru.nsu.ccfit.lopatkin.store.processor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.OfferMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.OfferDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Offer;
import ru.nsu.ccfit.lopatkin.store.common.repository.OfferRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferMapper offerMapper;

    private final OfferRepository offerRepository;

    public Page<OfferDTO> getPageWithOffers(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return offerRepository.findAll(pageable).map(offerMapper::offerToOfferDTO);
    }

    // TODO эта штука должна добавлять в буфер + нужно сделать автоофер на выполнение заказа
    public OfferDTO getOfferById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            return offerMapper.offerToOfferDTO(offer.get());
        }
        throw new LogicException("Не найден офер с id: " + id);
    }

    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer newOffer = offerRepository.save(offerMapper.offerDTOToOffer(offerDTO));
        return offerMapper.offerToOfferDTO(newOffer);
    }
}
