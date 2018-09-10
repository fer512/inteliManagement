package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.service.dto.VariationDTO;

public interface VariationMapper extends EntityMapper<VariationDTO, Variation> {

    VariationDTO toDto(Variation variation);

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
