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

import com.cibertec.springboot.proyecto.app.models.entity.Alumno;
import com.cibertec.springboot.proyecto.app.models.entity.Grado;
import com.cibertec.springboot.proyecto.app.models.repository.AlumnoRepository;
import com.cibertec.springboot.proyecto.app.models.repository.GradoRepository;

@Controller
@RequestMapping("alumno")
@SessionAttributes("alumno")
public class AlumnoController {

	@Autowired
	private AlumnoRepository alumnoRepo;
	@Autowired
	private GradoRepository gradoRepo;

	@GetMapping({ "", "/", "index", "listar" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Listar Alumnos");
		model.addAttribute("alumnos", alumnoRepo.findAll(Sort.by(Sort.Direction.ASC, "apellidos")));
		return "alumno/listar";
	}

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("titulo", "Crear Alumno");
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("grados", (List<Grado>) gradoRepo.findAll());

		return "alumno/form";
	}

	@GetMapping("form/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("titulo", "Editar Grado");
		model.addAttribute("alumno", alumnoRepo.findById(id).get());
		model.addAttribute("grados", (List<Grado>) gradoRepo.findAll());
		return "alumno/form";
	}

	@PostMapping("form")
	public String guardar(Alumno alumno) {
		alumnoRepo.save(alumno);
		return "redirect:";
	}

}
