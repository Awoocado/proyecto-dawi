package com.cibertec.springboot.proyecto.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.springboot.proyecto.app.models.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
