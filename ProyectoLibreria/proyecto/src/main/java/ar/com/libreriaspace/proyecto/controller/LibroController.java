package ar.com.libreriaspace.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.libreriaspace.proyecto.dto.LibroRequestDTO;
import ar.com.libreriaspace.proyecto.model.Libro;
import ar.com.libreriaspace.proyecto.repository.LibroRepository;
import ar.com.libreriaspace.proyecto.service.libroService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class LibroController {

    @Autowired
    private LibroRepository libroRepo;

    @GetMapping("/libro/traer")
    public ResponseEntity <List<Libro>> traerLibro(){
        List<Libro> libros = libroRepo.findAll();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/libro/traer/{id}") 
    public ResponseEntity<Libro> traerUnLibro(@PathVariable Long id) { 
        return libroRepo.findById(id) .map(libro -> ResponseEntity.ok(libro)) 
        .orElse(ResponseEntity.notFound().build()); 
    }
    @Autowired
    private libroService libroService;
    @GetMapping("/libros") 
    public List<Libro> obtenerLibros() { 
        return libroService.obtenerTodosLosLibros(); 
    }
    @PostMapping("/libro/crear") 
    public ResponseEntity<String> postMethodName(@RequestBody LibroRequestDTO libroDTO) { 
        Libro libro = new Libro(libroDTO.getTitulo(), libroDTO.getAutor(), libroDTO.getGenero(), libroDTO.getPrecio(), libroDTO.getStock()); 
        libroRepo.save(libro); 
        return ResponseEntity.status(201).body("Libro Creado Correctamente"); 
    }

    @DeleteMapping("/libro/eliminar/{id}") 
    public ResponseEntity<String> eliminarUnLibro(@PathVariable Long id) { 
        if (libroRepo.existsById(id)) { libroRepo.deleteById(id); return ResponseEntity.ok("Libro Eliminado Correctamente"); 
    } else { 
        return ResponseEntity.status(404).body("Libro no encontrado"); 
    }
    }

    @PutMapping("/libro/editar/{id}") 
    public ResponseEntity<String> editUnLibro(@PathVariable Long id, @RequestBody LibroRequestDTO libroDTO) { 
        return libroRepo.findById(id) 
        .map(libroExistente -> { libroExistente.setTitulo(libroDTO.getTitulo());
            libroExistente.setAutor(libroDTO.getAutor());
            libroExistente.setGenero(libroDTO.getGenero());
            libroExistente.setPrecio(libroDTO.getPrecio()); 
            libroExistente.setStock(libroDTO.getStock());
            libroRepo.save(libroExistente);
        return ResponseEntity.ok("Libro Actualizado Correctamente"); }) .orElse(ResponseEntity.status(404).body("Libro no encontrado"));
    }

    @PutMapping("/libro/actualizar/{id}") 
    public ResponseEntity<String> actualizarUnLibro(@PathVariable Long id, @RequestBody Libro p) { 
        if (!libroRepo.existsById(id)) { 
            return ResponseEntity.status(404).body("Libro no encontrado"); 
        } 
        Libro libroBuscada = libroRepo.findById(id).orElse(null); 
        if (libroBuscada != null) { 
            libroBuscada.setTitulo(p.getTitulo()); 
            libroBuscada.setAutor(p.getAutor()); 
            libroBuscada.setGenero(p.getGenero()); 
            libroBuscada.setPrecio(p.getPrecio()); 
            libroBuscada.setStock(p.getStock()); 
            libroRepo.save(libroBuscada); 
            return ResponseEntity.ok("Datos del libro actualizado correctamente"); 
        } else { 
            return ResponseEntity.status(404).body("Libro no encontrado"); 
        } 
    }
    @GetMapping("/libro/buscar/{nombre}")
    public ResponseEntity<List<Libro>> buscarPorNombre(@PathVariable String titulo) {
        List<Libro> libros = libroService.buscarPorNombre(titulo);
        return ResponseEntity.ok(libros);
    }

    // @GetMapping("/libro/buscarSimilar/{cadena}")
    // public ResponseEntity<List<Libro>> buscarPorNombreSimilar(@PathVariable String cadena) {
    //     List<Libro> libros = libroService.buscarLibrosPorNombreSimilar(cadena);
    //     return ResponseEntity.ok(libros);
    // }
    @GetMapping("persona/buscarSimilar/{cadena}")
public ResponseEntity<List<Libro>> buscarPorNombreSimilar(@PathVariable String cadena) {
    List<Libro> libros = libroService.buscarLibrosPorNombreSimilar(cadena);
    return ResponseEntity.ok(libros);}


}
