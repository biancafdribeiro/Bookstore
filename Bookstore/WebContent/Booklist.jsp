<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="net.codejava.bookstore.Book" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livraria</title>

<style>
body{
    font-family: Arial;
    background: #f4f6f9;
    margin: 0;
    padding: 0;
}

.container{
    width: 80%;
    margin: 40px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

h1{
    text-align: center;
    color: #333;
}

.top-bar{
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.btn{
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    text-decoration: none;
    color: white;
    font-weight: bold;
}

.btn-add{
    background: #28a745;
}

.btn-creditos{
    background: #6c63ff;
}

table{
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed;
}

table th{
    background: #343a40;
    color: white;
    padding: 12px;
    text-align: center;
}

table td{
    padding: 12px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}

.btn-edit{
    background: #007bff;
    padding: 8px 12px;
    border-radius: 5px;
    color: white;
    text-decoration: none;
}

.btn-delete{
    background: #dc3545;
    padding: 8px 12px;
    border-radius: 5px;
    color: white;
    text-decoration: none;
}
</style>

</head>

<body>

<div class="container">

    <h1>Sistema de Livros</h1>

    <div class="top-bar">

        <!-- ✔ agora chama o SERVLET corretamente -->
        <a class="btn btn-add"
           href="<%= request.getContextPath() %>/new">
            + Novo Livro
        </a>

        <!-- ✔ créditos com caminho correto -->
        <a class="btn btn-creditos"
           href="<%= request.getContextPath() %>/creditos.jsp">
            Créditos
        </a>

    </div>

    <table>

        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>

        <%
            List<Book> listBook =
                (List<Book>) request.getAttribute("listBook");

            if (listBook != null && !listBook.isEmpty()) {

                for (Book b : listBook) {
        %>

        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getPrice() %></td>

            <td>

                <!-- ✔ editar agora chama servlet -->
                <a class="btn-edit"
                   href="<%= request.getContextPath() %>/edit?id=<%= b.getId() %>">
                    Editar
                </a>

                <!-- ✔ excluir agora chama servlet -->
                <a class="btn-delete"
                   href="<%= request.getContextPath() %>/delete?id=<%= b.getId() %>"
                   onclick="return confirm('Tem certeza que deseja excluir este livro?')">
                    Excluir
                </a>

            </td>
        </tr>

        <%
                }
            } else {
        %>

        <tr>
            <td colspan="5">Nenhum livro encontrado</td>
        </tr>

        <%
            }
        %>

    </table>

</div>

</body>
</html>