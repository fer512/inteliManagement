package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ProvinceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Province and its DTO ProvinceDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {

    @Mapping(source = "country.id", target = "countryId")
    ProvinceDTO toDto(Province province);

    @Mapping(source = "countryId", target = "country")
    Province toEntity(ProvinceDTO provinceDTO);

    default Province fromId(Long id) {
        if (id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
        return province;
    }
}
