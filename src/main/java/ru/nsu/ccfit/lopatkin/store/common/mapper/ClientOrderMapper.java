package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrder;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProductId;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientOrderMapper {

    private final ClientMapper clientMapper;

    private final ProductMapper productMapper;

    public ClientOrderDTO clientOrderToClientOrderDTO(ClientOrder clientOrder) {
        ClientOrderDTO clientOrderDTO = new ClientOrderDTO(clientOrder.getId(),
                clientOrder.getOrderDate(),
                clientOrder.getOrderEndDate(),
                clientMapper.clintToClientDTO(clientOrder.getClient()),
                clientOrder.getIsCompleted()
        );

        clientOrderDTO.setProducts(clientOrder.getProducts().stream()
                .collect(Collectors.toMap(
                        clientOrderProduct -> productMapper.productToProductDTO(clientOrderProduct.getProduct()),
                        ClientOrderProduct::getCount
                )));
        return clientOrderDTO;
    }

    public ClientOrder clientOrderDTOToClientOrder(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setId(clientOrderDTO.getId());
        clientOrder.setOrderDate(clientOrderDTO.getOrderDate());
        clientOrder.setOrderEndDate(clientOrderDTO.getOrderEndDate());
        clientOrder.setClient(clientMapper.ClientDTOtoClient(clientOrderDTO.getClient()));
        clientOrder.setIsCompleted(clientOrderDTO.isCompleted());

        // (ノ-_-)ノ ミ ┴┴
        clientOrder.setProducts(clientOrderDTO.getProducts()
                .keySet().stream()
                .map(productDTO -> new ClientOrderProduct(
                        new ClientOrderProductId(clientOrderDTO.getId(), productDTO.getId()),
                        clientOrder,
                        productMapper.productDTOToProduct(productDTO),
                        clientOrderDTO.getProducts().get(productDTO))
                )
                .collect(Collectors.toList())
        );
        return clientOrder;
    }
}
