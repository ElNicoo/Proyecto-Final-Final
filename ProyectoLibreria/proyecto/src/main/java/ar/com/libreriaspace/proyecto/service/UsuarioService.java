package ar.com.libreriaspace.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.libreriaspace.proyecto.model.Pedido;
import ar.com.libreriaspace.proyecto.model.Usuario;
import ar.com.libreriaspace.proyecto.repository.PedidoRepository;
import ar.com.libreriaspace.proyecto.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario guardUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario crearUsuario(Usuario usuario) { 
        return usuarioRepository.save(usuario);
    }

    public boolean eliminarUnUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario traeUsuario(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario edUsuario(Long id, String nuevoNombre,String nuevoEmail,String nuevoContrasenia){
        Usuario usuarioExistente = traeUsuario(id);
        if(usuarioExistente != null){
            usuarioExistente.setNombre(nuevoNombre);
            usuarioExistente.setEmail(nuevoContrasenia);
            usuarioExistente.setContrasenia(nuevoContrasenia);
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    public Pedido guardaPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
