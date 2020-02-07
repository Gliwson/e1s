package io.github.e1s.e1s.repository;

import io.github.e1s.e1s.domain.Discount;
import io.github.e1s.e1s.domain.enums.TypeMaleFemaleKid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByType(@Param("type") TypeMaleFemaleKid string);

}
