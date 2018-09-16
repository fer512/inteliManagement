package ar.com.intelimanagement.web.rest;

import ar.com.intelimanagement.InteliManagementApp;

import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.domain.Company;
import ar.com.intelimanagement.domain.ProductByBooking;
import ar.com.intelimanagement.repository.BookingRepository;
import ar.com.intelimanagement.service.BookingService;
import ar.com.intelimanagement.service.dto.BookingDTO;
import ar.com.intelimanagement.service.mapper.BookingMapper;
import ar.com.intelimanagement.web.rest.errors.ExceptionTranslator;
import ar.com.intelimanagement.service.dto.BookingCriteria;
import ar.com.intelimanagement.service.BookingQueryService;

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
 * Test class for the BookingResource REST controller.
 *
 * @see BookingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InteliManagementApp.class)
public class BookingResourceIntTest {

    private static final String DEFAULT_ID_TRANSACTION = "AAAAAAAAAA";
    private static final String UPDATED_ID_TRANSACTION = "BBBBBBBBBB";

    private static final String DEFAULT_ID_RESERVE_LOCATOR_JUNIPER = "AAAAAAAAAA";
    private static final String UPDATED_ID_RESERVE_LOCATOR_JUNIPER = "BBBBBBBBBB";

    private static final String DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL = "AAAAAAAAAA";
    private static final String UPDATED_ID_RESERVE_LOCATOR_EXTERNAL = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_PAYMENT_CREDIT_CARD = 1D;
    private static final Double UPDATED_PAYMENT_CREDIT_CARD = 2D;

    private static final Integer DEFAULT_PAYMENT_POINTS_IN_USD = 1;
    private static final Integer UPDATED_PAYMENT_POINTS_IN_USD = 2;

    private static final Double DEFAULT_JUNIPER_SALE_PRICE = 1D;
    private static final Double UPDATED_JUNIPER_SALE_PRICE = 2D;

    private static final Double DEFAULT_JUNIPER_RESERVATION_COST = 1D;
    private static final Double UPDATED_JUNIPER_RESERVATION_COST = 2D;

    private static final Double DEFAULT_BENEFIT_IN_RESERVATION = 1D;
    private static final Double UPDATED_BENEFIT_IN_RESERVATION = 2D;

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private BookingMapper bookingMapper;
    

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingQueryService bookingQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBookingMockMvc;

    private Booking booking;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingResource bookingResource = new BookingResource(bookingService, bookingQueryService);
        this.restBookingMockMvc = MockMvcBuilders.standaloneSetup(bookingResource)
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
    public static Booking createEntity(EntityManager em) {
        Booking booking = new Booking()
            .idTransaction(DEFAULT_ID_TRANSACTION)
            .idReserveLocatorJuniper(DEFAULT_ID_RESERVE_LOCATOR_JUNIPER)
            .idReserveLocatorExternal(DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL)
            .detail(DEFAULT_DETAIL)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .paymentCreditCard(DEFAULT_PAYMENT_CREDIT_CARD)
            .paymentPointsInUSD(DEFAULT_PAYMENT_POINTS_IN_USD)
            .juniperSalePrice(DEFAULT_JUNIPER_SALE_PRICE)
            .juniperReservationCost(DEFAULT_JUNIPER_RESERVATION_COST)
            .benefitInReservation(DEFAULT_BENEFIT_IN_RESERVATION);
        return booking;
    }

    @Before
    public void initTest() {
        booking = createEntity(em);
    }

    @Test
    @Transactional
    public void createBooking() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();

        // Create the Booking
        BookingDTO bookingDTO = bookingMapper.toDto(booking);
        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isCreated());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate + 1);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getIdTransaction()).isEqualTo(DEFAULT_ID_TRANSACTION);
        assertThat(testBooking.getIdReserveLocatorJuniper()).isEqualTo(DEFAULT_ID_RESERVE_LOCATOR_JUNIPER);
        assertThat(testBooking.getIdReserveLocatorExternal()).isEqualTo(DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL);
        assertThat(testBooking.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testBooking.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testBooking.getPaymentCreditCard()).isEqualTo(DEFAULT_PAYMENT_CREDIT_CARD);
        assertThat(testBooking.getPaymentPointsInUSD()).isEqualTo(DEFAULT_PAYMENT_POINTS_IN_USD);
        assertThat(testBooking.getJuniperSalePrice()).isEqualTo(DEFAULT_JUNIPER_SALE_PRICE);
        assertThat(testBooking.getJuniperReservationCost()).isEqualTo(DEFAULT_JUNIPER_RESERVATION_COST);
        assertThat(testBooking.getBenefitInReservation()).isEqualTo(DEFAULT_BENEFIT_IN_RESERVATION);
    }

    @Test
    @Transactional
    public void createBookingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();

        // Create the Booking with an existing ID
        booking.setId(1L);
        BookingDTO bookingDTO = bookingMapper.toDto(booking);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPaymentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setPaymentType(null);

        // Create the Booking, which fails.
        BookingDTO bookingDTO = bookingMapper.toDto(booking);

        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJuniperSalePriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setJuniperSalePrice(null);

        // Create the Booking, which fails.
        BookingDTO bookingDTO = bookingMapper.toDto(booking);

        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJuniperReservationCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingRepository.findAll().size();
        // set the field null
        booking.setJuniperReservationCost(null);

        // Create the Booking, which fails.
        BookingDTO bookingDTO = bookingMapper.toDto(booking);

        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isBadRequest());

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookings() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList
        restBookingMockMvc.perform(get("/api/bookings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booking.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTransaction").value(hasItem(DEFAULT_ID_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].idReserveLocatorJuniper").value(hasItem(DEFAULT_ID_RESERVE_LOCATOR_JUNIPER.toString())))
            .andExpect(jsonPath("$.[*].idReserveLocatorExternal").value(hasItem(DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL.toString())))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].paymentCreditCard").value(hasItem(DEFAULT_PAYMENT_CREDIT_CARD.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentPointsInUSD").value(hasItem(DEFAULT_PAYMENT_POINTS_IN_USD)))
            .andExpect(jsonPath("$.[*].juniperSalePrice").value(hasItem(DEFAULT_JUNIPER_SALE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].juniperReservationCost").value(hasItem(DEFAULT_JUNIPER_RESERVATION_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].benefitInReservation").value(hasItem(DEFAULT_BENEFIT_IN_RESERVATION.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get the booking
        restBookingMockMvc.perform(get("/api/bookings/{id}", booking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(booking.getId().intValue()))
            .andExpect(jsonPath("$.idTransaction").value(DEFAULT_ID_TRANSACTION.toString()))
            .andExpect(jsonPath("$.idReserveLocatorJuniper").value(DEFAULT_ID_RESERVE_LOCATOR_JUNIPER.toString()))
            .andExpect(jsonPath("$.idReserveLocatorExternal").value(DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL.toString()))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL.toString()))
            .andExpect(jsonPath("$.paymentType").value(DEFAULT_PAYMENT_TYPE.toString()))
            .andExpect(jsonPath("$.paymentCreditCard").value(DEFAULT_PAYMENT_CREDIT_CARD.doubleValue()))
            .andExpect(jsonPath("$.paymentPointsInUSD").value(DEFAULT_PAYMENT_POINTS_IN_USD))
            .andExpect(jsonPath("$.juniperSalePrice").value(DEFAULT_JUNIPER_SALE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.juniperReservationCost").value(DEFAULT_JUNIPER_RESERVATION_COST.doubleValue()))
            .andExpect(jsonPath("$.benefitInReservation").value(DEFAULT_BENEFIT_IN_RESERVATION.doubleValue()));
    }

    @Test
    @Transactional
    public void getAllBookingsByIdTransactionIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idTransaction equals to DEFAULT_ID_TRANSACTION
        defaultBookingShouldBeFound("idTransaction.equals=" + DEFAULT_ID_TRANSACTION);

        // Get all the bookingList where idTransaction equals to UPDATED_ID_TRANSACTION
        defaultBookingShouldNotBeFound("idTransaction.equals=" + UPDATED_ID_TRANSACTION);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdTransactionIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idTransaction in DEFAULT_ID_TRANSACTION or UPDATED_ID_TRANSACTION
        defaultBookingShouldBeFound("idTransaction.in=" + DEFAULT_ID_TRANSACTION + "," + UPDATED_ID_TRANSACTION);

        // Get all the bookingList where idTransaction equals to UPDATED_ID_TRANSACTION
        defaultBookingShouldNotBeFound("idTransaction.in=" + UPDATED_ID_TRANSACTION);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdTransactionIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idTransaction is not null
        defaultBookingShouldBeFound("idTransaction.specified=true");

        // Get all the bookingList where idTransaction is null
        defaultBookingShouldNotBeFound("idTransaction.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorJuniperIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorJuniper equals to DEFAULT_ID_RESERVE_LOCATOR_JUNIPER
        defaultBookingShouldBeFound("idReserveLocatorJuniper.equals=" + DEFAULT_ID_RESERVE_LOCATOR_JUNIPER);

        // Get all the bookingList where idReserveLocatorJuniper equals to UPDATED_ID_RESERVE_LOCATOR_JUNIPER
        defaultBookingShouldNotBeFound("idReserveLocatorJuniper.equals=" + UPDATED_ID_RESERVE_LOCATOR_JUNIPER);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorJuniperIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorJuniper in DEFAULT_ID_RESERVE_LOCATOR_JUNIPER or UPDATED_ID_RESERVE_LOCATOR_JUNIPER
        defaultBookingShouldBeFound("idReserveLocatorJuniper.in=" + DEFAULT_ID_RESERVE_LOCATOR_JUNIPER + "," + UPDATED_ID_RESERVE_LOCATOR_JUNIPER);

        // Get all the bookingList where idReserveLocatorJuniper equals to UPDATED_ID_RESERVE_LOCATOR_JUNIPER
        defaultBookingShouldNotBeFound("idReserveLocatorJuniper.in=" + UPDATED_ID_RESERVE_LOCATOR_JUNIPER);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorJuniperIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorJuniper is not null
        defaultBookingShouldBeFound("idReserveLocatorJuniper.specified=true");

        // Get all the bookingList where idReserveLocatorJuniper is null
        defaultBookingShouldNotBeFound("idReserveLocatorJuniper.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorExternalIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorExternal equals to DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL
        defaultBookingShouldBeFound("idReserveLocatorExternal.equals=" + DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL);

        // Get all the bookingList where idReserveLocatorExternal equals to UPDATED_ID_RESERVE_LOCATOR_EXTERNAL
        defaultBookingShouldNotBeFound("idReserveLocatorExternal.equals=" + UPDATED_ID_RESERVE_LOCATOR_EXTERNAL);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorExternalIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorExternal in DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL or UPDATED_ID_RESERVE_LOCATOR_EXTERNAL
        defaultBookingShouldBeFound("idReserveLocatorExternal.in=" + DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL + "," + UPDATED_ID_RESERVE_LOCATOR_EXTERNAL);

        // Get all the bookingList where idReserveLocatorExternal equals to UPDATED_ID_RESERVE_LOCATOR_EXTERNAL
        defaultBookingShouldNotBeFound("idReserveLocatorExternal.in=" + UPDATED_ID_RESERVE_LOCATOR_EXTERNAL);
    }

    @Test
    @Transactional
    public void getAllBookingsByIdReserveLocatorExternalIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where idReserveLocatorExternal is not null
        defaultBookingShouldBeFound("idReserveLocatorExternal.specified=true");

        // Get all the bookingList where idReserveLocatorExternal is null
        defaultBookingShouldNotBeFound("idReserveLocatorExternal.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByDetailIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where detail equals to DEFAULT_DETAIL
        defaultBookingShouldBeFound("detail.equals=" + DEFAULT_DETAIL);

        // Get all the bookingList where detail equals to UPDATED_DETAIL
        defaultBookingShouldNotBeFound("detail.equals=" + UPDATED_DETAIL);
    }

    @Test
    @Transactional
    public void getAllBookingsByDetailIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where detail in DEFAULT_DETAIL or UPDATED_DETAIL
        defaultBookingShouldBeFound("detail.in=" + DEFAULT_DETAIL + "," + UPDATED_DETAIL);

        // Get all the bookingList where detail equals to UPDATED_DETAIL
        defaultBookingShouldNotBeFound("detail.in=" + UPDATED_DETAIL);
    }

    @Test
    @Transactional
    public void getAllBookingsByDetailIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where detail is not null
        defaultBookingShouldBeFound("detail.specified=true");

        // Get all the bookingList where detail is null
        defaultBookingShouldNotBeFound("detail.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentType equals to DEFAULT_PAYMENT_TYPE
        defaultBookingShouldBeFound("paymentType.equals=" + DEFAULT_PAYMENT_TYPE);

        // Get all the bookingList where paymentType equals to UPDATED_PAYMENT_TYPE
        defaultBookingShouldNotBeFound("paymentType.equals=" + UPDATED_PAYMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentType in DEFAULT_PAYMENT_TYPE or UPDATED_PAYMENT_TYPE
        defaultBookingShouldBeFound("paymentType.in=" + DEFAULT_PAYMENT_TYPE + "," + UPDATED_PAYMENT_TYPE);

        // Get all the bookingList where paymentType equals to UPDATED_PAYMENT_TYPE
        defaultBookingShouldNotBeFound("paymentType.in=" + UPDATED_PAYMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentType is not null
        defaultBookingShouldBeFound("paymentType.specified=true");

        // Get all the bookingList where paymentType is null
        defaultBookingShouldNotBeFound("paymentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentCreditCardIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentCreditCard equals to DEFAULT_PAYMENT_CREDIT_CARD
        defaultBookingShouldBeFound("paymentCreditCard.equals=" + DEFAULT_PAYMENT_CREDIT_CARD);

        // Get all the bookingList where paymentCreditCard equals to UPDATED_PAYMENT_CREDIT_CARD
        defaultBookingShouldNotBeFound("paymentCreditCard.equals=" + UPDATED_PAYMENT_CREDIT_CARD);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentCreditCardIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentCreditCard in DEFAULT_PAYMENT_CREDIT_CARD or UPDATED_PAYMENT_CREDIT_CARD
        defaultBookingShouldBeFound("paymentCreditCard.in=" + DEFAULT_PAYMENT_CREDIT_CARD + "," + UPDATED_PAYMENT_CREDIT_CARD);

        // Get all the bookingList where paymentCreditCard equals to UPDATED_PAYMENT_CREDIT_CARD
        defaultBookingShouldNotBeFound("paymentCreditCard.in=" + UPDATED_PAYMENT_CREDIT_CARD);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentCreditCardIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentCreditCard is not null
        defaultBookingShouldBeFound("paymentCreditCard.specified=true");

        // Get all the bookingList where paymentCreditCard is null
        defaultBookingShouldNotBeFound("paymentCreditCard.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentPointsInUSDIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentPointsInUSD equals to DEFAULT_PAYMENT_POINTS_IN_USD
        defaultBookingShouldBeFound("paymentPointsInUSD.equals=" + DEFAULT_PAYMENT_POINTS_IN_USD);

        // Get all the bookingList where paymentPointsInUSD equals to UPDATED_PAYMENT_POINTS_IN_USD
        defaultBookingShouldNotBeFound("paymentPointsInUSD.equals=" + UPDATED_PAYMENT_POINTS_IN_USD);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentPointsInUSDIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentPointsInUSD in DEFAULT_PAYMENT_POINTS_IN_USD or UPDATED_PAYMENT_POINTS_IN_USD
        defaultBookingShouldBeFound("paymentPointsInUSD.in=" + DEFAULT_PAYMENT_POINTS_IN_USD + "," + UPDATED_PAYMENT_POINTS_IN_USD);

        // Get all the bookingList where paymentPointsInUSD equals to UPDATED_PAYMENT_POINTS_IN_USD
        defaultBookingShouldNotBeFound("paymentPointsInUSD.in=" + UPDATED_PAYMENT_POINTS_IN_USD);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentPointsInUSDIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentPointsInUSD is not null
        defaultBookingShouldBeFound("paymentPointsInUSD.specified=true");

        // Get all the bookingList where paymentPointsInUSD is null
        defaultBookingShouldNotBeFound("paymentPointsInUSD.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentPointsInUSDIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentPointsInUSD greater than or equals to DEFAULT_PAYMENT_POINTS_IN_USD
        defaultBookingShouldBeFound("paymentPointsInUSD.greaterOrEqualThan=" + DEFAULT_PAYMENT_POINTS_IN_USD);

        // Get all the bookingList where paymentPointsInUSD greater than or equals to UPDATED_PAYMENT_POINTS_IN_USD
        defaultBookingShouldNotBeFound("paymentPointsInUSD.greaterOrEqualThan=" + UPDATED_PAYMENT_POINTS_IN_USD);
    }

    @Test
    @Transactional
    public void getAllBookingsByPaymentPointsInUSDIsLessThanSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where paymentPointsInUSD less than or equals to DEFAULT_PAYMENT_POINTS_IN_USD
        defaultBookingShouldNotBeFound("paymentPointsInUSD.lessThan=" + DEFAULT_PAYMENT_POINTS_IN_USD);

        // Get all the bookingList where paymentPointsInUSD less than or equals to UPDATED_PAYMENT_POINTS_IN_USD
        defaultBookingShouldBeFound("paymentPointsInUSD.lessThan=" + UPDATED_PAYMENT_POINTS_IN_USD);
    }


    @Test
    @Transactional
    public void getAllBookingsByJuniperSalePriceIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperSalePrice equals to DEFAULT_JUNIPER_SALE_PRICE
        defaultBookingShouldBeFound("juniperSalePrice.equals=" + DEFAULT_JUNIPER_SALE_PRICE);

        // Get all the bookingList where juniperSalePrice equals to UPDATED_JUNIPER_SALE_PRICE
        defaultBookingShouldNotBeFound("juniperSalePrice.equals=" + UPDATED_JUNIPER_SALE_PRICE);
    }

    @Test
    @Transactional
    public void getAllBookingsByJuniperSalePriceIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperSalePrice in DEFAULT_JUNIPER_SALE_PRICE or UPDATED_JUNIPER_SALE_PRICE
        defaultBookingShouldBeFound("juniperSalePrice.in=" + DEFAULT_JUNIPER_SALE_PRICE + "," + UPDATED_JUNIPER_SALE_PRICE);

        // Get all the bookingList where juniperSalePrice equals to UPDATED_JUNIPER_SALE_PRICE
        defaultBookingShouldNotBeFound("juniperSalePrice.in=" + UPDATED_JUNIPER_SALE_PRICE);
    }

    @Test
    @Transactional
    public void getAllBookingsByJuniperSalePriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperSalePrice is not null
        defaultBookingShouldBeFound("juniperSalePrice.specified=true");

        // Get all the bookingList where juniperSalePrice is null
        defaultBookingShouldNotBeFound("juniperSalePrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByJuniperReservationCostIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperReservationCost equals to DEFAULT_JUNIPER_RESERVATION_COST
        defaultBookingShouldBeFound("juniperReservationCost.equals=" + DEFAULT_JUNIPER_RESERVATION_COST);

        // Get all the bookingList where juniperReservationCost equals to UPDATED_JUNIPER_RESERVATION_COST
        defaultBookingShouldNotBeFound("juniperReservationCost.equals=" + UPDATED_JUNIPER_RESERVATION_COST);
    }

    @Test
    @Transactional
    public void getAllBookingsByJuniperReservationCostIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperReservationCost in DEFAULT_JUNIPER_RESERVATION_COST or UPDATED_JUNIPER_RESERVATION_COST
        defaultBookingShouldBeFound("juniperReservationCost.in=" + DEFAULT_JUNIPER_RESERVATION_COST + "," + UPDATED_JUNIPER_RESERVATION_COST);

        // Get all the bookingList where juniperReservationCost equals to UPDATED_JUNIPER_RESERVATION_COST
        defaultBookingShouldNotBeFound("juniperReservationCost.in=" + UPDATED_JUNIPER_RESERVATION_COST);
    }

    @Test
    @Transactional
    public void getAllBookingsByJuniperReservationCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where juniperReservationCost is not null
        defaultBookingShouldBeFound("juniperReservationCost.specified=true");

        // Get all the bookingList where juniperReservationCost is null
        defaultBookingShouldNotBeFound("juniperReservationCost.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByBenefitInReservationIsEqualToSomething() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where benefitInReservation equals to DEFAULT_BENEFIT_IN_RESERVATION
        defaultBookingShouldBeFound("benefitInReservation.equals=" + DEFAULT_BENEFIT_IN_RESERVATION);

        // Get all the bookingList where benefitInReservation equals to UPDATED_BENEFIT_IN_RESERVATION
        defaultBookingShouldNotBeFound("benefitInReservation.equals=" + UPDATED_BENEFIT_IN_RESERVATION);
    }

    @Test
    @Transactional
    public void getAllBookingsByBenefitInReservationIsInShouldWork() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where benefitInReservation in DEFAULT_BENEFIT_IN_RESERVATION or UPDATED_BENEFIT_IN_RESERVATION
        defaultBookingShouldBeFound("benefitInReservation.in=" + DEFAULT_BENEFIT_IN_RESERVATION + "," + UPDATED_BENEFIT_IN_RESERVATION);

        // Get all the bookingList where benefitInReservation equals to UPDATED_BENEFIT_IN_RESERVATION
        defaultBookingShouldNotBeFound("benefitInReservation.in=" + UPDATED_BENEFIT_IN_RESERVATION);
    }

    @Test
    @Transactional
    public void getAllBookingsByBenefitInReservationIsNullOrNotNull() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList where benefitInReservation is not null
        defaultBookingShouldBeFound("benefitInReservation.specified=true");

        // Get all the bookingList where benefitInReservation is null
        defaultBookingShouldNotBeFound("benefitInReservation.specified=false");
    }

    @Test
    @Transactional
    public void getAllBookingsByCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        Company company = CompanyResourceIntTest.createEntity(em);
        em.persist(company);
        em.flush();
        booking.setCompany(company);
        bookingRepository.saveAndFlush(booking);
        Long companyId = company.getId();

        // Get all the bookingList where company equals to companyId
        defaultBookingShouldBeFound("companyId.equals=" + companyId);

        // Get all the bookingList where company equals to companyId + 1
        defaultBookingShouldNotBeFound("companyId.equals=" + (companyId + 1));
    }


    @Test
    @Transactional
    public void getAllBookingsByProductsIsEqualToSomething() throws Exception {
        // Initialize the database
        ProductByBooking products = ProductByBookingResourceIntTest.createEntity(em);
        em.persist(products);
        em.flush();
        booking.addProducts(products);
        bookingRepository.saveAndFlush(booking);
        Long productsId = products.getId();

        // Get all the bookingList where products equals to productsId
        defaultBookingShouldBeFound("productsId.equals=" + productsId);

        // Get all the bookingList where products equals to productsId + 1
        defaultBookingShouldNotBeFound("productsId.equals=" + (productsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBookingShouldBeFound(String filter) throws Exception {
        restBookingMockMvc.perform(get("/api/bookings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booking.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTransaction").value(hasItem(DEFAULT_ID_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].idReserveLocatorJuniper").value(hasItem(DEFAULT_ID_RESERVE_LOCATOR_JUNIPER.toString())))
            .andExpect(jsonPath("$.[*].idReserveLocatorExternal").value(hasItem(DEFAULT_ID_RESERVE_LOCATOR_EXTERNAL.toString())))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].paymentCreditCard").value(hasItem(DEFAULT_PAYMENT_CREDIT_CARD.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentPointsInUSD").value(hasItem(DEFAULT_PAYMENT_POINTS_IN_USD)))
            .andExpect(jsonPath("$.[*].juniperSalePrice").value(hasItem(DEFAULT_JUNIPER_SALE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].juniperReservationCost").value(hasItem(DEFAULT_JUNIPER_RESERVATION_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].benefitInReservation").value(hasItem(DEFAULT_BENEFIT_IN_RESERVATION.doubleValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBookingShouldNotBeFound(String filter) throws Exception {
        restBookingMockMvc.perform(get("/api/bookings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingBooking() throws Exception {
        // Get the booking
        restBookingMockMvc.perform(get("/api/bookings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // Update the booking
        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();
        // Disconnect from session so that the updates on updatedBooking are not directly saved in db
        em.detach(updatedBooking);
        updatedBooking
            .idTransaction(UPDATED_ID_TRANSACTION)
            .idReserveLocatorJuniper(UPDATED_ID_RESERVE_LOCATOR_JUNIPER)
            .idReserveLocatorExternal(UPDATED_ID_RESERVE_LOCATOR_EXTERNAL)
            .detail(UPDATED_DETAIL)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentCreditCard(UPDATED_PAYMENT_CREDIT_CARD)
            .paymentPointsInUSD(UPDATED_PAYMENT_POINTS_IN_USD)
            .juniperSalePrice(UPDATED_JUNIPER_SALE_PRICE)
            .juniperReservationCost(UPDATED_JUNIPER_RESERVATION_COST)
            .benefitInReservation(UPDATED_BENEFIT_IN_RESERVATION);
        BookingDTO bookingDTO = bookingMapper.toDto(updatedBooking);

        restBookingMockMvc.perform(put("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isOk());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getIdTransaction()).isEqualTo(UPDATED_ID_TRANSACTION);
        assertThat(testBooking.getIdReserveLocatorJuniper()).isEqualTo(UPDATED_ID_RESERVE_LOCATOR_JUNIPER);
        assertThat(testBooking.getIdReserveLocatorExternal()).isEqualTo(UPDATED_ID_RESERVE_LOCATOR_EXTERNAL);
        assertThat(testBooking.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testBooking.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testBooking.getPaymentCreditCard()).isEqualTo(UPDATED_PAYMENT_CREDIT_CARD);
        assertThat(testBooking.getPaymentPointsInUSD()).isEqualTo(UPDATED_PAYMENT_POINTS_IN_USD);
        assertThat(testBooking.getJuniperSalePrice()).isEqualTo(UPDATED_JUNIPER_SALE_PRICE);
        assertThat(testBooking.getJuniperReservationCost()).isEqualTo(UPDATED_JUNIPER_RESERVATION_COST);
        assertThat(testBooking.getBenefitInReservation()).isEqualTo(UPDATED_BENEFIT_IN_RESERVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingBooking() throws Exception {
        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // Create the Booking
        BookingDTO bookingDTO = bookingMapper.toDto(booking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restBookingMockMvc.perform(put("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        int databaseSizeBeforeDelete = bookingRepository.findAll().size();

        // Get the booking
        restBookingMockMvc.perform(delete("/api/bookings/{id}", booking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Booking.class);
        Booking booking1 = new Booking();
        booking1.setId(1L);
        Booking booking2 = new Booking();
        booking2.setId(booking1.getId());
        assertThat(booking1).isEqualTo(booking2);
        booking2.setId(2L);
        assertThat(booking1).isNotEqualTo(booking2);
        booking1.setId(null);
        assertThat(booking1).isNotEqualTo(booking2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingDTO.class);
        BookingDTO bookingDTO1 = new BookingDTO();
        bookingDTO1.setId(1L);
        BookingDTO bookingDTO2 = new BookingDTO();
        assertThat(bookingDTO1).isNotEqualTo(bookingDTO2);
        bookingDTO2.setId(bookingDTO1.getId());
        assertThat(bookingDTO1).isEqualTo(bookingDTO2);
        bookingDTO2.setId(2L);
        assertThat(bookingDTO1).isNotEqualTo(bookingDTO2);
        bookingDTO1.setId(null);
        assertThat(bookingDTO1).isNotEqualTo(bookingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bookingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bookingMapper.fromId(null)).isNull();
    }
}
