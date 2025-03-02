package com.example.controller;

import com.example.model.Producto;
import com.example.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @PostMapping
    public Mono<ResponseEntity<Producto>> agregarProducto(@RequestBody Producto producto) {
        return service.guardar(producto).map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Producto> listarProductos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> obtenerProducto(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizar(id, producto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarProducto(@PathVariable Long id) {
        return service.eliminar(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
