package com.cibertec.springboot.proyecto.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.springboot.proyecto.app.models.entity.Curso;
import com.cibertec.springboot.proyecto.app.models.entity.Profesor;
import com.cibertec.springboot.proyecto.app.models.repository.CursoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.ProfesorRepository;

@Controller
@RequestMapping("profesor")
@SessionAttributes("profesor")
public class ProfesorController {

	@Autowired
	private ProfesorRepository profesorRepo;
	@Autowired
	private CursoRepository cursoRepo;

	@GetMapping({ "", "/", "index", "listar" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Listar Profesores");
		model.addAttribute("profesores", profesorRepo.findAll(Sort.by(Sort.Direction.ASC, "apellidos")));
		return "profesor/listar";
	}

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("titulo", "Crear Profesor");
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("cursos", (List<Curso>) cursoRepo.findAll());

		return "profesor/form";
	}

	@GetMapping("form/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("titulo", "Editar Profesor");
		model.addAttribute("profesor", profesorRepo.findById(id).get());
		model.addAttribute("cursos", (List<Curso>) cursoRepo.findAll());
		return "profesor/form";
	}

	@PostMapping("form")
	public String guardar(Profesor profesor) {
		profesorRepo.save(profesor);
		return "redirect:";
	}
}