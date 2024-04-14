<%--
  Created by IntelliJ IDEA.
  User: Mamadou Diouf
  Date: 28/03/2024
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xml:lang="fr" lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .error-message {
            color: #333;
        }

        .error-code {
            font-size: 120px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #dc3545;
        }

        .error-message p {
            font-size: 24px;
            margin-bottom: 30px;
        }

        .back-link {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="error-content">
        <div class="error-code">404</div>
        <div class="error-message">
            <p>Ohhhh! Page bi nga lal baxoul..</p>
            <p>Désolé, la page que vous recherchez a peut-être été supprimée, son nom a été modifié ou est temporairement indisponible.</p>
            <p><a href="/gestionscolaire_war/" class="back-link">Back to Home</a></p>
        </div>
    </div>
</div>
</body>
</html>

