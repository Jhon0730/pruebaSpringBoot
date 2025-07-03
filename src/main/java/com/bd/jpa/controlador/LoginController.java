package com.bd.jpa.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bd.jpa.modelo.TblCliente;
import com.bd.jpa.servicio.IClienteServicio;

@Controller
public class LoginController {

    @Autowired
    private IClienteServicio clienteServicio;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas, intente de nuevo.");
        }
        if (logout != null) {
            model.addAttribute("message", "Has cerrado sesión exitosamente.");
        }
        return "Vistas/login";
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                HttpSession session) {

        TblCliente cliente = clienteServicio.buscarPorEmail(email);

        if (cliente != null && cliente.getPassword().equals(password)) {
            session.setAttribute("usuarioLogueado", cliente);
            session.setAttribute("rol", cliente.getRol());
            session.setAttribute("username", cliente.getEmail());
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
