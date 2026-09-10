package com.gtic.lab3.controller;

import com.gtic.lab3.entity.Sede;
import com.gtic.lab3.repository.SedeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/sede")
public class SedeController {

    private final SedeRepository sedeRepository;

    public SedeController(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @GetMapping({"", "/list"})
    public String listarSedes(Model model) {
        model.addAttribute("listaSedes", sedeRepository.findAll());
        return "sede/lista";
    }

    @GetMapping("/nueva")
    public String nuevaSede(Model model) {
        model.addAttribute("sede", new Sede());
        return "sede/form";
    }

    @GetMapping("/editar")
    public String editarSede(@RequestParam("id") Integer id, Model model) {
        Optional<Sede> opt = sedeRepository.findById(id);
        if (opt.isPresent()) {
            Sede sede = opt.get();
            model.addAttribute("sede", sede);
            model.addAttribute("listaTrabajadores", sede.getTrabajadores());
            return "sede/form";
        }
        return "redirect:/sede/list";
    }

    @PostMapping("/guardar")
    public String guardarSede(Sede sede, RedirectAttributes attr) {
        boolean esNuevo = (sede.getIdSede() == null);
        sedeRepository.save(sede);
        if (esNuevo) {
            attr.addFlashAttribute("msg", "Sede creada exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Sede actualizada exitosamente");
        }
        return "redirect:/sede/list";
    }

    @GetMapping("/borrar")
    public String borrarSede(@RequestParam("id") Integer id, RedirectAttributes attr) {
        Optional<Sede> opt = sedeRepository.findById(id);
        if (opt.isPresent()) {
            sedeRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Sede borrada exitosamente");
        }
        return "redirect:/sede/list";
    }
}