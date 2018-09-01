package ar.com.intelimanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import ar.com.intelimanagement.service.ApprovalsService;
import ar.com.intelimanagement.web.rest.errors.BadRequestAlertException;
import ar.com.intelimanagement.web.rest.util.HeaderUtil;
import ar.com.intelimanagement.web.rest.util.PaginationUtil;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.dto.ApprovalsCriteria;
import ar.com.intelimanagement.service.ApprovalsQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Approvals.
 */
@RestController
@RequestMapping("/api")
public class ApprovalsResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalsResource.class);

    private static final String ENTITY_NAME = "approvals";

    private final ApprovalsService approvalsService;

    private final ApprovalsQueryService approvalsQueryService;

    public ApprovalsResource(ApprovalsService approvalsService, ApprovalsQueryService approvalsQueryService) {
        this.approvalsService = approvalsService;
        this.approvalsQueryService = approvalsQueryService;
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
}
