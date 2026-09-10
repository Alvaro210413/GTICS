package com.gtic.lab3.controller;

import com.gtic.lab3.entity.Tipo;
import com.gtic.lab3.repository.TipoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/tipo")
public class TipoController {

    private final TipoRepository tipoRepository;

    public TipoController(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    @GetMapping({"", "/list"})
    public String listarTipos(Model model) {
        model.addAttribute("listaTipos", tipoRepository.findAll());
        return "tipo/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoTipo(Model model) {
        model.addAttribute("tipo", new Tipo());
        return "tipo/form";
    }

    @GetMapping("/editar")
    public String editarTipo(@RequestParam("id") Integer id, Model model) {
        Optional<Tipo> opt = tipoRepository.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("tipo", opt.get());
            return "tipo/form";
        }
        return "redirect:/tipo/list";
    }

    @PostMapping("/guardar")
    public String guardarTipo(Tipo tipo, RedirectAttributes attr) {
        boolean esNuevo = (tipo.getIdTipo() == null);
        tipoRepository.save(tipo);
        if (esNuevo) {
            attr.addFlashAttribute("msg", "Tipo creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Tipo editado exitosamente");
        }
        return "redirect:/tipo/list";
    }

    @GetMapping("/borrar")
    public String borrarTipo(@RequestParam("id") Integer id, RedirectAttributes attr) {
        if (tipoRepository.existsById(id)) {
            tipoRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Tipo borrado exitosamente");
        }
        return "redirect:/tipo/list";
    }
}