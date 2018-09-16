package ar.com.intelimanagement.web.rest;

import ar.com.intelimanagement.InteliManagementApp;

import ar.com.intelimanagement.domain.ProductByBooking;
import ar.com.intelimanagement.domain.Product;
import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.repository.ProductByBookingRepository;
import ar.com.intelimanagement.service.ProductByBookingService;
import ar.com.intelimanagement.service.dto.ProductByBookingDTO;
import ar.com.intelimanagement.service.mapper.ProductByBookingMapper;
import ar.com.intelimanagement.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static ar.com.intelimanagement.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductByBookingResource REST controller.
 *
 * @see ProductByBookingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InteliManagementApp.class)
public class ProductByBookingResourceIntTest {

    @Autowired
    private ProductByBookingRepository productByBookingRepository;


    @Autowired
    private ProductByBookingMapper productByBookingMapper;
    

    @Autowired
    private ProductByBookingService productByBookingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductByBookingMockMvc;

    private ProductByBooking productByBooking;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductByBookingResource productByBookingResource = new ProductByBookingResource(productByBookingService);
        this.restProductByBookingMockMvc = MockMvcBuilders.standaloneSetup(productByBookingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductByBooking createEntity(EntityManager em) {
        ProductByBooking productByBooking = new ProductByBooking();
        // Add required entity
        Product product = ProductResourceIntTest.createEntity(em);
        em.persist(product);
        em.flush();
        productByBooking.setProduct(product);
        // Add required entity
        Booking booking = BookingResourceIntTest.createEntity(em);
        em.persist(booking);
        em.flush();
        productByBooking.setBooking(booking);
        return productByBooking;
    }

    @Before
    public void initTest() {
        productByBooking = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductByBooking() throws Exception {
        int databaseSizeBeforeCreate = productByBookingRepository.findAll().size();

        // Create the ProductByBooking
        ProductByBookingDTO productByBookingDTO = productByBookingMapper.toDto(productByBooking);
        restProductByBookingMockMvc.perform(post("/api/product-by-bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productByBookingDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductByBooking in the database
        List<ProductByBooking> productByBookingList = productByBookingRepository.findAll();
        assertThat(productByBookingList).hasSize(databaseSizeBeforeCreate + 1);
        ProductByBooking testProductByBooking = productByBookingList.get(productByBookingList.size() - 1);
    }

    @Test
    @Transactional
    public void createProductByBookingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productByBookingRepository.findAll().size();

        // Create the ProductByBooking with an existing ID
        productByBooking.setId(1L);
        ProductByBookingDTO productByBookingDTO = productByBookingMapper.toDto(productByBooking);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductByBookingMockMvc.perform(post("/api/product-by-bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productByBookingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductByBooking in the database
        List<ProductByBooking> productByBookingList = productByBookingRepository.findAll();
        assertThat(productByBookingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductByBookings() throws Exception {
        // Initialize the database
        productByBookingRepository.saveAndFlush(productByBooking);

        // Get all the productByBookingList
        restProductByBookingMockMvc.perform(get("/api/product-by-bookings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productByBooking.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getProductByBooking() throws Exception {
        // Initialize the database
        productByBookingRepository.saveAndFlush(productByBooking);

        // Get the productByBooking
        restProductByBookingMockMvc.perform(get("/api/product-by-bookings/{id}", productByBooking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productByBooking.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProductByBooking() throws Exception {
        // Get the productByBooking
        restProductByBookingMockMvc.perform(get("/api/product-by-bookings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductByBooking() throws Exception {
        // Initialize the database
        productByBookingRepository.saveAndFlush(productByBooking);

        int databaseSizeBeforeUpdate = productByBookingRepository.findAll().size();

        // Update the productByBooking
        ProductByBooking updatedProductByBooking = productByBookingRepository.findById(productByBooking.getId()).get();
        // Disconnect from session so that the updates on updatedProductByBooking are not directly saved in db
        em.detach(updatedProductByBooking);
        ProductByBookingDTO productByBookingDTO = productByBookingMapper.toDto(updatedProductByBooking);

        restProductByBookingMockMvc.perform(put("/api/product-by-bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productByBookingDTO)))
            .andExpect(status().isOk());

        // Validate the ProductByBooking in the database
        List<ProductByBooking> productByBookingList = productByBookingRepository.findAll();
        assertThat(productByBookingList).hasSize(databaseSizeBeforeUpdate);
        ProductByBooking testProductByBooking = productByBookingList.get(productByBookingList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProductByBooking() throws Exception {
        int databaseSizeBeforeUpdate = productByBookingRepository.findAll().size();

        // Create the ProductByBooking
        ProductByBookingDTO productByBookingDTO = productByBookingMapper.toDto(productByBooking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restProductByBookingMockMvc.perform(put("/api/product-by-bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productByBookingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductByBooking in the database
        List<ProductByBooking> productByBookingList = productByBookingRepository.findAll();
        assertThat(productByBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductByBooking() throws Exception {
        // Initialize the database
        productByBookingRepository.saveAndFlush(productByBooking);

        int databaseSizeBeforeDelete = productByBookingRepository.findAll().size();

        // Get the productByBooking
        restProductByBookingMockMvc.perform(delete("/api/product-by-bookings/{id}", productByBooking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductByBooking> productByBookingList = productByBookingRepository.findAll();
        assertThat(productByBookingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductByBooking.class);
        ProductByBooking productByBooking1 = new ProductByBooking();
        productByBooking1.setId(1L);
        ProductByBooking productByBooking2 = new ProductByBooking();
        productByBooking2.setId(productByBooking1.getId());
        assertThat(productByBooking1).isEqualTo(productByBooking2);
        productByBooking2.setId(2L);
        assertThat(productByBooking1).isNotEqualTo(productByBooking2);
        productByBooking1.setId(null);
        assertThat(productByBooking1).isNotEqualTo(productByBooking2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductByBookingDTO.class);
        ProductByBookingDTO productByBookingDTO1 = new ProductByBookingDTO();
        productByBookingDTO1.setId(1L);
        ProductByBookingDTO productByBookingDTO2 = new ProductByBookingDTO();
        assertThat(productByBookingDTO1).isNotEqualTo(productByBookingDTO2);
        productByBookingDTO2.setId(productByBookingDTO1.getId());
        assertThat(productByBookingDTO1).isEqualTo(productByBookingDTO2);
        productByBookingDTO2.setId(2L);
        assertThat(productByBookingDTO1).isNotEqualTo(productByBookingDTO2);
        productByBookingDTO1.setId(null);
        assertThat(productByBookingDTO1).isNotEqualTo(productByBookingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productByBookingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productByBookingMapper.fromId(null)).isNull();
    }
}
