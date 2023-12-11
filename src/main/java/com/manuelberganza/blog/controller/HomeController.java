package com.manuelberganza.blog.controller;

import com.manuelberganza.blog.model.Comentario;
import com.manuelberganza.blog.model.Perfil;
import com.manuelberganza.blog.model.Publicacion;
import com.manuelberganza.blog.model.Usuario;
import com.manuelberganza.blog.service.ICategoria;
import com.manuelberganza.blog.service.IComentario;
import com.manuelberganza.blog.service.IPublicaciones;
import com.manuelberganza.blog.service.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private IPublicaciones publicacionesService;

    @Autowired
    private IComentario comentarioService;

    @Autowired
    private ICategoria categoriaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String mostrarHome(Authentication auth, Model model, Pageable page) {
        Usuario usuario = usuarioService.buscarPorNombre(auth.getName());
        model.addAttribute("publicaciones", publicacionesService.buscarTodasPaginada(page));

        return "home";
    }

    @GetMapping("/mis-publicaciones")
    public String mostrarMisPublicaciones(Authentication auth, Model model) {
        Usuario usuario = usuarioService.buscarPorNombre(auth.getName());
        model.addAttribute("publicaciones", publicacionesService.buscarTodasPorId(usuario));

        return "mis-publicaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPublicacion(@PathVariable("id") Integer idPublicacion) {
        publicacionesService.eliminarPublicacion(idPublicacion);

        return "redirect:/mis-publicaciones";
    }

    @GetMapping("/publicacion/{id}")
    public String mostarPublicacion(@PathVariable("id") Integer id, Comentario comentario, Model model, Authentication auth) {
        Publicacion publicacion = publicacionesService.buscarPorId(id);

        model.addAttribute("publicacion", publicacion);
        model.addAttribute("comentarios", comentarioService.bucarPorPublicacion(publicacion));

        return "publicacion";
    }

    @PostMapping("/publicacion/{id}")
    public String guardarComentario(@PathVariable("id") Integer idPublicacion, @ModelAttribute("comentario") Comentario comentario, Authentication auth) {

        Publicacion publicacion = publicacionesService.buscarPorId(idPublicacion);
        Usuario usuario = usuarioService.buscarPorNombre(auth.getName());

        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);

        comentarioService.guardarComentario(comentario);

        return "redirect:/publicacion/" + idPublicacion;
    }

    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }

    @GetMapping("/registro")
    public String registro(Usuario usuario) {

        return "registro";
    }

    @PostMapping("/registro")
    public String guardarRegistro(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr, Model model) {

        if (result.hasErrors()) { return "/registro"; }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEstatus(1);

        Perfil perfil = new Perfil();
        perfil.setId(1);

        usuario.agregar(perfil);

        if (!usuarioService.validarUsername(usuario.getUsername())) {
            model.addAttribute("errUsername", "Usuario ya existe, elija otro nombre!");

            return "/registro";
        }

        if (!usuarioService.validarEmail(usuario.getEmail())) {
            model.addAttribute("errEmail", "Email ya existe, elija otro.");

            return "/registro";
        }

        usuarioService.guardarUsuario(usuario);
        attr.addFlashAttribute("msg", "Usuario registrado exitosamente!");

        return "redirect:/login";
    }

    @GetMapping("/mi-perfil")
    public String mostrarMiPerfil(Publicacion publicacion, Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodas());

        return "mi-perfil";
    }

    @PostMapping("/mi-perfil")
    public String guardarPublicacion(@ModelAttribute("publicacion") Publicacion publicacion, Authentication auth, BindingResult result) {

        if (result.hasErrors()) {

            return "/mi-perfil";
        }

        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();

        publicacion.setFecha_publicacion(fecha.format(d));
        publicacion.setUsuario(usuarioService.buscarPorNombre(auth.getName()));

        publicacionesService.guardar(publicacion);

        return "redirect:/mis-publicaciones";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);

        return "redirect:/login";
    }

}
