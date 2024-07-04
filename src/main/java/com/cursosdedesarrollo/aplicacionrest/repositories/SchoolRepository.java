package com.cursosdedesarrollo.aplicacionrest.repositories;

import com.cursosdedesarrollo.aplicacionrest.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findByActive(Boolean activeState);
    List<School> findByName(String name);

    // @Query(value = "from School where name=?1 and  active=true")
    // List<School> findByNameAndActiveTrue(String name);

    List<School> findByNameAndActiveTrue(String name);
}
