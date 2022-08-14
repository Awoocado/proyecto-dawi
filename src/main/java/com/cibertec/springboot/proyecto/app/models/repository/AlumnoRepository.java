package com.cibertec.springboot.proyecto.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.springboot.proyecto.app.models.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	@Query(value = "SELECT * FROM alumnos where grado_id = ?1", nativeQuery = true)
	List<Alumno> findByGrado(Integer Grado);
}
