package ar.com.intelimanagement.web.rest;

import ar.com.intelimanagement.InteliManagementApp;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.repository.ApprovalsRepository;
import ar.com.intelimanagement.service.ApprovalsService;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.mapper.ApprovalsMapper;
import ar.com.intelimanagement.web.rest.errors.ExceptionTranslator;
import ar.com.intelimanagement.service.dto.ApprovalsCriteria;
import ar.com.intelimanagement.service.ApprovalsQueryService;

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
import java.time.temporal.ChronoUnit;
import java.util.List;


import static ar.com.intelimanagement.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
/**
 * Test class for the ApprovalsResource REST controller.
 *
 * @see ApprovalsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InteliManagementApp.class)
public class ApprovalsResourceIntTest {

    private static final Instant DEFAULT_STAST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STAST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ApprovalsStatusType DEFAULT_STATUS = ApprovalsStatusType.APPOVED;
    private static final ApprovalsStatusType UPDATED_STATUS = ApprovalsStatusType.REJECTED;

    @Autowired
    private ApprovalsRepository approvalsRepository;


    @Autowired
    private ApprovalsMapper approvalsMapper;
    

    @Autowired
    private ApprovalsService approvalsService;

    @Autowired
    private ApprovalsQueryService approvalsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restApprovalsMockMvc;

    private Approvals approvals;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApprovalsResource approvalsResource = new ApprovalsResource(approvalsService, approvalsQueryService);
        this.restApprovalsMockMvc = MockMvcBuilders.standaloneSetup(approvalsResource)
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
    public static Approvals createEntity(EntityManager em) {
        Approvals approvals = new Approvals()
            .stastDate(DEFAULT_STAST_DATE)
            .endDate(DEFAULT_END_DATE)
            .status(DEFAULT_STATUS);
        return approvals;
    }

    @Before
    public void initTest() {
        approvals = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprovals() throws Exception {
        int databaseSizeBeforeCreate = approvalsRepository.findAll().size();

        // Create the Approvals
        ApprovalsDTO approvalsDTO = approvalsMapper.toDto(approvals);
        restApprovalsMockMvc.perform(post("/api/approvals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalsDTO)))
            .andExpect(status().isCreated());

        // Validate the Approvals in the database
        List<Approvals> approvalsList = approvalsRepository.findAll();
        assertThat(approvalsList).hasSize(databaseSizeBeforeCreate + 1);
        Approvals testApprovals = approvalsList.get(approvalsList.size() - 1);
        assertThat(testApprovals.getStastDate()).isEqualTo(DEFAULT_STAST_DATE);
        assertThat(testApprovals.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testApprovals.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createApprovalsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = approvalsRepository.findAll().size();

        // Create the Approvals with an existing ID
        approvals.setId(1L);
        ApprovalsDTO approvalsDTO = approvalsMapper.toDto(approvals);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprovalsMockMvc.perform(post("/api/approvals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Approvals in the database
        List<Approvals> approvalsList = approvalsRepository.findAll();
        assertThat(approvalsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllApprovals() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList
        restApprovalsMockMvc.perform(get("/api/approvals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvals.getId().intValue())))
            .andExpect(jsonPath("$.[*].stastDate").value(hasItem(DEFAULT_STAST_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    

    @Test
    @Transactional
    public void getApprovals() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get the approvals
        restApprovalsMockMvc.perform(get("/api/approvals/{id}", approvals.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(approvals.getId().intValue()))
            .andExpect(jsonPath("$.stastDate").value(DEFAULT_STAST_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getAllApprovalsByStastDateIsEqualToSomething() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where stastDate equals to DEFAULT_STAST_DATE
        defaultApprovalsShouldBeFound("stastDate.equals=" + DEFAULT_STAST_DATE);

        // Get all the approvalsList where stastDate equals to UPDATED_STAST_DATE
        defaultApprovalsShouldNotBeFound("stastDate.equals=" + UPDATED_STAST_DATE);
    }

    @Test
    @Transactional
    public void getAllApprovalsByStastDateIsInShouldWork() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where stastDate in DEFAULT_STAST_DATE or UPDATED_STAST_DATE
        defaultApprovalsShouldBeFound("stastDate.in=" + DEFAULT_STAST_DATE + "," + UPDATED_STAST_DATE);

        // Get all the approvalsList where stastDate equals to UPDATED_STAST_DATE
        defaultApprovalsShouldNotBeFound("stastDate.in=" + UPDATED_STAST_DATE);
    }

    @Test
    @Transactional
    public void getAllApprovalsByStastDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where stastDate is not null
        defaultApprovalsShouldBeFound("stastDate.specified=true");

        // Get all the approvalsList where stastDate is null
        defaultApprovalsShouldNotBeFound("stastDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllApprovalsByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where endDate equals to DEFAULT_END_DATE
        defaultApprovalsShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the approvalsList where endDate equals to UPDATED_END_DATE
        defaultApprovalsShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllApprovalsByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultApprovalsShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the approvalsList where endDate equals to UPDATED_END_DATE
        defaultApprovalsShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllApprovalsByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where endDate is not null
        defaultApprovalsShouldBeFound("endDate.specified=true");

        // Get all the approvalsList where endDate is null
        defaultApprovalsShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllApprovalsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where status equals to DEFAULT_STATUS
        defaultApprovalsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the approvalsList where status equals to UPDATED_STATUS
        defaultApprovalsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllApprovalsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultApprovalsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the approvalsList where status equals to UPDATED_STATUS
        defaultApprovalsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllApprovalsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        // Get all the approvalsList where status is not null
        defaultApprovalsShouldBeFound("status.specified=true");

        // Get all the approvalsList where status is null
        defaultApprovalsShouldNotBeFound("status.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultApprovalsShouldBeFound(String filter) throws Exception {
        restApprovalsMockMvc.perform(get("/api/approvals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvals.getId().intValue())))
            .andExpect(jsonPath("$.[*].stastDate").value(hasItem(DEFAULT_STAST_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultApprovalsShouldNotBeFound(String filter) throws Exception {
        restApprovalsMockMvc.perform(get("/api/approvals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingApprovals() throws Exception {
        // Get the approvals
        restApprovalsMockMvc.perform(get("/api/approvals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprovals() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        int databaseSizeBeforeUpdate = approvalsRepository.findAll().size();

        // Update the approvals
        Approvals updatedApprovals = approvalsRepository.findById(approvals.getId()).get();
        // Disconnect from session so that the updates on updatedApprovals are not directly saved in db
        em.detach(updatedApprovals);
        updatedApprovals
            .stastDate(UPDATED_STAST_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS);
        ApprovalsDTO approvalsDTO = approvalsMapper.toDto(updatedApprovals);

        restApprovalsMockMvc.perform(put("/api/approvals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalsDTO)))
            .andExpect(status().isOk());

        // Validate the Approvals in the database
        List<Approvals> approvalsList = approvalsRepository.findAll();
        assertThat(approvalsList).hasSize(databaseSizeBeforeUpdate);
        Approvals testApprovals = approvalsList.get(approvalsList.size() - 1);
        assertThat(testApprovals.getStastDate()).isEqualTo(UPDATED_STAST_DATE);
        assertThat(testApprovals.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testApprovals.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingApprovals() throws Exception {
        int databaseSizeBeforeUpdate = approvalsRepository.findAll().size();

        // Create the Approvals
        ApprovalsDTO approvalsDTO = approvalsMapper.toDto(approvals);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restApprovalsMockMvc.perform(put("/api/approvals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Approvals in the database
        List<Approvals> approvalsList = approvalsRepository.findAll();
        assertThat(approvalsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApprovals() throws Exception {
        // Initialize the database
        approvalsRepository.saveAndFlush(approvals);

        int databaseSizeBeforeDelete = approvalsRepository.findAll().size();

        // Get the approvals
        restApprovalsMockMvc.perform(delete("/api/approvals/{id}", approvals.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Approvals> approvalsList = approvalsRepository.findAll();
        assertThat(approvalsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Approvals.class);
        Approvals approvals1 = new Approvals();
        approvals1.setId(1L);
        Approvals approvals2 = new Approvals();
        approvals2.setId(approvals1.getId());
        assertThat(approvals1).isEqualTo(approvals2);
        approvals2.setId(2L);
        assertThat(approvals1).isNotEqualTo(approvals2);
        approvals1.setId(null);
        assertThat(approvals1).isNotEqualTo(approvals2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApprovalsDTO.class);
        ApprovalsDTO approvalsDTO1 = new ApprovalsDTO();
        approvalsDTO1.setId(1L);
        ApprovalsDTO approvalsDTO2 = new ApprovalsDTO();
        assertThat(approvalsDTO1).isNotEqualTo(approvalsDTO2);
        approvalsDTO2.setId(approvalsDTO1.getId());
        assertThat(approvalsDTO1).isEqualTo(approvalsDTO2);
        approvalsDTO2.setId(2L);
        assertThat(approvalsDTO1).isNotEqualTo(approvalsDTO2);
        approvalsDTO1.setId(null);
        assertThat(approvalsDTO1).isNotEqualTo(approvalsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(approvalsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(approvalsMapper.fromId(null)).isNull();
    }
}
