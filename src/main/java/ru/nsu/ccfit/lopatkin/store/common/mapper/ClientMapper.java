package ru.nsu.ccfit.lopatkin.store.common.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Client;

@Component
public class ClientMapper {

    public Client ClientDTOtoClient(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail());
    }

    public ClientDTO clintToClientDTO(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getEmail());
    }
}
