package medvedev.services;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.entities.Sneaker;
import medvedev.dao.get.GetSneakerDto;
import medvedev.dao.mappers.SneakerMapper;
import medvedev.dao.repository.SneakerRepository;
import medvedev.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final SneakerMapper sneakerMapper;
    private final SneakerCacheService sneakerCacheService;  // Внедряем кеш-сервис

    // Метод для получения всех кроссовок
    public List<GetSneakerDto> getAllSneakers() {
        List<Sneaker> sneakers = sneakerRepository.findAll();
        return sneakers.stream()
                .map(sneakerMapper::toDto) // Преобразуем сущности в DTO
                .collect(Collectors.toList());
    }

    // Метод для получения кроссовок по бренду с кешированием
    public List<GetSneakerDto> getSneakersByBrand(String brand) {
        // Проверяем, есть ли кроссовки в кеше
        Sneaker cachedSneaker = sneakerCacheService.getSneakerFromCache(brand);
        if (cachedSneaker != null) {
            // Если кроссовки есть в кеше, возвращаем их
            return List.of(sneakerMapper.toDto(cachedSneaker));
        }

        // Если кроссовки нет в кеше, ищем в базе данных
        List<Sneaker> sneakers = sneakerRepository.findSneakersByBrand(brand);

        if (sneakers.isEmpty()) {
            throw new ResourceNotFoundException("Sneakers not found for the given brand: "
                    + brand);
        }

        // Кешируем результаты поиска
        sneakers.forEach(sneaker -> sneakerCacheService.addSneakerToCache(sneaker.getBrand(),
                sneaker));

        // Возвращаем результат
        return sneakers.stream()
                .map(sneakerMapper::toDto)
                .collect(Collectors.toList());
    }

    // Метод для получения кроссовка по ID
    @Transactional
    public GetSneakerDto getSneakerById(long id) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sneaker not found with ID: "
                        + id));

        return sneakerMapper.toDto(sneaker);
    }

    // Метод для создания нового кроссовка
    @Transactional
    public GetSneakerDto createSneaker(CreateSneakerDto createSneakerDto) {
        Sneaker sneaker = sneakerMapper.toEntity(createSneakerDto);
        sneaker = sneakerRepository.save(sneaker);
        return sneakerMapper.toDto(sneaker);
    }

    // Метод для обновления данных кроссовка
    @Transactional
    public GetSneakerDto updateSneaker(Long id, CreateSneakerDto createSneakerDto) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sneaker not found with ID: "
                        + id));

        sneakerMapper.updateSneakerFromDto(createSneakerDto, sneaker);
        sneaker = sneakerRepository.save(sneaker);

        return sneakerMapper.toDto(sneaker);
    }

    // Метод для удаления кроссовка
    @Transactional
    public void deleteSneaker(Long id) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sneaker not found with ID: "
                        + id));

        sneakerRepository.delete(sneaker);
    }
}
