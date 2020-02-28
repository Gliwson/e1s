package io.github.e1s.components.discount.scope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DiscountScopeRepository extends JpaRepository<DiscountScope, Long> {

    @Query(value = "SELECT ds.percent from DiscountScope ds " +
            "where :price >= ds.firsNumber and :price < ds.secondNumber or " +
            ":price >= ds.firsNumber and  ds.secondNumber is null or " +
            ":price < ds.secondNumber and ds.firsNumber is null")
    List<Long> findwszystko(@Param("price") BigDecimal price);

}
