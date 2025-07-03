package com.bd.jpa.servicio;

import java.util.List;

import com.bd.jpa.modelo.TblProducto;

public interface IProductoServicio {
	
	    void guardarProducto(TblProducto producto);
	    List<TblProducto> listarProductos();
	    TblProducto buscarProducto(Integer id);
	    void eliminarProducto(Integer id);
	}


