package ar.com.intelimanagement.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(ar.com.intelimanagement.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Company.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Company.class.getName() + ".users", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Company.class.getName() + ".bookings", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Company.class.getName() + ".providers", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Company.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Address.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Approvals.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Approvals.class.getName() + ".history", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Country.class.getName() + ".provinces", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Province.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.User.class.getName() + ".phones", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.User.class.getName() + ".notifications", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.User.class.getName() + ".team", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Phone.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Booking.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Booking.class.getName()+ ".variations", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Provider.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Notification.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Variation.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.ApprovalHistory.class.getName(), jcacheConfiguration);
            //cm.createCache(ar.com.intelimanagement.domain.Variation.class.getName() + ".relationshipProviderVariations", jcacheConfiguration);
            //cm.createCache(ar.com.intelimanagement.domain.Variation.class.getName() + ".relationshipProductVariations", jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.ProductByBooking.class.getName(), jcacheConfiguration);
            cm.createCache(ar.com.intelimanagement.domain.Booking.class.getName() + ".products", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
