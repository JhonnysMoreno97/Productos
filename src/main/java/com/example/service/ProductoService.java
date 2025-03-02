package com.example.service;

import com.example.model.Producto;
import com.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public Flux<Producto> listarTodos() {
        return repository.findAll();
    }

    public Mono<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Mono<Producto> guardar(Producto producto) {
        return repository.save(producto);
    }

    public Mono<Producto> actualizar(Long id, Producto producto) {
        return repository.findById(id)
                .flatMap(existingProducto -> {
                    producto.setId(id);
                    return repository.save(producto);
                });
    }

    public Mono<Void> eliminar(Long id) {
        return repository.deleteById(id);
    }

}
