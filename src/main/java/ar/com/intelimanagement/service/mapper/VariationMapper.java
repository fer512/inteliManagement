package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.VariationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Variation and its DTO VariationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface VariationMapper extends EntityMapper<VariationDTO, Variation> {

    @Mapping(source = "relationship_user_variation.id", target = "relationship_user_variationId")
    @Mapping(source = "relationship_user_variation.login", target = "relationship_user_variationLogin")
    VariationDTO toDto(Variation variation);

    @Mapping(source = "relationship_user_variationId", target = "relationship_user_variation")
    @Mapping(target = "relationship_provider_variations", ignore = true)
    @Mapping(target = "relationship_product_variations", ignore = true)
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
