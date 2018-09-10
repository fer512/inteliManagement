package ar.com.intelimanagement.service.impl;

import ar.com.intelimanagement.service.NotificationService;
import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.Notification;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.repository.NotificationRepository;
import ar.com.intelimanagement.service.dto.NotificationDTO;
import ar.com.intelimanagement.service.mapper.NotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Notification.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    /**
     * Save a notification.
     *
     * @param notificationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NotificationDTO save(NotificationDTO notificationDTO) {
        log.debug("Request to save Notification : {}", notificationDTO);
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    /**
     * Get all the notifications.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Notifications");
        return notificationRepository.findAll(pageable)
            .map(notificationMapper::toDto);
    }


    /**
     * Get one notification by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationDTO> findOne(Long id) {
        log.debug("Request to get Notification : {}", id);
        return notificationRepository.findById(id)
            .map(notificationMapper::toDto);
    }

    /**
     * Delete the notification by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Notification : {}", id);
        notificationRepository.deleteById(id);
    }

	@Override
	@Transactional
	public void sendNotification(Approvals approvals) {
		List<Notification> userNotifications = new ArrayList<Notification>();
		switch (approvals.getStatus()) {
		case APPOVED:
		case REJECTED:
			userNotifications.add(createNotification(approvals.getCreationUser()));
			//send notificacion al creador y a los q participaron de la aprobacion
			break;
		case PENDING:
			//send notificacion nivel siguiente
			List<User> users = approvals.getUserByNextLevel();
			users.stream().map(u ->createNotification(u)).collect(Collectors.toList());
			break;
		default:
			break;
		}
		
		notificationRepository.saveAll(userNotifications);
	}
	
	private Notification createNotification(User u){
		Notification n = new Notification();
		n.setUser(u);
		return n;
	}
}
