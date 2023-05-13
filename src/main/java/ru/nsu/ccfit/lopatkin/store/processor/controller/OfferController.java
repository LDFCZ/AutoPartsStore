package ru.nsu.ccfit.lopatkin.store.processor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.OfferDTO;

/**
 * Контроллер для работы с рынком деталей (запчастей) между магазином и поставщиками
 */
@Slf4j
@RestController
@RequestMapping("/processing/offers")
public class OfferController {

    @GetMapping("/all-offers")
    public ResponseEntity<?> getOffers() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOffer(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createOffer(@RequestBody OfferDTO offerDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOffer(@RequestBody OfferDTO offerDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable String id) {
        return null;
    }
}
