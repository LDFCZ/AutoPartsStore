package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ClientMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Client;
import ru.nsu.ccfit.lopatkin.store.common.repository.ClientRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper;

    private final ClientRepository clientRepository;

    public Page<ClientDTO> getPageWithClients(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return clientRepository.findAll(pageable).map(clientMapper::clintToClientDTO);
    }

    public ClientDTO getClintById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return clientMapper.clintToClientDTO(client.get());
        }
        throw new LogicException("Не найден клиент с id: " + id);
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        if (clientRepository.findClientByEmail(clientDTO.getEmail()).isPresent()) {
            throw new LogicException("Клиент с email=" + clientDTO.getEmail() + " уже существует");
        }
        Client newClient = clientRepository.save(clientMapper.ClientDTOtoClient(clientDTO));
        return clientMapper.clintToClientDTO(newClient);
    }

    public ClientDTO updateClient(ClientDTO updatedClientDTO) {
        Optional<Client> oldClientOptional = clientRepository.findById(updatedClientDTO.getId());
        if (oldClientOptional.isEmpty()) {
            throw new LogicException("Не найден клиент с id: " + updatedClientDTO.getId());
        }
        if (clientRepository.findClientByEmailAndIdNot(updatedClientDTO.getEmail(), updatedClientDTO.getId()).isPresent()) {
            throw new LogicException("Клиент с email=" + updatedClientDTO.getEmail() + " уже существует");
        }
        Client oldClient = oldClientOptional.get();
        oldClient.setName(updatedClientDTO.getName());
        oldClient.setEmail(updatedClientDTO.getEmail());
        Client updatedClient = clientRepository.save(oldClient);
        return clientMapper.clintToClientDTO(updatedClient);
    }

    public Long deleteClient(Long id) {
        clientRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }
}
