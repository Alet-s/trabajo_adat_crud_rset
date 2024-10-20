package model;
/**
 * @author Alejandro Serrano
 */

/**
 * El libro sobre el que se van a registrar datos en la base de datos.
 */
public class Book {
    private int id;
    private String titulo;
    private String autor;
    private int anioPublicacion;

    /**
     * Constructor con parámetros.
     *
     * @param id              el id del libro
     * @param autor           el autor del libro a especificar.
     * @param titulo          título del ejemplar
     * @param anioPublicacion año de publicacion del libro
     */
    public Book(int id, String titulo, String autor, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Constructor con parámetros sin id.
     * Para métodos en los que no se requiera acceder a la clave primaria de la base de datos.
     *
     * @param autor           el autor del libro a especificar.
     * @param titulo          título del ejemplar
     * @param anioPublicacion año de publicacion del libro
     */
    public Book(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
