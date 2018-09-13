package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.NotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Notification and its DTO NotificationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {
	
	@Mapping(source = "userCreation", target = "userCreation")
    NotificationDTO toDto(Notification notification);

    Notification toEntity(NotificationDTO notificationDTO);

    default Notification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setId(id);
        return notification;
    }
}
