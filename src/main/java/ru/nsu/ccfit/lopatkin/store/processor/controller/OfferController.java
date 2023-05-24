package ru.nsu.ccfit.lopatkin.store.processor.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.OfferDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.OfferService;

/**
 * Контроллер для работы с рынком деталей (запчастей) между магазином и поставщиками
 */
@Slf4j
@RestController
@RequestMapping("/processing/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/all-offers-page")
    public Page<OfferDTO> getOffers(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                    @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return offerService.getPageWithOffers(offset, limit);
    }

    @GetMapping("/{id}")
    public OfferDTO getOffer(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @GetMapping("/complete/{id}")
    public void completeOffer(@PathVariable Long id) {
        offerService.completeOffer(id);
    }

    @PostMapping("/new")
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }
}
