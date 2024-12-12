package ar.com.libreriaspace.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import ar.com.libreriaspace.proyecto.dto.PedidoRequestDTO;
import ar.com.libreriaspace.proyecto.model.Pedido;
import ar.com.libreriaspace.proyecto.repository.PedidoRepository;
import ar.com.libreriaspace.proyecto.service.PedidoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepo;

    @GetMapping("/pedido/traer")
    public List<Pedido> getAllPedidos() {
        return pedidoService.obtenerPedidos();
    }
    
    @GetMapping("pedido/traer/{id}")
    public Pedido getPedidoByID(@PathVariable Long id){
        return pedidoService.traerPedido(id);
    }
    
    // @PostMapping("/pedido/crear")
    // public void crearPedido(@RequestBody Pedido p) {
    //     pedidoService.guardarPedido(p);
    // }

    @DeleteMapping("/pedido/eliminar/{id}")
    public void deletePedido(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
    }

    // @PutMapping("/pedido/editar/{id}")
    // public void updatePedido(@PathVariable Long id, @RequestBody Pedido p) {
    //     pedidoService.editarPedido(id, p.getFecha(),p.getEstado());
    // }

    @PutMapping("/pedido/editar/{id}")
    public String editarUnPedido(@PathVariable Long id, @RequestBody Pedido p) {
    
        if (pedidoRepo.existsById(id)) {
        
            Pedido pedidoExistente = pedidoRepo.findById(id).get();
            pedidoExistente.setFecha(p.getFecha());
            pedidoExistente.setEstado(p.getEstado());
            pedidoRepo.save(pedidoExistente);
            return "Pedido actualizado Correctamente";
        } else {
            return "Pedido no Encontrado";
        }
    }

    // @PostMapping("/pedido/crear-dto")
    // public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequestDTO pedidoRequest) {
    // Pedido pedido = pedidoService.crearPedidoConLibro(pedidoRequest);        
    //     return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    // }

    @PostMapping("/pedido/crear")
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequestDTO pedidoRequest) {
        Pedido pedido = pedidoService.crearPedidoConDetalles(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
