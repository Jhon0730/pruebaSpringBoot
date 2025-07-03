package com.bd.jpa.controlador;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bd.jpa.modelo.TblProducto;
import com.bd.jpa.servicio.IProductoServicio;

@Controller
public class HomeController {

    @Autowired
    private IProductoServicio productoServicio;

    @GetMapping("/")
    public String inicio(HttpSession session, Model model) {
        List<TblProducto> productos = productoServicio.listarProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new TblProducto());

        
        String rol = (String) session.getAttribute("rol");
        String username = (String) session.getAttribute("username");

        model.addAttribute("rol", rol);
        model.addAttribute("username", username);

        return "Vistas/index";
    }
}
