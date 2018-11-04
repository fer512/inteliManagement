package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ProviderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Provider and its DTO ProviderDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, CompanyMapper.class})
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {

    @Mapping(source = "company.id", target = "companyId")
    ProviderDTO toDto(Provider provider);

    @Mapping(source = "companyId", target = "company")
    Provider toEntity(ProviderDTO providerDTO);

    default Provider fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
