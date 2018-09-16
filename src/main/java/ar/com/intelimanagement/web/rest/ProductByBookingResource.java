package ar.com.intelimanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import ar.com.intelimanagement.service.ProductByBookingService;
import ar.com.intelimanagement.web.rest.errors.BadRequestAlertException;
import ar.com.intelimanagement.web.rest.util.HeaderUtil;
import ar.com.intelimanagement.service.dto.ProductByBookingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductByBooking.
 */
@RestController
@RequestMapping("/api")
public class ProductByBookingResource {

    private final Logger log = LoggerFactory.getLogger(ProductByBookingResource.class);

    private static final String ENTITY_NAME = "productByBooking";

    private final ProductByBookingService productByBookingService;

    public ProductByBookingResource(ProductByBookingService productByBookingService) {
        this.productByBookingService = productByBookingService;
    }

    /**
     * POST  /product-by-bookings : Create a new productByBooking.
     *
     * @param productByBookingDTO the productByBookingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productByBookingDTO, or with status 400 (Bad Request) if the productByBooking has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-by-bookings")
    @Timed
    public ResponseEntity<ProductByBookingDTO> createProductByBooking(@Valid @RequestBody ProductByBookingDTO productByBookingDTO) throws URISyntaxException {
        log.debug("REST request to save ProductByBooking : {}", productByBookingDTO);
        if (productByBookingDTO.getId() != null) {
            throw new BadRequestAlertException("A new productByBooking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductByBookingDTO result = productByBookingService.save(productByBookingDTO);
        return ResponseEntity.created(new URI("/api/product-by-bookings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-by-bookings : Updates an existing productByBooking.
     *
     * @param productByBookingDTO the productByBookingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productByBookingDTO,
     * or with status 400 (Bad Request) if the productByBookingDTO is not valid,
     * or with status 500 (Internal Server Error) if the productByBookingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-by-bookings")
    @Timed
    public ResponseEntity<ProductByBookingDTO> updateProductByBooking(@Valid @RequestBody ProductByBookingDTO productByBookingDTO) throws URISyntaxException {
        log.debug("REST request to update ProductByBooking : {}", productByBookingDTO);
        if (productByBookingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductByBookingDTO result = productByBookingService.save(productByBookingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productByBookingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-by-bookings : get all the productByBookings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productByBookings in body
     */
    @GetMapping("/product-by-bookings")
    @Timed
    public List<ProductByBookingDTO> getAllProductByBookings() {
        log.debug("REST request to get all ProductByBookings");
        return productByBookingService.findAll();
    }

    /**
     * GET  /product-by-bookings/:id : get the "id" productByBooking.
     *
     * @param id the id of the productByBookingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productByBookingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/product-by-bookings/{id}")
    @Timed
    public ResponseEntity<ProductByBookingDTO> getProductByBooking(@PathVariable Long id) {
        log.debug("REST request to get ProductByBooking : {}", id);
        Optional<ProductByBookingDTO> productByBookingDTO = productByBookingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productByBookingDTO);
    }

    /**
     * DELETE  /product-by-bookings/:id : delete the "id" productByBooking.
     *
     * @param id the id of the productByBookingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-by-bookings/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductByBooking(@PathVariable Long id) {
        log.debug("REST request to delete ProductByBooking : {}", id);
        productByBookingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
