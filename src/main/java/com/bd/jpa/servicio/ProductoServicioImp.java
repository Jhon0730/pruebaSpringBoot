package com.bd.jpa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.jpa.modelo.TblProducto;
import com.bd.jpa.repositorio.IProductoRepositorio;

@Service
public class ProductoServicioImp implements IProductoServicio{

	
	 @Autowired
	    private IProductoRepositorio productoRepo;
	 
	 
	@Override
	public void guardarProducto(TblProducto producto) {
		// TODO Auto-generated method stub
		 productoRepo.save(producto);
	}

	@Override
	public List<TblProducto> listarProductos() {
		// TODO Auto-generated method stub
		return productoRepo.findAll();
	}

	@Override
	public TblProducto buscarProducto(Integer id) {
		// TODO Auto-generated method stub
		return productoRepo.findById(id).orElse(null);
	}

	@Override
	public void eliminarProducto(Integer id) {
		 productoRepo.deleteById(id);
		
	}

}
