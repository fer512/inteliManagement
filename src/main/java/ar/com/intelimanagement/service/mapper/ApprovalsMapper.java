package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.dto.BookingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Approvals and its DTO ApprovalsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApprovalsMapper extends EntityMapper<ApprovalsDTO, Approvals> {


    @Mapping(source = "history", target = "history")
    ApprovalsDTO toDto(Approvals ap);
    
    default Approvals fromId(Long id) {
        if (id == null) {
            return null;
        }
        Approvals approvals = new Approvals();
        approvals.setId(id);
        return approvals;
    }
}
