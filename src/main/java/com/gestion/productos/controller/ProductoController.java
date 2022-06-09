package com.gestion.productos.controller;


import com.gestion.productos.model.Producto;
import com.gestion.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productos")
    public void guardarProducto(@RequestBody Producto producto){
        productoService.guardarProducto(producto);
    }

    @PutMapping("/productos/{id}")// el Signo <?> significa que puede devolver un ResponseEntity de cualquier tipo
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id){
        try {
            Producto productoExistente = productoService.obtenerProductoPorId(id);

            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());

            productoService.guardarProducto(productoExistente);
            return new ResponseEntity<Producto>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("productos/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }

}
