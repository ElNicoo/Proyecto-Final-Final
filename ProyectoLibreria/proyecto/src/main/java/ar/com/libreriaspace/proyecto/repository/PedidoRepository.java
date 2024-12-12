package ar.com.libreriaspace.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.libreriaspace.proyecto.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}
