package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.OfferDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Offer;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    private final ProductMapper productMapper;

    private final SupplierMapper supplierMapper;

    public OfferDTO offerToOfferDTO(Offer offer) {
        return new OfferDTO(offer.getId(),
                productMapper.productToProductDTO(offer.getProduct()),
                supplierMapper.supplierToSupplierDTO(offer.getSupplier()),
                offer.getCount()
        );
    }

    public Offer offerDTOToOffer(OfferDTO offerDTO) {
        return new Offer(offerDTO.getId(),
                productMapper.productDTOToProduct(offerDTO.getProduct()),
                supplierMapper.supplierDTOToSupplier(offerDTO.getSupplier()),
                offerDTO.getCount()
        );
    }
}
