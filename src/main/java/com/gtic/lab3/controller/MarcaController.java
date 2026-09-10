package com.gtic.lab3.controller;

import com.gtic.lab3.entity.Marca;
import com.gtic.lab3.repository.MarcaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @GetMapping({"", "/list"})
    public String listarMarcas(Model model) {
        model.addAttribute("listaMarcas", marcaRepository.findAll());
        return "marca/lista";
    }

    @GetMapping("/nueva")
    public String nuevaMarca(Model model) {
        model.addAttribute("marca", new Marca());
        return "marca/form";
    }

    @GetMapping("/editar")
    public String editarMarca(@RequestParam("id") Integer id, Model model) {
        Optional<Marca> opt = marcaRepository.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("marca", opt.get());
            return "marca/form";
        }
        return "redirect:/marca/list";
    }

    @PostMapping("/guardar")
    public String guardarMarca(Marca marca, RedirectAttributes attr) {
        boolean esNuevo = (marca.getIdMarca() == null);
        marcaRepository.save(marca);
        if (esNuevo) {
            attr.addFlashAttribute("msg", "Marca creada exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Marca editada exitosamente");
        }
        return "redirect:/marca/list";
    }

    @GetMapping("/borrar")
    public String borrarMarca(@RequestParam("id") Integer id, RedirectAttributes attr) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Marca borrada exitosamente");
        }
        return "redirect:/marca/list";
    }
}