package ar.com.libreriaspace.proyecto.model;

import java.time.Instant;

import lombok.Data;

@Data
public class Dolar {
private String moneda;
private String casa;
private String nombre;
private Double compra;
private Double venta;
private Instant fechaActual;

}
