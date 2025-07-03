package com.bd.jpa.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_pedido")
public class TblPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    private int idpedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "idcliente")
    private TblCliente cliente;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TblDetallePedido> detalles;

    public TblPedido() {
    }

    public TblPedido(int idpedido, Date fecha, TblCliente cliente, BigDecimal total) {
        this.idpedido = idpedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TblCliente getCliente() {
        return cliente;
    }

    public void setCliente(TblCliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TblDetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<TblDetallePedido> detalles) {
        this.detalles = detalles;
    }
}


