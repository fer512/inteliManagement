package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Approvals and its DTO ApprovalsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApprovalsMapper extends EntityMapper<ApprovalsDTO, Approvals> {



    default Approvals fromId(Long id) {
        if (id == null) {
            return null;
        }
        Approvals approvals = new Approvals();
        approvals.setId(id);
        return approvals;
    }
}
