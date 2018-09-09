package ar.com.intelimanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import ar.com.intelimanagement.service.VariationService;
import ar.com.intelimanagement.web.rest.errors.BadRequestAlertException;
import ar.com.intelimanagement.web.rest.util.HeaderUtil;
import ar.com.intelimanagement.web.rest.util.PaginationUtil;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.dto.VariationCriteria;
import ar.com.intelimanagement.service.VariationQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Variation.
 */
@RestController
@RequestMapping("/api")
public class VariationResource {

    private final Logger log = LoggerFactory.getLogger(VariationResource.class);

    private static final String ENTITY_NAME = "variation";

    private final VariationService variationService;

    private final VariationQueryService variationQueryService;

    public VariationResource(VariationService variationService, VariationQueryService variationQueryService) {
        this.variationService = variationService;
        this.variationQueryService = variationQueryService;
    }

    /**
     * POST  /variations : Create a new variation.
     *
     * @param variationDTO the variationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new variationDTO, or with status 400 (Bad Request) if the variation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/variations")
    @Timed
    public ResponseEntity<VariationDTO> createVariation(@Valid @RequestBody VariationDTO variationDTO) throws URISyntaxException {
        log.debug("REST request to save Variation : {}", variationDTO);
        if (variationDTO.getId() != null) {
            throw new BadRequestAlertException("A new variation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VariationDTO result = variationService.save(variationDTO);
        return ResponseEntity.created(new URI("/api/variations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /variations : Updates an existing variation.
     *
     * @param variationDTO the variationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated variationDTO,
     * or with status 400 (Bad Request) if the variationDTO is not valid,
     * or with status 500 (Internal Server Error) if the variationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/variations")
    @Timed
    public ResponseEntity<VariationDTO> updateVariation(@Valid @RequestBody VariationDTO variationDTO) throws URISyntaxException {
        log.debug("REST request to update Variation : {}", variationDTO);
        if (variationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VariationDTO result = variationService.save(variationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, variationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /variations : get all the variations.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of variations in body
     */
    @GetMapping("/variations")
    @Timed
    public ResponseEntity<List<VariationDTO>> getAllVariations(VariationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Variations by criteria: {}", criteria);
        Page<VariationDTO> page = variationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/variations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /variations/:id : get the "id" variation.
     *
     * @param id the id of the variationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the variationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/variations/{id}")
    @Timed
    public ResponseEntity<VariationDTO> getVariation(@PathVariable Long id) {
        log.debug("REST request to get Variation : {}", id);
        Optional<VariationDTO> variationDTO = variationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(variationDTO);
    }

    /**
     * DELETE  /variations/:id : delete the "id" variation.
     *
     * @param id the id of the variationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/variations/{id}")
    @Timed
    public ResponseEntity<Void> deleteVariation(@PathVariable Long id) {
        log.debug("REST request to delete Variation : {}", id);
        variationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}