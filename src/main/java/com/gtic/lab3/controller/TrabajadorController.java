package com.gtic.lab3.controller;

import com.gtic.lab3.entity.Trabajador;
import com.gtic.lab3.repository.SedeRepository;
import com.gtic.lab3.repository.TrabajadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    private final TrabajadorRepository trabajadorRepository;
    private final SedeRepository sedeRepository;

    public TrabajadorController(TrabajadorRepository trabajadorRepository, SedeRepository sedeRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.sedeRepository = sedeRepository;
    }

    @GetMapping({"", "/list"})
    public String listarTrabajadores(Model model) {
        model.addAttribute("listaTrabajadores", trabajadorRepository.findAll());
        return "trabajador/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoTrabajador(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        model.addAttribute("listaSedes", sedeRepository.findAll());
        return "trabajador/form";
    }

    @GetMapping("/editar")
    public String editarTrabajador(@RequestParam("id") String dni, Model model) {
        Optional<Trabajador> opt = trabajadorRepository.findById(dni);
        if (opt.isPresent()) {
            model.addAttribute("trabajador", opt.get());
            model.addAttribute("listaSedes", sedeRepository.findAll());
            return "trabajador/form";
        }
        return "redirect:/trabajador/list";
    }

    @PostMapping("/guardar")
    public String guardarTrabajador(Trabajador trabajador, RedirectAttributes attr) {
        boolean existe = trabajadorRepository.existsById(trabajador.getDni());
        trabajadorRepository.save(trabajador);
        if (!existe) {
            attr.addFlashAttribute("msg", "Trabajador creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Trabajador editado exitosamente");
        }
        return "redirect:/trabajador/list";
    }

    @GetMapping("/borrar")
    public String borrarTrabajador(@RequestParam("id") String dni, RedirectAttributes attr) {
        if (trabajadorRepository.existsById(dni)) {
            trabajadorRepository.deleteById(dni);
            attr.addFlashAttribute("msg", "Trabajador borrado exitosamente");
        }
        return "redirect:/trabajador/list";
    }
}