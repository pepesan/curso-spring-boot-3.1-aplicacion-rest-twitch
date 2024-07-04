package com.cursosdedesarrollo.aplicacionrest.repositories;

import com.cursosdedesarrollo.aplicacionrest.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query(name = "School.findByName")
    School findByName(@Param("name") String name);

    List<School> findByActive(Boolean activeState);

    // @Query(value = "from School where name=?1 and  active=true")
    // List<School> findByNameAndActiveTrue(String name);

    List<School> findByNameAndActiveTrue(String name);
    List<School> findByActiveTrueAndId(Long id);

}
