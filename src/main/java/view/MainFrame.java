package view;
/**
 * @author Alejandro Serrano con asistencia IA
 */
import dao.UsuarioBookDAO;
import model.Book;
import util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz básica en Swing proporcionada por ChatGPT en base a mi propio código.
 * Muestra la tabla libros y permite hacer operaciones CRUD sobre la misma utilizando botones y ventanas emergentes.
 */
public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private UsuarioBookDAO dao;

    public MainFrame() {
        setTitle("Libreria");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            Connection connection = DBConnection.getConnection();
            dao = new UsuarioBookDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
            System.exit(1);
        }

        // Table model and JTable
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Year"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Botones para operaciones CRUD
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton refreshButton = new JButton("Refresh");

        // Paneles para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Componentes
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargamos la tabla
        loadTableData();

        // Acciones para los botones mediante lambdas.
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        refreshButton.addActionListener(e -> loadTableData());
    }

    private void loadTableData() {
        try {
            tableModel.setRowCount(0); // Clear existing data
            List<Book> books = fetchBooks();
            for (Book book : books) {
                tableModel.addRow(new Object[]{book.getId(), book.getTitulo(), book.getAutor(), book.getAnioPublicacion()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data");
        }
    }

    private List<Book> fetchBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM libros";
        Connection conn = DBConnection.getConnection();
        var statement = conn.createStatement();
        var resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            String autor = resultSet.getString("autor");
            int anio = resultSet.getInt("anio_publicacion");
            books.add(new Book(id, titulo, autor, anio));
        }
        return books;
    }

    //Métodos privados para las operaciones CRUD en la interfaz gráfica.
    private void addBook() {
        // Prompt for book details
        String title = JOptionPane.showInputDialog(this, "Enter Title:");
        String author = JOptionPane.showInputDialog(this, "Enter Author:");
        String yearStr = JOptionPane.showInputDialog(this, "Enter Year:");
        try {
            int year = Integer.parseInt(yearStr);
            Book book = new Book(title, author, year);
            dao.insertBook(book);
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void updateBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String newTitle = JOptionPane.showInputDialog(this, "Enter new title:");
            try {
                dao.updateTitulo((String) tableModel.getValueAt(selectedRow, 1), newTitle);
                loadTableData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating book");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to update");
        }
    }

    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            try {
                dao.deleteLibro(id);
                loadTableData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting book");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to delete");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
