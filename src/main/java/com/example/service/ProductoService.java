package com.example.service;

import com.example.model.Producto;
import com.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto producto) {
        if (repository.existsById(id)) {
            producto.setId(id);
            return repository.save(producto);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
