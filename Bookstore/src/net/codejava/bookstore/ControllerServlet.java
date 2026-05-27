package net.codejava.bookstore;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet({"/", "/new", "/insert", "/delete", "/edit"})
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {

                case "/new":
                    showNewForm(request, response);
                    break;

                case "/insert":
                    insertBook(request, response);
                    break;

                case "/delete":
                    deleteBook(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;

                default:
                    listBook(request, response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Bookform.jsp");

        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String priceStr = request.getParameter("price");

        priceStr = priceStr.replace(",", ".");

        float price = Float.parseFloat(priceStr);

        Book book = new Book(title, author, price);

        bookDAO.insertBook(book);

        response.sendRedirect(request.getContextPath() + "/");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        bookDAO.deleteBook(id);

        response.sendRedirect(request.getContextPath() + "/");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        Book existingBook = bookDAO.getBookById(id);

        request.setAttribute("book", existingBook);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Bookform.jsp");

        dispatcher.forward(request, response);
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> listBook = bookDAO.listAllBooks();

        request.setAttribute("listBook", listBook);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Booklist.jsp");

        dispatcher.forward(request, response);
    }
}