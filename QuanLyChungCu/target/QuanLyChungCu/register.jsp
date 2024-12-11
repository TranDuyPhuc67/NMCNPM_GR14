<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        /* Ghi đè font cho toàn bộ body */
        body {
            font-family: 'Be Vietnam', sans-serif !important;
        }
       
    </style>
</head>
<body>
    <h2>Register</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">Username: </label>
        <input type="text" id="username" name="username" required/><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required/><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required/><br><br>
        
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
