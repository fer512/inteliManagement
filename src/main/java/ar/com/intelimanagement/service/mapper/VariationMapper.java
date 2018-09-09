package ar.com.intelimanagement.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.service.dto.VariationDTO;

/**
 * Mapper for the entity Variation and its DTO VariationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class,ProductMapper.class})
public interface VariationMapper extends EntityMapper<VariationDTO, Variation> {

    @Mapping(source = "creationUser.id", target = "creationUser")
    @Mapping(source = "product.id", target = "product")
    VariationDTO toDto(Variation variation);

    @Mapping(source = "creationUser", target = "creationUser")
    @Mapping(source = "product", target = "product")
    Variation toEntity(VariationDTO variationDTO);

    default Variation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Variation variation = new Variation();
        variation.setId(id);
        return variation;
    }
}
