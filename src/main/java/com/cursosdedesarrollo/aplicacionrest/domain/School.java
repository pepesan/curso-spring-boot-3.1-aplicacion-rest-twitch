package com.cursosdedesarrollo.aplicacionrest.domain;

import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Entity
@Data
@Table(name = "SCHOOLS", indexes = {
        @Index(name = "idx_school_active", columnList = "active")
})
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "School.findByName",
                query = "SELECT s FROM School s WHERE s.name = :name"
        )
})
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    @NotNull
    @NotBlank
    private String name;

    @PositiveOrZero
    private Long alumnSize;

    @Size(max = 50)
    private String address;

    @PastOrPresent
    private Date createdAt;
    @PastOrPresent
    private Date updatedAt;

    private Boolean active;




    public School(SchoolCreateUpdateDTO schoolDTO) {
        this.apply(schoolDTO);
    }

    public void apply(SchoolCreateUpdateDTO schoolDTO) {
        this.name= schoolDTO.getName();
        this.address = schoolDTO.getAddress();
        this.alumnSize = schoolDTO.getAlumnSize();
        this.active = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
