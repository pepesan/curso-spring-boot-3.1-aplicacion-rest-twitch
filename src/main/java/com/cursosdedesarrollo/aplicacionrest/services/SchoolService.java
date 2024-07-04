package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import com.cursosdedesarrollo.aplicacionrest.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.cursosdedesarrollo.aplicacionrest.domain.School;

import java.util.Date;
import java.util.List;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    @Autowired
    SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAll(){
        return this.schoolRepository.findByActive(true);
    }

    public School save(SchoolCreateUpdateDTO schoolDTO) {
        School school = new School(schoolDTO);
        school = this.schoolRepository.save(school);
        return school;
    }
    public Page<School> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.schoolRepository.findAll(pageable);
    }

    public School findById(Long id){
        return this.schoolRepository
                .findByActiveTrueAndId(id)
                .stream()
                .findFirst()
                .orElse(new School());
    }

    public School update(SchoolCreateUpdateDTO schoolDTO, Long id){
        School savedSchool = this.findById(id);
        if (savedSchool.getId() !=null){
            savedSchool.apply(schoolDTO);
            savedSchool.setUpdatedAt(new Date());
            this.schoolRepository.save(savedSchool);
            return savedSchool;
        }else {
            return new School();
        }
    }

    public School deleteById(Long id) {
        School savedSchool = this.findById(id);
        if (savedSchool.getId() !=null){
            savedSchool.setUpdatedAt(new Date());
            savedSchool.setActive(false);
            this.schoolRepository.save(savedSchool);
            return savedSchool;
        }else{
            return new School();
        }
    }
}
