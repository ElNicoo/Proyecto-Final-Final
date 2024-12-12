package ar.com.libreriaspace.proyecto.dto;

// import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {
    private Long usuarioId;
    private List<Long> librosIds;
    private String fecha;
    private String estado;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Long> getLibrosIds() {
        return librosIds;
    }

    public void setLibrosIds(List<Long> librosIds) {
        this.librosIds = librosIds;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}



