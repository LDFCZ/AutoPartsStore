package ru.nsu.ccfit.lopatkin.store.processor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.OfferMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.OfferDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Buffer;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Offer;
import ru.nsu.ccfit.lopatkin.store.common.repository.BufferRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.OfferRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferMapper offerMapper;

    private final OfferRepository offerRepository;

    private final BufferRepository bufferRepository;

    public Page<OfferDTO> getPageWithOffers(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return offerRepository.findAll(pageable).map(offerMapper::offerToOfferDTO);
    }

    public OfferDTO getOfferById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            return offerMapper.offerToOfferDTO(offer.get());
        }
        throw new LogicException("Не найден офер с id: " + id);
    }

    public void completeOffer(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            Optional<Buffer> bufferOptional = bufferRepository.findFirstByProduct_IdIs(offer.get().getProduct().getId());
            Buffer buffer = null;
            if (bufferOptional.isPresent()) {
                buffer = bufferOptional.get();
                buffer.setCount(buffer.getCount() + offer.get().getCount());
            } else {
                buffer = new Buffer();
                buffer.setProduct(offer.get().getProduct());
                buffer.setCount(offer.get().getCount());
            }
            bufferRepository.save(buffer);
        }
        throw new LogicException("Не найден офер с id: " + id);
    }

    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer newOffer = offerRepository.save(offerMapper.offerDTOToOffer(offerDTO));
        return offerMapper.offerToOfferDTO(newOffer);
    }
}
