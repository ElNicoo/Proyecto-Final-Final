package ar.com.libreriaspace.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.libreriaspace.proyecto.model.Libro;
import ar.com.libreriaspace.proyecto.repository.LibroRepository;

import java.util.List;

@Service
public class libroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> buscarPorNombre(String titulo) {
        return libroRepository.findByNombreContaining(titulo);
    }

    public List<Libro> buscarLibrosPorNombreSimilar(String titulo) {
        return libroRepository.findByNombreLike(titulo);
}

}


