package net.codejava.bookstore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "IFSP_123456";

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✔ INSERT (OK)
    public void insertBook(Book book) {

        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setFloat(3, book.getPrice());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✔ DELETE (CORRIGIDO book_id)
    public void deleteBook(int id) {

        String sql = "DELETE FROM book WHERE book_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✔ GET BY ID
    public Book getBookById(int id) {

        String sql = "SELECT * FROM book WHERE book_id = ?";
        Book book = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getFloat("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    // ✔ LIST ALL
    public List<Book> listAllBooks() {

        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM book";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                list.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getFloat("price")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}