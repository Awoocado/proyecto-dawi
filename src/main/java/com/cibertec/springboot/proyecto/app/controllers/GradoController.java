package com.cibertec.springboot.proyecto.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.springboot.proyecto.app.models.entity.Grado;
import com.cibertec.springboot.proyecto.app.models.repository.GradoRepository;

@Controller
@RequestMapping("grado")
@SessionAttributes("grado")
public class GradoController {
	@Autowired
	private GradoRepository gradoRepo;

	@GetMapping({ "", "/", "index", "listar" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Listar Grados");
		model.addAttribute("grados", gradoRepo.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return "grado/listar";
	}

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("titulo", "Crear Grado");
		model.addAttribute("grado", new Grado());
		return "grado/form";
	}

	@GetMapping("form/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("titulo", "Editar Grado");
		model.addAttribute("grado", gradoRepo.findById(id).get());
		return "grado/form";
	}

	@PostMapping("form")
	public String guardar(Grado grado) {
		gradoRepo.save(grado);
		return "redirect:";
	}

}
