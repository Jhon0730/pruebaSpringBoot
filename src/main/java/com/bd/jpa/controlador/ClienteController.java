package com.bd.jpa.controlador;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bd.jpa.modelo.TblCliente;
import com.bd.jpa.servicio.IClienteServicio;

@Controller
@RequestMapping("/vistas/clientes")
public class ClienteController {

    @Autowired
    private IClienteServicio iclienteservicio;

    @GetMapping("/listar")
    public String listarClientes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model modelo) {
        try {
            List<TblCliente> listado = iclienteservicio.ListarCliente();
            modelo.addAttribute("listado", listado);
            modelo.addAttribute("currentPage", page);
            modelo.addAttribute("totalPages", (int) Math.ceil((double) listado.size() / size));
            return "Vistas/ListarClientes";
        } catch (Exception e) {
            e.printStackTrace();
            modelo.addAttribute("error", "Error al cargar la lista de clientes: " + e.getMessage());
            modelo.addAttribute("listado", List.of());
            modelo.addAttribute("currentPage", 0);
            modelo.addAttribute("totalPages", 0);
            return "Vistas/ListarClientes";
        }
    }

    @GetMapping("/registrar")
    public String registrarCliente(@RequestParam(value = "from", required = false) String from, Model modelo) {
        modelo.addAttribute("cliente", new TblCliente());
        modelo.addAttribute("from", from); // indica si vino desde login
        return "Vistas/FrmRegCliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(
            @Valid @ModelAttribute("cliente") TblCliente tblcli,
            BindingResult result,
            @RequestParam(value = "from", required = false) String from,
            Model model,
            RedirectAttributes redirectAttributes) {

        boolean esEdicion = tblcli.getIdcliente() != null && tblcli.getIdcliente() != 0;
        TblCliente existente = iclienteservicio.buscarPorEmail(tblcli.getEmail());

        if (existente != null && (!esEdicion || !existente.getIdcliente().equals(tblcli.getIdcliente()))) {
            result.rejectValue("email", "error.cliente", "El correo ya está en uso");
        }

        if (result.hasErrors()) {
            model.addAttribute("cliente", tblcli);
            model.addAttribute("from", from);
            return "Vistas/FrmRegCliente";
        }

        if (esEdicion) {
            TblCliente clienteExistente = iclienteservicio.BuscarCliente(tblcli.getIdcliente());
            if (clienteExistente != null && (tblcli.getPassword() == null || tblcli.getPassword().isEmpty())) {
                tblcli.setPassword(clienteExistente.getPassword());
            }
        }

        iclienteservicio.RegistrarCliente(tblcli);
        redirectAttributes.addFlashAttribute("message", "Cuenta creada exitosamente");

        // Redirige según el origen del formulario
        if ("login".equals(from)) {
            return "redirect:/login";
        } else {
            return "redirect:/vistas/clientes/listar";
        }
    }



    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            TblCliente cliente = iclienteservicio.BuscarCliente(id);
            if (cliente == null) {
                redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
                return "redirect:/vistas/clientes/listar";
            }
            model.addAttribute("cliente", cliente);
            return "Vistas/FrmRegCliente";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al cargar el cliente: " + e.getMessage());
            return "redirect:/vistas/clientes/listar";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Integer idcliente, RedirectAttributes redirectAttributes) {
        try {
            TblCliente tblcli = new TblCliente();
            tblcli.setIdcliente(idcliente);
            iclienteservicio.EliminarCliente(tblcli);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el cliente: " + e.getMessage());
        }
        return "redirect:/vistas/clientes/listar";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("error", "Ha ocurrido un error inesperado: " + e.getMessage());
        model.addAttribute("listado", List.of());
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", 0);
        return "Vistas/ListarClientes";
    }
}
