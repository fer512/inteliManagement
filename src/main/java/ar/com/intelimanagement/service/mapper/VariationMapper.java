package ar.com.intelimanagement.service.mapper;

import org.mapstruct.Mapping;

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.dto.VariationFullDTO;
import ar.com.intelimanagement.service.dto.VariationListDTO;
import ar.com.intelimanagement.service.dto.VariationMiddleDTO;

public interface VariationMapper extends EntityMapper<VariationDTO, Variation> {

    VariationDTO toDto(Variation variation);

    @Mapping(source = "creationUser", target = "creationUser")
    @Mapping(source = "product", target = "product")
    @Mapping(source = "approvals", target = "approvals")
    VariationFullDTO toFullDto(Variation variation);
    
    @Mapping(source = "creationUser", target = "creationUser")
    @Mapping(source = "product", target = "product")
    @Mapping(source = "approvals", target = "approvals")
    VariationListDTO toDtoPending(Variation variation);
    
    @Mapping(source = "creationUser", target = "creationUser")
    @Mapping(source = "approvals", target = "approvals")
    VariationMiddleDTO toMiddleDto(Variation variation);
    
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
