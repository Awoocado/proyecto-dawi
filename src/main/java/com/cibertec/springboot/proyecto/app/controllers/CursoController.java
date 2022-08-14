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
import com.cibertec.springboot.proyecto.app.models.entity.Grado;
import com.cibertec.springboot.proyecto.app.models.repository.CursoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.GradoRepository;

@Controller
@RequestMapping("curso")
@SessionAttributes("curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepo;

	@Autowired
	private GradoRepository gradoRepo;

	@GetMapping({ "", "/", "index", "listar" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Listar Cursos");
		model.addAttribute("cursos", cursoRepo.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return "curso/listar";
	}

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("titulo", "Crear Curso");
		model.addAttribute("curso", new Curso());
		model.addAttribute("grados", (List<Grado>) gradoRepo.findAll());

		return "curso/form";
	}

	@GetMapping("form/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("titulo", "Editar Curso");
		model.addAttribute("curso", cursoRepo.findById(id).get());
		model.addAttribute("grados", (List<Grado>) gradoRepo.findAll());
		return "curso/form";
	}

	@PostMapping("form")
	public String guardar(Curso curso) {
		cursoRepo.save(curso);
		return "redirect:";
	}
}
