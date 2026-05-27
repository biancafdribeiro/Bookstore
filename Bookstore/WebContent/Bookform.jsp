<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Livro</title>

<style>

body{
    font-family: Arial;
    background: #f4f6f9;
}

.container{
    width: 500px;
    margin: 50px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

h1{
    text-align: center;
    color: #333;
}

label{
    font-weight: bold;
}

input{
    width: 100%;
    padding: 12px;
    margin-top: 5px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.btn{
    width: 100%;
    background: #28a745;
    color: white;
    border: none;
    padding: 12px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
}

.btn:hover{
    background: #218838;
}

.voltar{
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
    color: #007bff;
}

</style>

</head>

<body>

<div class="container">

    <h1>Cadastrar Livro</h1>

    <!-- CORRIGIDO AQUI -->
    <form action="insert" method="post">

        <label>Título</label>
        <input type="text" name="title">

        <label>Autor</label>
        <input type="text" name="author">

        <label>Preço</label>
        <input type="text" name="price">

        <button class="btn" type="submit">
            Salvar Livro
        </button>

    </form>

    <a class="voltar" href="<%=request.getContextPath()%>/">
        ← Voltar
    </a>

</div>

</body>
</html>