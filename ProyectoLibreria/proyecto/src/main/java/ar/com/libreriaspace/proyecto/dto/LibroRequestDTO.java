package ar.com.libreriaspace.proyecto.dto;

public class LibroRequestDTO {
    private String titulo;
    private String autor;
    private String genero;
    private Integer precio;
    private Integer stock;

    public LibroRequestDTO(String titulo,String autor,String genero, Integer precio, Integer stock){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
    }
    // Getters y setters 
    public String getTitulo() { return titulo; } 
    public void setTitulo(String titulo) { this.titulo = titulo; } 
    public String getAutor() { return autor; } 
    public void setAutor(String autor) { this.autor = autor; } 
    public String getGenero() { return genero; } 
    public void setGenero(String genero) { this.genero = genero; } 
    public Integer getPrecio() { return precio; } 
    public void setPrecio(Integer precio) { this.precio = precio; } 
    public Integer getStock() { return stock; }
     public void setStock(Integer stock) { this.stock = stock; }
}

