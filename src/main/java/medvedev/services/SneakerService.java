package medvedev.services;

import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.entities.Sneaker;
import medvedev.dao.get.GetSneakerDto;
import medvedev.dao.mappers.SneakerMapper;
import medvedev.dao.repository.SneakerRepository;
import medvedev.errors.CannotDeleteSneakerException;
import medvedev.errors.ErrorMessages;
import medvedev.errors.ResourceNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final SneakerMapper sneakerMapper;

    public GetSneakerDto getSneakerById(long id) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.SNEAKER_NOT_FOUND, id)
                ));
        return sneakerMapper.toDto(sneaker);
    }



    public List<GetSneakerDto> getAllSneakers() {
        return sneakerMapper.toDtos(sneakerRepository.findAll());
    }

    @Transactional
    public GetSneakerDto createSneaker(CreateSneakerDto createSneakerDto) {
        Sneaker sneaker = sneakerMapper.toEntity(createSneakerDto);
        sneaker = sneakerRepository.save(sneaker);
        return sneakerMapper.toDto(sneaker);
    }

    @Transactional
    public GetSneakerDto updateSneaker(Long id, CreateSneakerDto createSneakerDto) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.SNEAKER_NOT_FOUND, id)
                ));

        sneakerMapper.updateSneakerFromDto(createSneakerDto, sneaker);
        sneaker = sneakerRepository.save(sneaker);

        return sneakerMapper.toDto(sneaker);
    }

    @Transactional
    public void deleteSneaker(Long id) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.SNEAKER_NOT_FOUND, id)
                ));

        try {
            sneakerRepository.delete(sneaker);
        } catch (PersistenceException e) {
            throw new CannotDeleteSneakerException(
                    String.format(ErrorMessages.SNEAKER_IN_USE, id)
            );
        }
    }
}