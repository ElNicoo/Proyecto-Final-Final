package ar.com.libreriaspace.proyecto.service;

// import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import ar.com.libreriaspace.proyecto.dto.LibroRequestDTO;
import ar.com.libreriaspace.proyecto.dto.PedidoRequestDTO;
import ar.com.libreriaspace.proyecto.model.Libro;
import ar.com.libreriaspace.proyecto.model.Pedido;
import ar.com.libreriaspace.proyecto.model.Usuario;
import ar.com.libreriaspace.proyecto.repository.LibroRepository;
import ar.com.libreriaspace.proyecto.repository.PedidoRepository;
import ar.com.libreriaspace.proyecto.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Pedido> obtenerPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public boolean eliminarPedido(Long id){
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pedido traerPedido(Long id){
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido editarPedido(Long id, String nuevaFecha, String nuevoEstado){
    Pedido pedidoExistente = traerPedido(id);
    if (pedidoExistente != null){
        pedidoExistente.setFecha(nuevaFecha);
        pedidoExistente.setEstado(nuevoEstado);
        return guardarPedido(pedidoExistente);
    }
    return null;
  }

  public Pedido crearPedidoConDetalles(PedidoRequestDTO pedidoRequest) {
        Pedido pedido = new Pedido();
        pedido.setEstado(pedidoRequest.getEstado());
        pedido.setFecha(pedidoRequest.getFecha());
        // Si se proporciona un usuario, lo asociamos al pedido
        if (pedidoRequest.getUsuarioId() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(pedidoRequest.getUsuarioId());
            usuario.ifPresent(pedido::setUsuario);
        }
        // Si se proporcionan libros, los asociamos al pedido
        if (pedidoRequest.getLibrosIds() != null && !pedidoRequest.getLibrosIds().isEmpty()) {
            List<Libro> libros = libroRepository.findAllById(pedidoRequest.getLibrosIds());
            pedido.setLibros(libros);
        }
        return pedidoRepository.save(pedido);
    }

//   public Pedido crearPedidoConLibro(PedidoRequestDTO pedidoRequest) {
//         Pedido pedido = new Pedido(pedidoRequest.getFecha(),pedidoRequest.getEstado());

//         for (LibroRequestDTO libro : pedidoRequest.getLibros()){
//             Libro libroNuevo = new Libro(libro.getTitulo(),libro.getAutor(),libro.getGenero(),libro.getPrecio(),libro.getStock());
//             libroNuevo.agregarPedido(pedido);
//             pedido.agregarLibro(libroNuevo);
//             libroRepository.save(libroNuevo);
//         }
//         return pedidoRepository.save(pedido);
//     }
}
