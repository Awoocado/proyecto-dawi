package com.cibertec.springboot.proyecto.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.springboot.proyecto.app.models.entity.Curso;
import com.cibertec.springboot.proyecto.app.models.entity.Nota;
import com.cibertec.springboot.proyecto.app.models.repository.AlumnoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.CursoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.GradoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.NotaRepository;

@Controller
@RequestMapping("nota")
@SessionAttributes("nota")
public class NotaController {

	@Autowired
	private NotaRepository notaRepo;
	@Autowired
	private AlumnoRepository alumnoRepo;
	@Autowired
	private CursoRepository cursoRepo;

	@Autowired
	private GradoRepository gradoRepo;

	@GetMapping({ "", "/", "index" })
	public String index() {
		return "redirect:/nota/listar";
	}

	@GetMapping("listar")
	public String listar(Model model) {
		List<Curso> cursos = cursoRepo.findAll();

		model.addAttribute("titulo", "Listar Notas");
		model.addAttribute("curso", new Curso());
		model.addAttribute("cursos", cursos);
		return "nota/listar";
	}

	@PostMapping("/listar/{id}")
	public String grados(@PathVariable Integer id, Model model) {
		Curso curso = cursoRepo.findById(id).get();
		model.addAttribute("grados");
		return "nota/listar";
	}

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("titulo", "Crear Nota");
		model.addAttribute("nota", new Nota());
		model.addAttribute("cursos", (List<Curso>) cursoRepo.findAll());

		return "nota/form";
	}

	@GetMapping("form/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("titulo", "Editar Nota");
		model.addAttribute("nota", notaRepo.findById(id).get());
		model.addAttribute("cursos", (List<Curso>) cursoRepo.findAll());
		return "nota/form";
	}

	@PostMapping("form")
	public String guardar(Nota nota) {
		notaRepo.save(nota);
		return "redirect:";
	}
}