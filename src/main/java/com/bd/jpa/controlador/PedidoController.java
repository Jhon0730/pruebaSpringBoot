package com.bd.jpa.controlador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bd.jpa.modelo.TblCliente;
import com.bd.jpa.modelo.TblDetallePedido;
import com.bd.jpa.modelo.TblPedido;
import com.bd.jpa.modelo.TblProducto;
import com.bd.jpa.servicio.IDetallePedidoServicio;
import com.bd.jpa.servicio.IPedidoServicio;
import com.bd.jpa.servicio.IProductoServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/vistas/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoServicio pedidoServicio;

    @Autowired
    private IProductoServicio productoServicio;

    @Autowired
    private IDetallePedidoServicio detallePedidoServicio;

    @GetMapping("/reportes")
    public String verReportePedidos(Model model) {
        List<TblPedido> pedidos = pedidoServicio.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        return "Vistas/ReportePedidos";
    }

    @GetMapping("/nuevo")
    public String nuevoPedido(Model model) {
        List<TblProducto> productos = productoServicio.listarProductos();
        model.addAttribute("productos", productos);
        return "Vistas/FrmRegPedido";
    }

    @GetMapping("/nuevo/{id}")
    public String nuevoPedidoConProducto(@PathVariable("id") int id, Model model) {
        TblProducto producto = productoServicio.buscarProducto(id);
        List<TblProducto> productos = productoServicio.listarProductos();

        model.addAttribute("producto", producto);
        model.addAttribute("productos", productos);
        return "Vistas/FrmRegPedido";
    }

    // Método unificado para guardar pedidos
    @PostMapping("/guardar")
    public String guardarPedido(
            @RequestParam(value = "idCliente", required = false) Integer idCliente,
            @RequestParam("idProducto[]") List<Integer> idProductos,
            @RequestParam("cantidad[]") List<Integer> cantidades,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Obtener el ID del cliente (de parámetro o de sesión)
        Integer clienteId = idCliente;
        if (clienteId == null) {
            clienteId = (Integer) session.getAttribute("userId");
            if (clienteId == null) {
                redirectAttributes.addFlashAttribute("error", "No se ha identificado al cliente");
                return "redirect:/vistas/pedidos/nuevo";
            }
        }

        TblPedido pedido = new TblPedido();
        pedido.setFecha(new Date());

        TblCliente cliente = new TblCliente();
        cliente.setIdcliente(clienteId);
        pedido.setCliente(cliente);

        BigDecimal totalPedido = BigDecimal.ZERO;
        List<TblDetallePedido> detalles = new ArrayList<>();

        for (int i = 0; i < idProductos.size(); i++) {
            int idProd = idProductos.get(i);
            int cant = cantidades.get(i);

            TblProducto prod = productoServicio.buscarProducto(idProd);

            BigDecimal precioUnitario = BigDecimal.valueOf(prod.getPrecio());
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cant));

            TblDetallePedido detalle = new TblDetallePedido();
            detalle.setProducto(prod);
            detalle.setCantidad(cant);
            detalle.setPrecio_unitario(precioUnitario.doubleValue());
            detalle.setSubtotal(subtotal.doubleValue());
            detalle.setPedido(pedido);

            detalles.add(detalle);
            totalPedido = totalPedido.add(subtotal);
        }

        pedido.setTotal(totalPedido);
        pedido.setDetalles(detalles);

        pedidoServicio.guardarPedido(pedido);

        // Redirigir a reportes con mensaje de éxito
        redirectAttributes.addFlashAttribute("success", "Pedido registrado correctamente");
        return "redirect:/vistas/pedidos/reportes";
    }
}