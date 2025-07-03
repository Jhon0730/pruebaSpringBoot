package com.bd.jpa.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bd.jpa.modelo.TblProducto;
import com.bd.jpa.servicio.IProductoServicio;

@Controller
@RequestMapping("/vistas/productos")
public class ProductoController {

    @Autowired
    private IProductoServicio productoServicio;

    @GetMapping("/listar")
    public String listarProductos(Model model) {
        List<TblProducto> lista = productoServicio.listarProductos();
        model.addAttribute("productos", lista);
        return "Vistas/ListarProductos";
    }

    @GetMapping("/registrar")
    public String mostrarFormRegistro(Model model) {
        model.addAttribute("producto", new TblProducto());
        return "Vistas/FrmRegProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute TblProducto producto, RedirectAttributes redirectAttributes) {
        try {
            productoServicio.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto guardado exitosamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Exceso de texto en la URL de la imagen. Por favor, verifica y vuelve a intentar.");
            return "redirect:/vistas/productos/registrar";
        }
        return "redirect:/vistas/productos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model) {
        TblProducto producto = productoServicio.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "Vistas/FrmRegProducto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoServicio.eliminarProducto(id);
        return "redirect:/";
    }
}