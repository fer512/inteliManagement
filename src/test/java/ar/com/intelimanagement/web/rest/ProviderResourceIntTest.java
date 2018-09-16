package ar.com.intelimanagement.web.rest;

import ar.com.intelimanagement.InteliManagementApp;

import ar.com.intelimanagement.domain.Provider;
import ar.com.intelimanagement.domain.Address;
import ar.com.intelimanagement.domain.Company;
import ar.com.intelimanagement.repository.ProviderRepository;
import ar.com.intelimanagement.service.ProviderService;
import ar.com.intelimanagement.service.dto.ProviderDTO;
import ar.com.intelimanagement.service.mapper.ProviderMapper;
import ar.com.intelimanagement.web.rest.errors.ExceptionTranslator;
import ar.com.intelimanagement.service.dto.ProviderCriteria;
import ar.com.intelimanagement.service.ProviderQueryService;

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
 * Test class for the ProviderResource REST controller.
 *
 * @see ProviderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InteliManagementApp.class)
public class ProviderResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ProviderRepository providerRepository;


    @Autowired
    private ProviderMapper providerMapper;
    

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderQueryService providerQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProviderMockMvc;

    private Provider provider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProviderResource providerResource = new ProviderResource(providerService, providerQueryService);
        this.restProviderMockMvc = MockMvcBuilders.standaloneSetup(providerResource)
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
    public static Provider createEntity(EntityManager em) {
        Provider provider = new Provider()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL);
        return provider;
    }

    @Before
    public void initTest() {
        provider = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvider() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isCreated());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate + 1);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProvider.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createProviderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider with an existing ID
        provider.setId(1L);
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProviders() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provider.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }
    

    @Test
    @Transactional
    public void getProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", provider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provider.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getAllProvidersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where name equals to DEFAULT_NAME
        defaultProviderShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the providerList where name equals to UPDATED_NAME
        defaultProviderShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProvidersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultProviderShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the providerList where name equals to UPDATED_NAME
        defaultProviderShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProvidersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where name is not null
        defaultProviderShouldBeFound("name.specified=true");

        // Get all the providerList where name is null
        defaultProviderShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllProvidersByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where email equals to DEFAULT_EMAIL
        defaultProviderShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the providerList where email equals to UPDATED_EMAIL
        defaultProviderShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProvidersByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultProviderShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the providerList where email equals to UPDATED_EMAIL
        defaultProviderShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProvidersByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList where email is not null
        defaultProviderShouldBeFound("email.specified=true");

        // Get all the providerList where email is null
        defaultProviderShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllProvidersByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        Address address = AddressResourceIntTest.createEntity(em);
        em.persist(address);
        em.flush();
        provider.setAddress(address);
        providerRepository.saveAndFlush(provider);
        Long addressId = address.getId();

        // Get all the providerList where address equals to addressId
        defaultProviderShouldBeFound("addressId.equals=" + addressId);

        // Get all the providerList where address equals to addressId + 1
        defaultProviderShouldNotBeFound("addressId.equals=" + (addressId + 1));
    }


    @Test
    @Transactional
    public void getAllProvidersByCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        Company company = CompanyResourceIntTest.createEntity(em);
        em.persist(company);
        em.flush();
        provider.setCompany(company);
        providerRepository.saveAndFlush(provider);
        Long companyId = company.getId();

        // Get all the providerList where company equals to companyId
        defaultProviderShouldBeFound("companyId.equals=" + companyId);

        // Get all the providerList where company equals to companyId + 1
        defaultProviderShouldNotBeFound("companyId.equals=" + (companyId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProviderShouldBeFound(String filter) throws Exception {
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provider.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProviderShouldNotBeFound(String filter) throws Exception {
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingProvider() throws Exception {
        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Update the provider
        Provider updatedProvider = providerRepository.findById(provider.getId()).get();
        // Disconnect from session so that the updates on updatedProvider are not directly saved in db
        em.detach(updatedProvider);
        updatedProvider
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL);
        ProviderDTO providerDTO = providerMapper.toDto(updatedProvider);

        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isOk());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProvider.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingProvider() throws Exception {
        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeDelete = providerRepository.findAll().size();

        // Get the provider
        restProviderMockMvc.perform(delete("/api/providers/{id}", provider.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provider.class);
        Provider provider1 = new Provider();
        provider1.setId(1L);
        Provider provider2 = new Provider();
        provider2.setId(provider1.getId());
        assertThat(provider1).isEqualTo(provider2);
        provider2.setId(2L);
        assertThat(provider1).isNotEqualTo(provider2);
        provider1.setId(null);
        assertThat(provider1).isNotEqualTo(provider2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderDTO.class);
        ProviderDTO providerDTO1 = new ProviderDTO();
        providerDTO1.setId(1L);
        ProviderDTO providerDTO2 = new ProviderDTO();
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
        providerDTO2.setId(providerDTO1.getId());
        assertThat(providerDTO1).isEqualTo(providerDTO2);
        providerDTO2.setId(2L);
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
        providerDTO1.setId(null);
        assertThat(providerDTO1).isNotEqualTo(providerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(providerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(providerMapper.fromId(null)).isNull();
    }
}
