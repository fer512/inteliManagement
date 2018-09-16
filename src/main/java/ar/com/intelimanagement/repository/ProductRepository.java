package ar.com.intelimanagement.repository;

import ar.com.intelimanagement.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query(value = "select distinct product from Product product left join fetch product.product_by_providers",
        countQuery = "select count(distinct product) from Product product")
    Page<Product> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct product from Product product left join fetch product.product_by_providers")
    List<Product> findAllWithEagerRelationships();

    @Query("select product from Product product left join fetch product.product_by_providers where product.id =:id")
    Optional<Product> findOneWithEagerRelationships(@Param("id") Long id);

}
