package com.gtic.lab3.controller;

import com.gtic.lab3.entity.Inventario;
import com.gtic.lab3.repository.InventarioRepository;
import com.gtic.lab3.repository.MarcaRepository;
import com.gtic.lab3.repository.SedeRepository;
import com.gtic.lab3.repository.TipoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioRepository inventarioRepository;
    private final SedeRepository sedeRepository;
    private final MarcaRepository marcaRepository;
    private final TipoRepository tipoRepository;

    public InventarioController(InventarioRepository inventarioRepository,
                                SedeRepository sedeRepository,
                                MarcaRepository marcaRepository,
                                TipoRepository tipoRepository) {
        this.inventarioRepository = inventarioRepository;
        this.sedeRepository = sedeRepository;
        this.marcaRepository = marcaRepository;
        this.tipoRepository = tipoRepository;
    }

    @GetMapping({"", "/list"})
    public String listarInventario(Model model) {
        model.addAttribute("listaInventario", inventarioRepository.findAll());
        return "inventario/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoInventario(Model model) {
        model.addAttribute("inventario", new Inventario());
        model.addAttribute("listaSedes", sedeRepository.findAll());
        model.addAttribute("listaMarcas", marcaRepository.findAll());
        model.addAttribute("listaTipos", tipoRepository.findAll());
        return "inventario/form";
    }

    @GetMapping("/editar")
    public String editarInventario(@RequestParam("id") Integer id, Model model) {
        Optional<Inventario> opt = inventarioRepository.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("inventario", opt.get());
            model.addAttribute("listaSedes", sedeRepository.findAll());
            model.addAttribute("listaMarcas", marcaRepository.findAll());
            model.addAttribute("listaTipos", tipoRepository.findAll());
            return "inventario/form";
        }
        return "redirect:/inventario/list";
    }

    @PostMapping("/guardar")
    public String guardarInventario(Inventario inventario, RedirectAttributes attr) {
        boolean esNuevo = (inventario.getIdInventario() == null);
        inventarioRepository.save(inventario);
        if (esNuevo) {
            attr.addFlashAttribute("msg", "Inventario creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Inventario editado exitosamente");
        }
        return "redirect:/inventario/list";
    }

    @GetMapping("/borrar")
    public String borrarInventario(@RequestParam("id") Integer id, RedirectAttributes attr) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Inventario borrado exitosamente");
        }
        return "redirect:/inventario/list";
    }
}