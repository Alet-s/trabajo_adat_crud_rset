package dao;
/**
 * @author Alejandro Serrano
 */
import model.Book;
import util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioBookDAO {
    private Connection connection;//Atributo conexión con la base de datos

    /**
     * Constructor con parámetros
     *
     * @param connection el objeto conexión con la base de datos
     * @throws SQLException
     */
    public UsuarioBookDAO(Connection connection) throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta un nuevo libro en la BBDD
     *
     * @param book el objeto libro sobre el que se van a insertar datos.
     * @throws SQLException
     */
    public void insertBook(Book book) throws SQLException {
        String query = "SELECT * FROM libros";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);

        resultSet.moveToInsertRow();
        resultSet.updateString("titulo", book.getTitulo());
        resultSet.updateString("autor", book.getAutor());
        resultSet.updateInt("anio_publicacion", book.getAnioPublicacion());
        resultSet.insertRow();
    }

    /**
     * Consulta todos los registros de la tabla libros
     *
     * @throws SQLException
     */
    public void selectLibros() throws SQLException {
        String query = "SELECT * FROM libros";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            String autor = resultSet.getString("autor");
            int anioPublicacion = resultSet.getInt("anio_publicacion");
            System.out.println(id + " " + titulo + " " + autor + " " + anioPublicacion);

        }
    }

    /**
     * Actualiza el título de un libro
     *
     * @param titulo      el título actual del libro que se quiere modificar
     * @param nuevoTitulo el nuevo título al que se desea cambiar
     * @throws SQLException
     */
    public void updateTitulo(String titulo, String nuevoTitulo) throws SQLException {
        String query = "SELECT * FROM libros WHERE titulo = '" + titulo + "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            resultSet.updateString("titulo", nuevoTitulo);
            resultSet.updateRow();
        }
    }

    /**
     * Actualiza el autor de un libro
     *
     * @param autor      el nombre del autor actual con el que centa el libro.
     * @param nuevoAutor el nombre del nuevo autor que pasará a tener el libro.
     * @throws SQLException
     */
    public void updateAutor(String autor, String nuevoAutor) throws SQLException {
        String query = "SELECT * FROM libros WHERE autor = '" + autor + "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            resultSet.updateString("autor", nuevoAutor);
            resultSet.updateRow();
        }
    }

    /**
     * Actualiza el año de publicación de determinado libro
     *
     * @param anio      el año de publicación actual con el que cuenta el libro.
     * @param nuevoAnio el nuevo año de publicación que pasará a tener el libro.
     * @throws SQLException
     */
    public void updateAnioPublicacion(String anio, String nuevoAnio) throws SQLException {
        String query = "SELECT * FROM libros WHERE anio_publicacion=" + anio;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            resultSet.updateString("anio_publicacion", nuevoAnio);
            resultSet.updateRow();
        }
    }

    /**
     * Elimina un libro de la tabla de libros
     *
     * @param id el id único del libro que se quiere eliminar de la BBDD
     * @throws SQLException
     */
    public void deleteLibro(int id) throws SQLException {
        String query = "SELECT * FROM libros WHERE id = " + id;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            resultSet.deleteRow();
        }
    }

}
