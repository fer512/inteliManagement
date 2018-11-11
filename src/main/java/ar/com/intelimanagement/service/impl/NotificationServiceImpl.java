package ar.com.intelimanagement.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.Notification;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.domain.enumeration.NotificationType;
import ar.com.intelimanagement.repository.NotificationRepository;
import ar.com.intelimanagement.service.NotificationService;
import ar.com.intelimanagement.service.VariationService;
import ar.com.intelimanagement.service.dto.NotificationDTO;
import ar.com.intelimanagement.service.mapper.NotificationMapper;
import ar.com.intelimanagement.service.util.MapperUtil;
/**
 * Service Implementation for managing Notification.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final VariationService variationService;
    
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper,VariationService variationService) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.variationService = variationService;
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
	public void sendNotification(Variation variation) {
		List<Notification> userNotifications = new ArrayList<>();
		switch (variation.getApprovals().getStatus()) {
		case CREATE:
			userNotifications =  this.createNotificarionWhenCreate(variation);
			break;
		case APPROVED:
		case REJECTED:
			userNotifications.add(createNotification(variation.getApprovals().getCreationUser(),variation));
			//send notificacion al creador y a los q participaron de la aprobacion
			break;
		case PENDING:
			//send notificacion nivel siguiente
			List<User> users2 = variation.getApprovals().getUserByNextLevel();
			users2.stream().map(u ->createNotification(u,variation)).collect(Collectors.toList());
			break;
		default:
			break;
		}
		
		notificationRepository.saveAll(userNotifications);
	}
	
	private List<Notification> createNotificarionWhenCreate(Variation variation) {
		List<Notification> listNotification = new ArrayList<>();
		List<User> users1 = variation.getApprovals().getUserByNextLevel();
		if(users1 != null && users1.size() > 0) {
			listNotification = users1.stream().map(u ->createNotification(u,variation)).collect(Collectors.toList());
		}else{
			listNotification.add(this.createNotification(variation.getCreationUser(),variation));
		}
		return listNotification;
	}

	private Notification createNotification(User u, Variation variation) {
		Notification n = new Notification();
		n.setUser(u);
		n.setCreationDate(Instant.now());
		n.setEndDate(null);
		n.setIdReference(variation.getId());
		n.setStastDate(Instant.now());
		n.setType(n.getTypeByAppovalsStatus(variation.getApprovals().getStatus()));
		n.setUser(u);
		n.setUserCreation(variation.getCreationUser());
		n.setView(false);
		return n;
	}



	public VariationService getVariationService() {
		return variationService;
	}
}
