package com.gestion.productos.service;


import com.gestion.productos.model.Producto;
import com.gestion.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public void guardarProducto(Producto producto){
        productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Long id){
        return productoRepository.findById(id).get();
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
