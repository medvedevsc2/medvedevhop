package medvedev.dao.mappers;

import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.entities.Sneaker;
import medvedev.dao.get.GetSneakerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = BaseMapper.class)
public interface SneakerMapper extends BaseMapper<Sneaker, GetSneakerDto> {

    @Mapping(target = "id", ignore = true)
    Sneaker toEntity(CreateSneakerDto dto);

    @Override
    GetSneakerDto toDto(Sneaker sneaker);

    @Mapping(target = "id", ignore = true)
    void updateSneakerFromDto(CreateSneakerDto dto, @MappingTarget Sneaker sneaker);
}
