package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import com.cursosdedesarrollo.aplicacionrest.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.cursosdedesarrollo.aplicacionrest.domain.School;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    @Autowired
    SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAll(){
        return this.schoolRepository.findAll();
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
        return this.schoolRepository.findById(id).orElse(new School());
    }

    public School update(SchoolCreateUpdateDTO schoolDTO, Long id){
        Optional<School> schoolOptional = this.schoolRepository.findById(id);
        if (schoolOptional.isPresent()){
            School savedSchool = schoolOptional.get();
            savedSchool.apply(schoolDTO);
            this.schoolRepository.save(savedSchool);
            return savedSchool;
        }else {
            return new School();
        }
    }

}
