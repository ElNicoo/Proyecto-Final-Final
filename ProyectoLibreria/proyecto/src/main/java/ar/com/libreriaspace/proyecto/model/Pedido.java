package ar.com.libreriaspace.proyecto.model;

// import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pedidos")
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Column(name = "fecha", nullable = false)
    private String fecha;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany(mappedBy = "pedidos")
    @JsonManagedReference
    private List<Libro> libros= new ArrayList<>();

    public Pedido(String fecha,String estado){
        this.fecha = fecha;
        this.estado = estado;
    }

    public Pedido(String fecha,String estado, Usuario usuario){
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarLibro(Libro libro){
        if (libros == null) {
            libros = new ArrayList<>();
        }
        libros.add(libro);
    }

    public void quitarLibro(Libro libro){
        if (libros != null) {
            libros.remove(libro);
        }
    }

    public int getLibrosCount(){
        return libros.size();
    }

    public void quitarTodosLosLibros() {
        if (libros != null) {
            libros.clear();
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Libro libro : libros) {
            total += libro.getPrecio(); 
        }
        return total;
    }
    
}
