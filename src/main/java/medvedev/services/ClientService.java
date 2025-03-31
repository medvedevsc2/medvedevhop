package medvedev.services;

import medvedev.dao.create.CreateClientDto;
import medvedev.dao.entities.Client;
import medvedev.dao.get.GetClientDto;
import medvedev.dao.mappers.ClientMapper;
import medvedev.dao.repository.ClientRepository;
import medvedev.errors.ErrorMessages;
import medvedev.errors.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import java.util.List;
import java.util.Optional;  // Import Optional
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public GetClientDto getClientById(Long id) {
        Optional<Client> clientOptional =clientRepository.findById(id); // Use Optional
        if (clientOptional.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.USER_NOT_FOUND, id)
            );
        }
        Client client = clientOptional.get(); // Get the Client from Optional

        return clientMapper.toDto(client);
    }

    @Transactional
    public GetClientDto createClient(CreateClientDto createClientDto) {
        if (clientRepository.existsByEmail(createClientDto.getEmail())) {
            throw new ValidationException(
                    String.format(ErrorMessages.EMAIL_EXISTS, createClientDto.getEmail())
            );
        }

        Client client = clientMapper.toEntity(createClientDto);
        client = clientRepository.save(client);

        return clientMapper.toDto(client);
    }

    @Transactional
    public GetClientDto updateClient(Long id, CreateClientDto createClientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id); // Use Optional
        if (clientOptional.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.USER_NOT_FOUND, id)
            );
        }
        Client client = clientOptional.get(); // Get the Client from Optional

        boolean emailExists = clientRepository.existsByEmail(createClientDto.getEmail());
        boolean isDifferentEmail = !client.getEmail().equals(createClientDto.getEmail());

        if (emailExists && isDifferentEmail) {
            throw new ValidationException(
                    String.format(ErrorMessages.EMAIL_EXISTS, createClientDto.getEmail())
            );
        }

        clientMapper.updateClientFromDto(createClientDto, client);
        client = clientRepository.save(client);

        return clientMapper.toDto(client);
    }

    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.USER_NOT_FOUND, id)
            );
        }
        clientRepository.deleteById(id);
    }

    public List<GetClientDto> getAllClients() {
        return clientMapper.toDtos(clientRepository.findAll());
    }
}
