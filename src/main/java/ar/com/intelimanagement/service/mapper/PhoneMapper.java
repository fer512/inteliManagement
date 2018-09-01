package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.PhoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Phone and its DTO PhoneDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PhoneMapper extends EntityMapper<PhoneDTO, Phone> {

    @Mapping(source = "employee.id", target = "employeeId")
    PhoneDTO toDto(Phone phone);

    @Mapping(source = "employeeId", target = "employee")
    Phone toEntity(PhoneDTO phoneDTO);

    default Phone fromId(Long id) {
        if (id == null) {
            return null;
        }
        Phone phone = new Phone();
        phone.setId(id);
        return phone;
    }
}
