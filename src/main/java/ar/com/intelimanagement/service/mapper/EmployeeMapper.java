package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, UserMapper.class, CompanyMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "company.id", target = "companyId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "phones", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "companyId", target = "company")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
