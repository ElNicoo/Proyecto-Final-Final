package ar.com.libreriaspace.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.libreriaspace.proyecto.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long>{
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(?1)")
    List<Libro> findByNombreContaining(String nombre);
    
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT(:titulo, '%'))")
    List<Libro> findByNombreLike(@Param("titulo") String titulo);
}
