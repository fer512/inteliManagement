package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Company and its DTO CompanyDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "address.id", target = "addressId")
    CompanyDTO toDto(Company company);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "providers", ignore = true)
    Company toEntity(CompanyDTO companyDTO);

    default Company fromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
