package peliculaFactory;

import clases.Categoria;
import clases.Estreno;
import clases.Infantil;
import clases.Normal;
import clases.Pelicula;

/**Factory
 * 
 *
 * @author JoseHP
 */
public class GetPeliculaFactory {

    public static Pelicula getPelicula(String titulo, Categoria categoria) {

        if (categoria == null) {
            
            throw new UnsupportedOperationException("No se especificó el tipo de categoría.");
        }
        
        switch (categoria) {
            case NORMAL:
                return new Normal(titulo);
            case ESTRENO:
                return new Estreno(titulo);
            case INFANTIL:
                return new Infantil(titulo);  
                
                default:
                
                 throw new UnsupportedOperationException("La categoría de la pélicula no es válida.");
        }
    }
}
