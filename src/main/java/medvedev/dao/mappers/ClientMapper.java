package medvedev.dao.mappers;

import medvedev.dao.create.CreateClientDto;
import medvedev.dao.entities.Client;
import medvedev.dao.get.GetClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = BaseMapper.class)
public interface ClientMapper extends BaseMapper<Client, GetClientDto> {

    @Mapping(target = "clientId", ignore = true)
    Client toEntity(CreateClientDto dto);

    @Override
    GetClientDto toDto(Client client);

    @Mapping(target = "clientId", ignore = true)
    void updateClientFromDto(CreateClientDto dto, @MappingTarget Client client);
}
