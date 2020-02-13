package io.github.e1s.components.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewsRepository extends JpaRepository<Views, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Views SET counter =(counter + 1) WHERE id =:id")
    void findByIdAndIncreaseViewsByOne(@Param("id") Long id);
}
