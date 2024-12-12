package ar.com.libreriaspace.proyecto.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

import ar.com.libreriaspace.proyecto.model.Pedido;
// import ar.com.libreriaspace.proyecto.model.Pedido;
import ar.com.libreriaspace.proyecto.model.Usuario;
// import ar.com.libreriaspace.proyecto.repository.UsuarioRepository;
import ar.com.libreriaspace.proyecto.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    // @Autowired
    // private UsuarioRepository usuarioRepo;

    @GetMapping("/usuario/traer")
    public List<Usuario> traerUsuario() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/usuario/traer/{id}")
    public Usuario traerUnUsuario(@PathVariable Long id){
        return usuarioService.traeUsuario(id);
    }

    @GetMapping("/usuario/{id}/pedidos")
    public List<Pedido> obtenerPedidosPorUsuario(@PathVariable Long id) {
    Usuario usuario = usuarioService.traeUsuario(id);
    if (usuario != null) {
        return usuario.getPedidos();  // Devuelve la lista de pedidos del usuario
    } else {
        return Collections.emptyList();  // Devuelve una lista vacía si el usuario no se encuentra
    }
    }

    // @PostMapping("/usuario/crear")
    // public void crearUsuaio(@RequestBody Usuario u) {        
    //     usuarioService.guardUsuario(u);
    // }
    @PostMapping("/usuario/crear") 
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) { 
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario); 
    }
    
    @DeleteMapping("/usuario/eliminar/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.eliminarUnUsuario(id);
    }

    @PutMapping("/usuario/editar/{id}")
    public void updateUsuario(@PathVariable Long id,@RequestBody Usuario u) {        
        usuarioService.edUsuario(id,u.getNombre(),u.getEmail(),u.getContrasenia());
    }
}
    // @PutMapping("/usuario/editar/{id}")
    // public Usuario editUsuario(@PathVariable Long id,
    //     @RequestParam(required = false, name = "nombre")String nuevoNombre,
    //     @RequestParam(required = false, name = "email")String nuevoEmail,
    //     @RequestParam(required = false, name = "contrasenia")String nuevoContrasenia){

    //     }

