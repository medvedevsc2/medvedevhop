package medvedev.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medvedev.dao.create.CreateClientDto;
import medvedev.dao.entities.Client;
import medvedev.dao.get.GetClientDto;
import medvedev.dao.mappers.ClientMapper;
import medvedev.dao.repository.ClientRepository;
import medvedev.services.ClientCache;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientCache clientCache;

    /**
     * Создание нового клиента.
     * После сохранения — кеш очищается.
     */
    public GetClientDto createClient(CreateClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        clientCache.clear(); // очищаем кеш, чтобы он не содержал устаревшие данные
        return clientMapper.toDto(saved);
    }

    /**
     * Получение клиента по ID.
     */
    public GetClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.toDto(client);
    }

    /**
     * Получение всех клиентов.
     */
    public List<GetClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    /**
     * Обновление данных клиента.
     * После сохранения — кеш очищается.
     */
    public GetClientDto updateClient(Long id, CreateClientDto dto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientMapper.updateClientFromDto(dto, existingClient);
        Client updated = clientRepository.save(existingClient);
        clientCache.clear(); // очищаем кеш
        return clientMapper.toDto(updated);
    }

    /**
     * Удаление клиента.
     * После удаления — кеш очищается.
     */
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
        clientCache.clear(); // очищаем кеш
    }

    /**
     * Получение клиентов по бренду кроссовок с кешированием.
     */
    public List<GetClientDto> getClientsBySneakerBrand(String brand) {
        // Проверка, есть ли в кеше
        log.info("Getting client by sneaker brand {}", brand);
        if (clientCache.contains(brand)) {
            log.info("Getting client by sneaker brand {}", brand);
            return clientCache.get(brand);
        }

        // Если нет — загрузка из БД и сохранение в кеш
        List<Client> clients = clientRepository.findClientsBySneakerBrand(brand);
        List<GetClientDto> result = clients.stream()
                .map(clientMapper::toDto)
                .toList();

        clientCache.put(brand, result);
        return result;
    }
}
