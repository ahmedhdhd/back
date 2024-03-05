package com.isimm.project.repository;

import com.isimm.project.classes.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Enseignant SET matiere_enseignee = :matier WHERE id= :id",nativeQuery= true)
    public void addmatier(@Param("id") Long id, @Param("matier") List<String> matier);
}