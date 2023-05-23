package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ShopOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ShopOrder;

@Component
@RequiredArgsConstructor
public class ShopOrderMapper {

    private final ProductMapper productMapper;

    public ShopOrderDTO shopOrderToShopOrderDTO(ShopOrder shopOrder) {
        return new ShopOrderDTO(shopOrder.getId(),
                shopOrder.getOrderDate(),
                productMapper.productToProductDTO(shopOrder.getProduct()),
                shopOrder.getTotalCount(),
                shopOrder.getDoneCount(),
                shopOrder.getContractId()
        );
    }

    public ShopOrder shopOrderDTOToShopOrder(ShopOrderDTO shopOrderDTO) {
        return new ShopOrder(shopOrderDTO.getId(),
                shopOrderDTO.getOrderDate(),
                productMapper.productDTOToProduct(shopOrderDTO.getProduct()),
                shopOrderDTO.getTotalCount(),
                shopOrderDTO.getDoneCount(),
                shopOrderDTO.getContractId());
    }
}
