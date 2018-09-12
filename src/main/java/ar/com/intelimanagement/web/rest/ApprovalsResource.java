package ar.com.intelimanagement.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.service.ApprovalsQueryService;
import ar.com.intelimanagement.service.ApprovalsService;
import ar.com.intelimanagement.service.NotificationService;
import ar.com.intelimanagement.service.dto.ApprovalsCriteria;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.web.rest.errors.BadRequestAlertException;
import ar.com.intelimanagement.web.rest.util.HeaderUtil;
import ar.com.intelimanagement.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Approvals.
 */
@RestController
@RequestMapping("/api")
public class ApprovalsResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalsResource.class);

    private static final String ENTITY_NAME = "approvals";

    private final ApprovalsService approvalsService;

    private final NotificationService notificationService;
    
    private final ApprovalsQueryService approvalsQueryService;

    public ApprovalsResource(ApprovalsService approvalsService, ApprovalsQueryService approvalsQueryService,NotificationService notificationService) {
        this.approvalsService = approvalsService;
        this.approvalsQueryService = approvalsQueryService;
        this.notificationService = notificationService;
    }

    /**
     * POST  /approvals : Create a new approvals.
     *
     * @param approvalsDTO the approvalsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new approvalsDTO, or with status 400 (Bad Request) if the approvals has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/approvals")
    @Timed
    public ResponseEntity<ApprovalsDTO> createApprovals(@RequestBody ApprovalsDTO approvalsDTO) throws URISyntaxException {
        log.debug("REST request to save Approvals : {}", approvalsDTO);
        if (approvalsDTO.getId() != null) {
            throw new BadRequestAlertException("A new approvals cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApprovalsDTO result = approvalsService.save(approvalsDTO);
        return ResponseEntity.created(new URI("/api/approvals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /approvals : Updates an existing approvals.
     *
     * @param approvalsDTO the approvalsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated approvalsDTO,
     * or with status 400 (Bad Request) if the approvalsDTO is not valid,
     * or with status 500 (Internal Server Error) if the approvalsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/approvals")
    @Timed
    public ResponseEntity<ApprovalsDTO> updateApprovals(@RequestBody ApprovalsDTO approvalsDTO) throws URISyntaxException {
        log.debug("REST request to update Approvals : {}", approvalsDTO);
        if (approvalsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApprovalsDTO result = approvalsService.save(approvalsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, approvalsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /approvals : get all the approvals.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of approvals in body
     */
    @GetMapping("/approvals")
    @Timed
    public ResponseEntity<List<ApprovalsDTO>> getAllApprovals(ApprovalsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Approvals by criteria: {}", criteria);
        Page<ApprovalsDTO> page = approvalsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/approvals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /approvals/:id : get the "id" approvals.
     *
     * @param id the id of the approvalsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the approvalsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/approvals/{id}")
    @Timed
    public ResponseEntity<ApprovalsDTO> getApprovals(@PathVariable Long id) {
        log.debug("REST request to get Approvals : {}", id);
        Optional<ApprovalsDTO> approvalsDTO = approvalsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(approvalsDTO);
    }

    /**
     * DELETE  /approvals/:id : delete the "id" approvals.
     *
     * @param id the id of the approvalsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/approvals/{id}")
    @Timed
    public ResponseEntity<Void> deleteApprovals(@PathVariable Long id) {
        log.debug("REST request to delete Approvals : {}", id);
        approvalsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    /*
     *   1) TENGO Q RETORNAR EL ESTADO GENERAL y el PARTICULAR
     *   2) si se aprobo la Particular y falta la General => envio notificacion
     * */
    @PostMapping("/approve")
    @Timed
    public ResponseEntity<Boolean> approve(@RequestBody Long id) throws URISyntaxException {
        log.debug("REST request to approve : {}", id);
        if (id != null) {
            throw new BadRequestAlertException("approve cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Approvals approvals = approvalsService.approve(id);
        
        //puede pasar que llege a un punto sin retorno, x ejemplo cuando no tenga mas supervisores en un nivel x
        	//- opciones darle la opcion q apruebe de todos modos - para eso crear un metodo aprroavcion.neverCantApprove()
        		//dejo q la execcion se envie a front
        this.notificationService.sendNotification(approvals);
        
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Approve", id.toString())).body(true);
    }

	public NotificationService getNotificationService() {
		return notificationService;
	}
}
