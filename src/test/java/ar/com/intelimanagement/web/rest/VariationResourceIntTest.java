package ar.com.intelimanagement.web.rest;

import ar.com.intelimanagement.InteliManagementApp;

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Provider;
import ar.com.intelimanagement.domain.Product;
import ar.com.intelimanagement.repository.VariationRepository;
import ar.com.intelimanagement.service.VariationService;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.mapper.VariationMapper;
import ar.com.intelimanagement.web.rest.errors.ExceptionTranslator;
import ar.com.intelimanagement.service.dto.VariationCriteria;
import ar.com.intelimanagement.service.VariationQueryService;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static ar.com.intelimanagement.web.rest.TestUtil.sameInstant;
import static ar.com.intelimanagement.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VariationResource REST controller.
 *
 * @see VariationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InteliManagementApp.class)
public class VariationResourceIntTest {

    private static final Double DEFAULT_EXTRA_CHARGE = 1D;
    private static final Double UPDATED_EXTRA_CHARGE = 2D;

    private static final Double DEFAULT_NEW_CHARGE = 1D;
    private static final Double UPDATED_NEW_CHARGE = 2D;

    private static final Double DEFAULT_NEW_COST = 1D;
    private static final Double UPDATED_NEW_COST = 2D;

    private static final Double DEFAULT_NEW_BENEFIT = 1D;
    private static final Double UPDATED_NEW_BENEFIT = 2D;

    private static final Integer DEFAULT_NEW_EXTERNAL_LOCATOR_ID = 1;
    private static final Integer UPDATED_NEW_EXTERNAL_LOCATOR_ID = 2;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATION_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATION_USER = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAING = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAING = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RECOVERABLE = false;
    private static final Boolean UPDATED_RECOVERABLE = true;

    private static final Integer DEFAULT_REFUND_IN_POINTS = 1;
    private static final Integer UPDATED_REFUND_IN_POINTS = 2;

    private static final Double DEFAULT_REFUND_IN_CASH = 1D;
    private static final Double UPDATED_REFUND_IN_CASH = 2D;

    private static final Boolean DEFAULT_CACEL = false;
    private static final Boolean UPDATED_CACEL = true;

    @Autowired
    private VariationRepository variationRepository;


    @Autowired
    private VariationMapper variationMapper;
    

    @Autowired
    private VariationService variationService;

    @Autowired
    private VariationQueryService variationQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVariationMockMvc;

    private Variation variation;
    /*
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VariationResource variationResource = new VariationResource(variationService, variationQueryService);
        this.restVariationMockMvc = MockMvcBuilders.standaloneSetup(variationResource)
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
     
    public static Variation createEntity(EntityManager em) {
        Variation variation = new Variation()
            .extraCharge(DEFAULT_EXTRA_CHARGE)
            .newCharge(DEFAULT_NEW_CHARGE)
            .newCost(DEFAULT_NEW_COST)
            .newBenefit(DEFAULT_NEW_BENEFIT)
            .newExternalLocatorId(DEFAULT_NEW_EXTERNAL_LOCATOR_ID)
            .comments(DEFAULT_COMMENTS)
            .creationDate(DEFAULT_CREATION_DATE)
            //.creationUser(DEFAULT_CREATION_USER)
            //.product(DEFAULT_PRODUCT)
            .area(DEFAULT_AREA)
            .campaing(DEFAULT_CAMPAING)
            .reason(DEFAULT_REASON)
            .recoverable(DEFAULT_RECOVERABLE)
            .refundInPoints(DEFAULT_REFUND_IN_POINTS)
            .refundInCash(DEFAULT_REFUND_IN_CASH)
            .cacel(DEFAULT_CACEL);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        variation.setCreationUser(user);
        return variation;
    }

    @Before
    public void initTest() {
        variation = createEntity(em);
    }

    @Test
    @Transactional
    public void createVariation() throws Exception {
        int databaseSizeBeforeCreate = variationRepository.findAll().size();

        // Create the Variation
        VariationDTO variationDTO = variationMapper.toDto(variation);
        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isCreated());

        // Validate the Variation in the database
        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeCreate + 1);
        Variation testVariation = variationList.get(variationList.size() - 1);
        assertThat(testVariation.getExtraCharge()).isEqualTo(DEFAULT_EXTRA_CHARGE);
        assertThat(testVariation.getNewCharge()).isEqualTo(DEFAULT_NEW_CHARGE);
        assertThat(testVariation.getNewCost()).isEqualTo(DEFAULT_NEW_COST);
        assertThat(testVariation.getNewBenefit()).isEqualTo(DEFAULT_NEW_BENEFIT);
        assertThat(testVariation.getNewExternalLocatorId()).isEqualTo(DEFAULT_NEW_EXTERNAL_LOCATOR_ID);
        assertThat(testVariation.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testVariation.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        //assertThat(testVariation.getCreation_user()).isEqualTo(DEFAULT_CREATION_USER);
        assertThat(testVariation.getProduct()).isEqualTo(DEFAULT_PRODUCT);
        assertThat(testVariation.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testVariation.getCampaing()).isEqualTo(DEFAULT_CAMPAING);
        assertThat(testVariation.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testVariation.getRecoverable()).isEqualTo(DEFAULT_RECOVERABLE);
        assertThat(testVariation.getRefundInPoints()).isEqualTo(DEFAULT_REFUND_IN_POINTS);
        assertThat(testVariation.getRefundInCash()).isEqualTo(DEFAULT_REFUND_IN_CASH);
        assertThat(testVariation.getCacel()).isEqualTo(DEFAULT_CACEL);
    }

    @Test
    @Transactional
    public void createVariationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = variationRepository.findAll().size();

        // Create the Variation with an existing ID
        variation.setId(1L);
        VariationDTO variationDTO = variationMapper.toDto(variation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variation in the database
        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setCreationDate(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreationUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setCreationUser(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setProduct(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setArea(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCampaingIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setCampaing(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = variationRepository.findAll().size();
        // set the field null
        variation.setReason(null);

        // Create the Variation, which fails.
        VariationDTO variationDTO = variationMapper.toDto(variation);

        restVariationMockMvc.perform(post("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVariations() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList
        restVariationMockMvc.perform(get("/api/variations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variation.getId().intValue())))
            .andExpect(jsonPath("$.[*].extraCharge").value(hasItem(DEFAULT_EXTRA_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].newCharge").value(hasItem(DEFAULT_NEW_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].newCost").value(hasItem(DEFAULT_NEW_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].newBenefit").value(hasItem(DEFAULT_NEW_BENEFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].newExternalLocatorId").value(hasItem(DEFAULT_NEW_EXTERNAL_LOCATOR_ID)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(sameInstant(DEFAULT_CREATION_DATE))))
            //.andExpect(jsonPath("$.[*].creation_user").value(hasItem(DEFAULT_CREATION_USER.toString())))
            //.andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
            .andExpect(jsonPath("$.[*].campaing").value(hasItem(DEFAULT_CAMPAING.toString())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].recoverable").value(hasItem(DEFAULT_RECOVERABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].refundInPoints").value(hasItem(DEFAULT_REFUND_IN_POINTS)))
            .andExpect(jsonPath("$.[*].refundInCash").value(hasItem(DEFAULT_REFUND_IN_CASH.doubleValue())))
            .andExpect(jsonPath("$.[*].cacel").value(hasItem(DEFAULT_CACEL.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getVariation() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get the variation
        restVariationMockMvc.perform(get("/api/variations/{id}", variation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(variation.getId().intValue()))
            .andExpect(jsonPath("$.extraCharge").value(DEFAULT_EXTRA_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.newCharge").value(DEFAULT_NEW_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.newCost").value(DEFAULT_NEW_COST.doubleValue()))
            .andExpect(jsonPath("$.new_benefit").value(DEFAULT_NEW_BENEFIT.doubleValue()))
            .andExpect(jsonPath("$.new_external_locator_id").value(DEFAULT_NEW_EXTERNAL_LOCATOR_ID))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.creation_date").value(sameInstant(DEFAULT_CREATION_DATE)))
            .andExpect(jsonPath("$.creation_user").value(DEFAULT_CREATION_USER.toString()))
            .andExpect(jsonPath("$.provider").value(DEFAULT_PROVIDER.toString()))
            .andExpect(jsonPath("$.product").value(DEFAULT_PRODUCT.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.campaing").value(DEFAULT_CAMPAING.toString()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.recoverable").value(DEFAULT_RECOVERABLE.booleanValue()))
            .andExpect(jsonPath("$.refund_in_points").value(DEFAULT_REFUND_IN_POINTS))
            .andExpect(jsonPath("$.refund_in_cash").value(DEFAULT_REFUND_IN_CASH.doubleValue()))
            .andExpect(jsonPath("$.cacel").value(DEFAULT_CACEL.booleanValue()));
    }
    
   
    @Test
    @Transactional
    public void getAllVariationsByExtra_chargeIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where extra_charge equals to DEFAULT_EXTRA_CHARGE
        //defaultVariationShouldBeFound("extraCharge.equals=" + DEFAULT_EXTRA_CHARGE);

        // Get all the variationList where extra_charge equals to UPDATED_EXTRA_CHARGE
        //defaultVariationShouldNotBeFound("extraCharge.equals=" + UPDATED_EXTRA_CHARGE);
    }

    @Test
    @Transactional
    public void getAllVariationsByExtra_chargeIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where extra_charge in DEFAULT_EXTRA_CHARGE or UPDATED_EXTRA_CHARGE
        defaultVariationShouldBeFound("extra_charge.in=" + DEFAULT_EXTRA_CHARGE + "," + UPDATED_EXTRA_CHARGE);

        // Get all the variationList where extra_charge equals to UPDATED_EXTRA_CHARGE
        defaultVariationShouldNotBeFound("extra_charge.in=" + UPDATED_EXTRA_CHARGE);
    }

    @Test
    @Transactional
    public void getAllVariationsByExtra_chargeIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where extra_charge is not null
        defaultVariationShouldBeFound("extra_charge.specified=true");

        // Get all the variationList where extra_charge is null
        defaultVariationShouldNotBeFound("extra_charge.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_chargeIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_charge equals to DEFAULT_NEW_CHARGE
        defaultVariationShouldBeFound("new_charge.equals=" + DEFAULT_NEW_CHARGE);

        // Get all the variationList where new_charge equals to UPDATED_NEW_CHARGE
        defaultVariationShouldNotBeFound("new_charge.equals=" + UPDATED_NEW_CHARGE);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_chargeIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_charge in DEFAULT_NEW_CHARGE or UPDATED_NEW_CHARGE
        defaultVariationShouldBeFound("new_charge.in=" + DEFAULT_NEW_CHARGE + "," + UPDATED_NEW_CHARGE);

        // Get all the variationList where new_charge equals to UPDATED_NEW_CHARGE
        defaultVariationShouldNotBeFound("new_charge.in=" + UPDATED_NEW_CHARGE);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_chargeIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_charge is not null
        defaultVariationShouldBeFound("new_charge.specified=true");

        // Get all the variationList where new_charge is null
        defaultVariationShouldNotBeFound("new_charge.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_costIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_cost equals to DEFAULT_NEW_COST
        defaultVariationShouldBeFound("new_cost.equals=" + DEFAULT_NEW_COST);

        // Get all the variationList where new_cost equals to UPDATED_NEW_COST
        defaultVariationShouldNotBeFound("new_cost.equals=" + UPDATED_NEW_COST);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_costIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_cost in DEFAULT_NEW_COST or UPDATED_NEW_COST
        defaultVariationShouldBeFound("new_cost.in=" + DEFAULT_NEW_COST + "," + UPDATED_NEW_COST);

        // Get all the variationList where new_cost equals to UPDATED_NEW_COST
        defaultVariationShouldNotBeFound("new_cost.in=" + UPDATED_NEW_COST);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_costIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_cost is not null
        defaultVariationShouldBeFound("new_cost.specified=true");

        // Get all the variationList where new_cost is null
        defaultVariationShouldNotBeFound("new_cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_benefitIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_benefit equals to DEFAULT_NEW_BENEFIT
        defaultVariationShouldBeFound("new_benefit.equals=" + DEFAULT_NEW_BENEFIT);

        // Get all the variationList where new_benefit equals to UPDATED_NEW_BENEFIT
        defaultVariationShouldNotBeFound("new_benefit.equals=" + UPDATED_NEW_BENEFIT);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_benefitIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_benefit in DEFAULT_NEW_BENEFIT or UPDATED_NEW_BENEFIT
        defaultVariationShouldBeFound("new_benefit.in=" + DEFAULT_NEW_BENEFIT + "," + UPDATED_NEW_BENEFIT);

        // Get all the variationList where new_benefit equals to UPDATED_NEW_BENEFIT
        defaultVariationShouldNotBeFound("new_benefit.in=" + UPDATED_NEW_BENEFIT);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_benefitIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_benefit is not null
        defaultVariationShouldBeFound("new_benefit.specified=true");

        // Get all the variationList where new_benefit is null
        defaultVariationShouldNotBeFound("new_benefit.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_external_locator_idIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_external_locator_id equals to DEFAULT_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldBeFound("new_external_locator_id.equals=" + DEFAULT_NEW_EXTERNAL_LOCATOR_ID);

        // Get all the variationList where new_external_locator_id equals to UPDATED_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldNotBeFound("new_external_locator_id.equals=" + UPDATED_NEW_EXTERNAL_LOCATOR_ID);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_external_locator_idIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_external_locator_id in DEFAULT_NEW_EXTERNAL_LOCATOR_ID or UPDATED_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldBeFound("new_external_locator_id.in=" + DEFAULT_NEW_EXTERNAL_LOCATOR_ID + "," + UPDATED_NEW_EXTERNAL_LOCATOR_ID);

        // Get all the variationList where new_external_locator_id equals to UPDATED_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldNotBeFound("new_external_locator_id.in=" + UPDATED_NEW_EXTERNAL_LOCATOR_ID);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_external_locator_idIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_external_locator_id is not null
        defaultVariationShouldBeFound("new_external_locator_id.specified=true");

        // Get all the variationList where new_external_locator_id is null
        defaultVariationShouldNotBeFound("new_external_locator_id.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_external_locator_idIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_external_locator_id greater than or equals to DEFAULT_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldBeFound("new_external_locator_id.greaterOrEqualThan=" + DEFAULT_NEW_EXTERNAL_LOCATOR_ID);

        // Get all the variationList where new_external_locator_id greater than or equals to UPDATED_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldNotBeFound("new_external_locator_id.greaterOrEqualThan=" + UPDATED_NEW_EXTERNAL_LOCATOR_ID);
    }

    @Test
    @Transactional
    public void getAllVariationsByNew_external_locator_idIsLessThanSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where new_external_locator_id less than or equals to DEFAULT_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldNotBeFound("new_external_locator_id.lessThan=" + DEFAULT_NEW_EXTERNAL_LOCATOR_ID);

        // Get all the variationList where new_external_locator_id less than or equals to UPDATED_NEW_EXTERNAL_LOCATOR_ID
        defaultVariationShouldBeFound("new_external_locator_id.lessThan=" + UPDATED_NEW_EXTERNAL_LOCATOR_ID);
    }


    @Test
    @Transactional
    public void getAllVariationsByCommentsIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where comments equals to DEFAULT_COMMENTS
        defaultVariationShouldBeFound("comments.equals=" + DEFAULT_COMMENTS);

        // Get all the variationList where comments equals to UPDATED_COMMENTS
        defaultVariationShouldNotBeFound("comments.equals=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    public void getAllVariationsByCommentsIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where comments in DEFAULT_COMMENTS or UPDATED_COMMENTS
        defaultVariationShouldBeFound("comments.in=" + DEFAULT_COMMENTS + "," + UPDATED_COMMENTS);

        // Get all the variationList where comments equals to UPDATED_COMMENTS
        defaultVariationShouldNotBeFound("comments.in=" + UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    public void getAllVariationsByCommentsIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where comments is not null
        defaultVariationShouldBeFound("comments.specified=true");

        // Get all the variationList where comments is null
        defaultVariationShouldNotBeFound("comments.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_dateIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_date equals to DEFAULT_CREATION_DATE
        defaultVariationShouldBeFound("creation_date.equals=" + DEFAULT_CREATION_DATE);

        // Get all the variationList where creation_date equals to UPDATED_CREATION_DATE
        defaultVariationShouldNotBeFound("creation_date.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_dateIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_date in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultVariationShouldBeFound("creation_date.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the variationList where creation_date equals to UPDATED_CREATION_DATE
        defaultVariationShouldNotBeFound("creation_date.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_dateIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_date is not null
        defaultVariationShouldBeFound("creation_date.specified=true");

        // Get all the variationList where creation_date is null
        defaultVariationShouldNotBeFound("creation_date.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_dateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_date greater than or equals to DEFAULT_CREATION_DATE
        defaultVariationShouldBeFound("creation_date.greaterOrEqualThan=" + DEFAULT_CREATION_DATE);

        // Get all the variationList where creation_date greater than or equals to UPDATED_CREATION_DATE
        defaultVariationShouldNotBeFound("creation_date.greaterOrEqualThan=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_dateIsLessThanSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_date less than or equals to DEFAULT_CREATION_DATE
        defaultVariationShouldNotBeFound("creation_date.lessThan=" + DEFAULT_CREATION_DATE);

        // Get all the variationList where creation_date less than or equals to UPDATED_CREATION_DATE
        defaultVariationShouldBeFound("creation_date.lessThan=" + UPDATED_CREATION_DATE);
    }


    @Test
    @Transactional
    public void getAllVariationsByCreation_userIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_user equals to DEFAULT_CREATION_USER
        defaultVariationShouldBeFound("creation_user.equals=" + DEFAULT_CREATION_USER);

        // Get all the variationList where creation_user equals to UPDATED_CREATION_USER
        defaultVariationShouldNotBeFound("creation_user.equals=" + UPDATED_CREATION_USER);
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_userIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_user in DEFAULT_CREATION_USER or UPDATED_CREATION_USER
        defaultVariationShouldBeFound("creation_user.in=" + DEFAULT_CREATION_USER + "," + UPDATED_CREATION_USER);

        // Get all the variationList where creation_user equals to UPDATED_CREATION_USER
        defaultVariationShouldNotBeFound("creation_user.in=" + UPDATED_CREATION_USER);
    }

    @Test
    @Transactional
    public void getAllVariationsByCreation_userIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where creation_user is not null
        defaultVariationShouldBeFound("creation_user.specified=true");

        // Get all the variationList where creation_user is null
        defaultVariationShouldNotBeFound("creation_user.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByProviderIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where provider equals to DEFAULT_PROVIDER
        defaultVariationShouldBeFound("provider.equals=" + DEFAULT_PROVIDER);

        // Get all the variationList where provider equals to UPDATED_PROVIDER
        defaultVariationShouldNotBeFound("provider.equals=" + UPDATED_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllVariationsByProviderIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where provider in DEFAULT_PROVIDER or UPDATED_PROVIDER
        defaultVariationShouldBeFound("provider.in=" + DEFAULT_PROVIDER + "," + UPDATED_PROVIDER);

        // Get all the variationList where provider equals to UPDATED_PROVIDER
        defaultVariationShouldNotBeFound("provider.in=" + UPDATED_PROVIDER);
    }

    @Test
    @Transactional
    public void getAllVariationsByProviderIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where provider is not null
        defaultVariationShouldBeFound("provider.specified=true");

        // Get all the variationList where provider is null
        defaultVariationShouldNotBeFound("provider.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByProductIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where product equals to DEFAULT_PRODUCT
        defaultVariationShouldBeFound("product.equals=" + DEFAULT_PRODUCT);

        // Get all the variationList where product equals to UPDATED_PRODUCT
        defaultVariationShouldNotBeFound("product.equals=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllVariationsByProductIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where product in DEFAULT_PRODUCT or UPDATED_PRODUCT
        defaultVariationShouldBeFound("product.in=" + DEFAULT_PRODUCT + "," + UPDATED_PRODUCT);

        // Get all the variationList where product equals to UPDATED_PRODUCT
        defaultVariationShouldNotBeFound("product.in=" + UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllVariationsByProductIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where product is not null
        defaultVariationShouldBeFound("product.specified=true");

        // Get all the variationList where product is null
        defaultVariationShouldNotBeFound("product.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where area equals to DEFAULT_AREA
        defaultVariationShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the variationList where area equals to UPDATED_AREA
        defaultVariationShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllVariationsByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where area in DEFAULT_AREA or UPDATED_AREA
        defaultVariationShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the variationList where area equals to UPDATED_AREA
        defaultVariationShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllVariationsByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where area is not null
        defaultVariationShouldBeFound("area.specified=true");

        // Get all the variationList where area is null
        defaultVariationShouldNotBeFound("area.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByCampaingIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where campaing equals to DEFAULT_CAMPAING
        defaultVariationShouldBeFound("campaing.equals=" + DEFAULT_CAMPAING);

        // Get all the variationList where campaing equals to UPDATED_CAMPAING
        defaultVariationShouldNotBeFound("campaing.equals=" + UPDATED_CAMPAING);
    }

    @Test
    @Transactional
    public void getAllVariationsByCampaingIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where campaing in DEFAULT_CAMPAING or UPDATED_CAMPAING
        defaultVariationShouldBeFound("campaing.in=" + DEFAULT_CAMPAING + "," + UPDATED_CAMPAING);

        // Get all the variationList where campaing equals to UPDATED_CAMPAING
        defaultVariationShouldNotBeFound("campaing.in=" + UPDATED_CAMPAING);
    }

    @Test
    @Transactional
    public void getAllVariationsByCampaingIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where campaing is not null
        defaultVariationShouldBeFound("campaing.specified=true");

        // Get all the variationList where campaing is null
        defaultVariationShouldNotBeFound("campaing.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByReasonIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where reason equals to DEFAULT_REASON
        defaultVariationShouldBeFound("reason.equals=" + DEFAULT_REASON);

        // Get all the variationList where reason equals to UPDATED_REASON
        defaultVariationShouldNotBeFound("reason.equals=" + UPDATED_REASON);
    }

    @Test
    @Transactional
    public void getAllVariationsByReasonIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where reason in DEFAULT_REASON or UPDATED_REASON
        defaultVariationShouldBeFound("reason.in=" + DEFAULT_REASON + "," + UPDATED_REASON);

        // Get all the variationList where reason equals to UPDATED_REASON
        defaultVariationShouldNotBeFound("reason.in=" + UPDATED_REASON);
    }

    @Test
    @Transactional
    public void getAllVariationsByReasonIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where reason is not null
        defaultVariationShouldBeFound("reason.specified=true");

        // Get all the variationList where reason is null
        defaultVariationShouldNotBeFound("reason.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByRecoverableIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where recoverable equals to DEFAULT_RECOVERABLE
        defaultVariationShouldBeFound("recoverable.equals=" + DEFAULT_RECOVERABLE);

        // Get all the variationList where recoverable equals to UPDATED_RECOVERABLE
        defaultVariationShouldNotBeFound("recoverable.equals=" + UPDATED_RECOVERABLE);
    }

    @Test
    @Transactional
    public void getAllVariationsByRecoverableIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where recoverable in DEFAULT_RECOVERABLE or UPDATED_RECOVERABLE
        defaultVariationShouldBeFound("recoverable.in=" + DEFAULT_RECOVERABLE + "," + UPDATED_RECOVERABLE);

        // Get all the variationList where recoverable equals to UPDATED_RECOVERABLE
        defaultVariationShouldNotBeFound("recoverable.in=" + UPDATED_RECOVERABLE);
    }

    @Test
    @Transactional
    public void getAllVariationsByRecoverableIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where recoverable is not null
        defaultVariationShouldBeFound("recoverable.specified=true");

        // Get all the variationList where recoverable is null
        defaultVariationShouldNotBeFound("recoverable.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_pointsIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_points equals to DEFAULT_REFUND_IN_POINTS
        defaultVariationShouldBeFound("refund_in_points.equals=" + DEFAULT_REFUND_IN_POINTS);

        // Get all the variationList where refund_in_points equals to UPDATED_REFUND_IN_POINTS
        defaultVariationShouldNotBeFound("refund_in_points.equals=" + UPDATED_REFUND_IN_POINTS);
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_pointsIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_points in DEFAULT_REFUND_IN_POINTS or UPDATED_REFUND_IN_POINTS
        defaultVariationShouldBeFound("refund_in_points.in=" + DEFAULT_REFUND_IN_POINTS + "," + UPDATED_REFUND_IN_POINTS);

        // Get all the variationList where refund_in_points equals to UPDATED_REFUND_IN_POINTS
        defaultVariationShouldNotBeFound("refund_in_points.in=" + UPDATED_REFUND_IN_POINTS);
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_pointsIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_points is not null
        defaultVariationShouldBeFound("refund_in_points.specified=true");

        // Get all the variationList where refund_in_points is null
        defaultVariationShouldNotBeFound("refund_in_points.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_pointsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_points greater than or equals to DEFAULT_REFUND_IN_POINTS
        defaultVariationShouldBeFound("refund_in_points.greaterOrEqualThan=" + DEFAULT_REFUND_IN_POINTS);

        // Get all the variationList where refund_in_points greater than or equals to UPDATED_REFUND_IN_POINTS
        defaultVariationShouldNotBeFound("refund_in_points.greaterOrEqualThan=" + UPDATED_REFUND_IN_POINTS);
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_pointsIsLessThanSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_points less than or equals to DEFAULT_REFUND_IN_POINTS
        defaultVariationShouldNotBeFound("refund_in_points.lessThan=" + DEFAULT_REFUND_IN_POINTS);

        // Get all the variationList where refund_in_points less than or equals to UPDATED_REFUND_IN_POINTS
        defaultVariationShouldBeFound("refund_in_points.lessThan=" + UPDATED_REFUND_IN_POINTS);
    }


    @Test
    @Transactional
    public void getAllVariationsByRefund_in_cashIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_cash equals to DEFAULT_REFUND_IN_CASH
        defaultVariationShouldBeFound("refund_in_cash.equals=" + DEFAULT_REFUND_IN_CASH);

        // Get all the variationList where refund_in_cash equals to UPDATED_REFUND_IN_CASH
        defaultVariationShouldNotBeFound("refund_in_cash.equals=" + UPDATED_REFUND_IN_CASH);
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_cashIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_cash in DEFAULT_REFUND_IN_CASH or UPDATED_REFUND_IN_CASH
        defaultVariationShouldBeFound("refund_in_cash.in=" + DEFAULT_REFUND_IN_CASH + "," + UPDATED_REFUND_IN_CASH);

        // Get all the variationList where refund_in_cash equals to UPDATED_REFUND_IN_CASH
        defaultVariationShouldNotBeFound("refund_in_cash.in=" + UPDATED_REFUND_IN_CASH);
    }

    @Test
    @Transactional
    public void getAllVariationsByRefund_in_cashIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where refund_in_cash is not null
        defaultVariationShouldBeFound("refund_in_cash.specified=true");

        // Get all the variationList where refund_in_cash is null
        defaultVariationShouldNotBeFound("refund_in_cash.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByCacelIsEqualToSomething() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where cacel equals to DEFAULT_CACEL
        defaultVariationShouldBeFound("cacel.equals=" + DEFAULT_CACEL);

        // Get all the variationList where cacel equals to UPDATED_CACEL
        defaultVariationShouldNotBeFound("cacel.equals=" + UPDATED_CACEL);
    }

    @Test
    @Transactional
    public void getAllVariationsByCacelIsInShouldWork() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where cacel in DEFAULT_CACEL or UPDATED_CACEL
        defaultVariationShouldBeFound("cacel.in=" + DEFAULT_CACEL + "," + UPDATED_CACEL);

        // Get all the variationList where cacel equals to UPDATED_CACEL
        defaultVariationShouldNotBeFound("cacel.in=" + UPDATED_CACEL);
    }

    @Test
    @Transactional
    public void getAllVariationsByCacelIsNullOrNotNull() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        // Get all the variationList where cacel is not null
        defaultVariationShouldBeFound("cacel.specified=true");

        // Get all the variationList where cacel is null
        defaultVariationShouldNotBeFound("cacel.specified=false");
    }

    @Test
    @Transactional
    public void getAllVariationsByRelationship_user_variationIsEqualToSomething() throws Exception {
        // Initialize the database
        User relationship_user_variation = UserResourceIntTest.createEntity(em);
        em.persist(relationship_user_variation);
        em.flush();
        variation.setRelationship_user_variation(relationship_user_variation);
        variationRepository.saveAndFlush(variation);
        Long relationship_user_variationId = relationship_user_variation.getId();

        // Get all the variationList where relationship_user_variation equals to relationship_user_variationId
        defaultVariationShouldBeFound("relationship_user_variationId.equals=" + relationship_user_variationId);

        // Get all the variationList where relationship_user_variation equals to relationship_user_variationId + 1
        defaultVariationShouldNotBeFound("relationship_user_variationId.equals=" + (relationship_user_variationId + 1));
    }


    @Test
    @Transactional
    public void getAllVariationsByRelationship_provider_variationIsEqualToSomething() throws Exception {
        // Initialize the database
        Provider relationship_provider_variation = ProviderResourceIntTest.createEntity(em);
        em.persist(relationship_provider_variation);
        em.flush();
        variation.addRelationshipProviderVariation(relationship_provider_variation);
        variationRepository.saveAndFlush(variation);
        Long relationship_provider_variationId = relationship_provider_variation.getId();

        // Get all the variationList where relationship_provider_variation equals to relationship_provider_variationId
        defaultVariationShouldBeFound("relationship_provider_variationId.equals=" + relationship_provider_variationId);

        // Get all the variationList where relationship_provider_variation equals to relationship_provider_variationId + 1
        defaultVariationShouldNotBeFound("relationship_provider_variationId.equals=" + (relationship_provider_variationId + 1));
    }


    @Test
    @Transactional
    public void getAllVariationsByRelationship_product_variationIsEqualToSomething() throws Exception {
        // Initialize the database
        Product relationship_product_variation = ProductResourceIntTest.createEntity(em);
        em.persist(relationship_product_variation);
        em.flush();
        variation.addRelationshipProductVariation(relationship_product_variation);
        variationRepository.saveAndFlush(variation);
        Long relationship_product_variationId = relationship_product_variation.getId();

        // Get all the variationList where relationship_product_variation equals to relationship_product_variationId
        defaultVariationShouldBeFound("relationship_product_variationId.equals=" + relationship_product_variationId);

        // Get all the variationList where relationship_product_variation equals to relationship_product_variationId + 1
        defaultVariationShouldNotBeFound("relationship_product_variationId.equals=" + (relationship_product_variationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     
    private void defaultVariationShouldBeFound(String filter) throws Exception {
        restVariationMockMvc.perform(get("/api/variations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variation.getId().intValue())))
            .andExpect(jsonPath("$.[*].extra_charge").value(hasItem(DEFAULT_EXTRA_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].new_charge").value(hasItem(DEFAULT_NEW_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].new_cost").value(hasItem(DEFAULT_NEW_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].new_benefit").value(hasItem(DEFAULT_NEW_BENEFIT.doubleValue())))
            .andExpect(jsonPath("$.[*].new_external_locator_id").value(hasItem(DEFAULT_NEW_EXTERNAL_LOCATOR_ID)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].creation_date").value(hasItem(sameInstant(DEFAULT_CREATION_DATE))))
            .andExpect(jsonPath("$.[*].creation_user").value(hasItem(DEFAULT_CREATION_USER.toString())))
            .andExpect(jsonPath("$.[*].provider").value(hasItem(DEFAULT_PROVIDER.toString())))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
            .andExpect(jsonPath("$.[*].campaing").value(hasItem(DEFAULT_CAMPAING.toString())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].recoverable").value(hasItem(DEFAULT_RECOVERABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].refund_in_points").value(hasItem(DEFAULT_REFUND_IN_POINTS)))
            .andExpect(jsonPath("$.[*].refund_in_cash").value(hasItem(DEFAULT_REFUND_IN_CASH.doubleValue())))
            .andExpect(jsonPath("$.[*].cacel").value(hasItem(DEFAULT_CACEL.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned

    private void defaultVariationShouldNotBeFound(String filter) throws Exception {
        restVariationMockMvc.perform(get("/api/variations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingVariation() throws Exception {
        // Get the variation
        restVariationMockMvc.perform(get("/api/variations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVariation() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        int databaseSizeBeforeUpdate = variationRepository.findAll().size();

        // Update the variation
        Variation updatedVariation = variationRepository.findById(variation.getId()).get();
        // Disconnect from session so that the updates on updatedVariation are not directly saved in db
        em.detach(updatedVariation);
        updatedVariation
            .extra_charge(UPDATED_EXTRA_CHARGE)
            .new_charge(UPDATED_NEW_CHARGE)
            .new_cost(UPDATED_NEW_COST)
            .new_benefit(UPDATED_NEW_BENEFIT)
            .new_external_locator_id(UPDATED_NEW_EXTERNAL_LOCATOR_ID)
            .comments(UPDATED_COMMENTS)
            .creation_date(UPDATED_CREATION_DATE)
            .creation_user(UPDATED_CREATION_USER)
            .provider(UPDATED_PROVIDER)
            .product(UPDATED_PRODUCT)
            .area(UPDATED_AREA)
            .campaing(UPDATED_CAMPAING)
            .reason(UPDATED_REASON)
            .recoverable(UPDATED_RECOVERABLE)
            .refund_in_points(UPDATED_REFUND_IN_POINTS)
            .refund_in_cash(UPDATED_REFUND_IN_CASH)
            .cacel(UPDATED_CACEL);
        VariationDTO variationDTO = variationMapper.toDto(updatedVariation);

        restVariationMockMvc.perform(put("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isOk());

        // Validate the Variation in the database
        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeUpdate);
        Variation testVariation = variationList.get(variationList.size() - 1);
        assertThat(testVariation.getExtra_charge()).isEqualTo(UPDATED_EXTRA_CHARGE);
        assertThat(testVariation.getNew_charge()).isEqualTo(UPDATED_NEW_CHARGE);
        assertThat(testVariation.getNew_cost()).isEqualTo(UPDATED_NEW_COST);
        assertThat(testVariation.getNew_benefit()).isEqualTo(UPDATED_NEW_BENEFIT);
        assertThat(testVariation.getNew_external_locator_id()).isEqualTo(UPDATED_NEW_EXTERNAL_LOCATOR_ID);
        assertThat(testVariation.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testVariation.getCreation_date()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testVariation.getCreation_user()).isEqualTo(UPDATED_CREATION_USER);
        assertThat(testVariation.getProvider()).isEqualTo(UPDATED_PROVIDER);
        assertThat(testVariation.getProduct()).isEqualTo(UPDATED_PRODUCT);
        assertThat(testVariation.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testVariation.getCampaing()).isEqualTo(UPDATED_CAMPAING);
        assertThat(testVariation.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testVariation.isRecoverable()).isEqualTo(UPDATED_RECOVERABLE);
        assertThat(testVariation.getRefund_in_points()).isEqualTo(UPDATED_REFUND_IN_POINTS);
        assertThat(testVariation.getRefund_in_cash()).isEqualTo(UPDATED_REFUND_IN_CASH);
        assertThat(testVariation.isCacel()).isEqualTo(UPDATED_CACEL);
    }

    @Test
    @Transactional
    public void updateNonExistingVariation() throws Exception {
        int databaseSizeBeforeUpdate = variationRepository.findAll().size();

        // Create the Variation
        VariationDTO variationDTO = variationMapper.toDto(variation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restVariationMockMvc.perform(put("/api/variations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variation in the database
        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVariation() throws Exception {
        // Initialize the database
        variationRepository.saveAndFlush(variation);

        int databaseSizeBeforeDelete = variationRepository.findAll().size();

        // Get the variation
        restVariationMockMvc.perform(delete("/api/variations/{id}", variation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Variation> variationList = variationRepository.findAll();
        assertThat(variationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Variation.class);
        Variation variation1 = new Variation();
        variation1.setId(1L);
        Variation variation2 = new Variation();
        variation2.setId(variation1.getId());
        assertThat(variation1).isEqualTo(variation2);
        variation2.setId(2L);
        assertThat(variation1).isNotEqualTo(variation2);
        variation1.setId(null);
        assertThat(variation1).isNotEqualTo(variation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VariationDTO.class);
        VariationDTO variationDTO1 = new VariationDTO();
        variationDTO1.setId(1L);
        VariationDTO variationDTO2 = new VariationDTO();
        assertThat(variationDTO1).isNotEqualTo(variationDTO2);
        variationDTO2.setId(variationDTO1.getId());
        assertThat(variationDTO1).isEqualTo(variationDTO2);
        variationDTO2.setId(2L);
        assertThat(variationDTO1).isNotEqualTo(variationDTO2);
        variationDTO1.setId(null);
        assertThat(variationDTO1).isNotEqualTo(variationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(variationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(variationMapper.fromId(null)).isNull();
    }
    */
}
