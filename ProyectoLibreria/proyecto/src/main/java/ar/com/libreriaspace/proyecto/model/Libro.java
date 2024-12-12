package ar.com.libreriaspace.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "libros")
@NoArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(
        name = "pedidos_libros", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    )
    private List<Pedido> pedidos= new ArrayList<>();

    public Libro(String titulo,String autor,String genero,Integer precio,Integer stock){
        this.titulo= titulo;
        this.autor= autor;
        this.genero= genero;
        this.precio= precio;
        this.stock= stock;
    }

    public void agregarPedido(Pedido pedido){
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
    pedidos.add(pedido);
    }
}
