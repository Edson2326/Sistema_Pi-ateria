package com.fantasi.fantasia.controller;

import com.fantasi.fantasia.entity.Usuario;
import com.fantasi.fantasia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = service.buscar(id);
        usuario.setPassword(""); // no mostrar el hash/valor actual en el formulario
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Usuario usuario,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "usuarios/form";
        }

        service.guardar(usuario);
    return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/usuarios";
    }
}