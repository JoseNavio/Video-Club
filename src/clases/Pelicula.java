package clases;

import java.io.Serializable;

/**
 * Pelicula
 *
 * @author JoseHP
 */
public abstract class Pelicula implements Comparable<Pelicula>, Serializable{

    private final String TITULO;
    private Categoria categoria;

    //Constructor 
    public Pelicula(String titulo, Categoria categoria) {
        this.TITULO = titulo;
        this.categoria = categoria;
    }

    //Getters
    public String getTitulo() {
        return TITULO;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public abstract double getPrecio(int dias);

    public abstract int getPuntos(int dias);
 

    //Setters
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria; 
//    }

    @Override
    public String toString() {

        return "Título: " + TITULO + ", Categoría: " + categoria;
    }
}
