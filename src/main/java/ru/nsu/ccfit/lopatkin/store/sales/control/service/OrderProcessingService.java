package ru.nsu.ccfit.lopatkin.store.sales.control.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ClientOrderMapper;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ProductMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.CashRegister;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ShopOrder;
import ru.nsu.ccfit.lopatkin.store.common.repository.CashRegisterRepository;
import ru.nsu.ccfit.lopatkin.store.common.repository.ShopOrderRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingService {

    private final ShopOrderRepository shopOrderRepository;

    private final CashRegisterRepository cashRegisterRepository;

    private final ClientOrderMapper clientOrderMapper;

    private final ProductMapper productMapper;

    @Transactional
    public void handleUncompletedOrder(ClientOrderDTO clientOrderDTO) {
        log.info("Обрабатываем незаконченный заказ с id={}", clientOrderDTO.getId());
        for (ProductDTO productDTO : clientOrderDTO.getProducts().keySet()) {
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setOrderDate(LocalDateTime.now());
            shopOrder.setProduct(productMapper.productDTOToProduct(productDTO));
            shopOrder.setDoneCount(0);
            shopOrder.setTotalCount(clientOrderDTO.getProducts().get(productDTO));
            shopOrderRepository.save(shopOrder);
        }
    }

    @Transactional
    public void handleCompletedOrder(ClientOrderDTO clientOrderDTO) {
        log.info("Обрабатываем законченный заказ с id={}", clientOrderDTO.getId());
        CashRegister cashRegister = new CashRegister();
        double sum = 0;
        for (ProductDTO productDTO : clientOrderDTO.getProducts().keySet()) {
            sum += productDTO.getFinalPrice() * ((100 - productDTO.getFinalDiscount()) / 100.0) * clientOrderDTO.getProducts().get(productDTO);
        }
        cashRegister.setOrder(clientOrderMapper.clientOrderDTOToClientOrder(clientOrderDTO));
        cashRegister.setTransactionDate(LocalDateTime.now());
        cashRegister.setSum(sum);
        cashRegisterRepository.save(cashRegister);
    }
}
