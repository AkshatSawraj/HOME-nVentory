<%-- 
    Document   : login
    Created on : Nov 16, 2021, 11:21:38 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Muli&display=swap');

            input[type=submit] {
                background-color: red;
                border: none;
                color: white;
                padding: 16px 32px;
                text-decoration: none;
                margin: 4px 2px;
                cursor: pointer;
                opacity: .8
            }



            .btn:hover {
                opacity: 1;
            }





            body {
                background-color: lavender;
                font-family: Arial, Helvetica, sans-serif;
            }
            td, th {
                border: 1px solid black;
                text-align: center;
            }



            table {
                background-color: white;
                width: 100%;
                border: 3px solid red;
            }



            th, td {
                padding: 15px;
            }




            * {box-sizing: border-box;}



            .input-container {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                width: 100%;
                margin-bottom: 15px;
            }



            .icon {
                padding: 10px;
                background: red;
                color: black;
                min-width: 50px;
                text-align: center;
            }



            .input-field {
                width: 500px;
                padding: 10px;
                outline: none;
            }



            .input-field:focus {
                border: 3px solid gray;
            }

            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color:white ;
            }



            li {
                float: left;
            }



            a {
                display: block;
                color: black;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }



            li a:hover:not(.active) {
                background-color: black;
            }



            .active {
                background-color: red;
            }


            * {
                box-sizing: border-box;
            }

            body {
                background-color: steelblue;
                color: #fff;
                font-family: 'Muli', sans-serif;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                overflow: hidden;
                margin: 0;
            }

            

            button {
                cursor: pointer;
                display: inline-block;
                width: 100%;
                background: lightblue;
                padding: 15px;
                font-family: inherit;
                font-size: 16px;
                border: 0;
                border-radius: 5px;
            }

            button:focus {
                outline: 0;
            }

            button:active {
                transform: scale(0.98);
            }

            
            form {
                position: relative;
                margin: 20px 0 40px;
                width: 300px;
            }

            form input {
                background-color: transparent;
                border: 0;
                border-bottom: 2px #fff solid;
                display: block;
                width: 100%;
                padding: 15px 0;
                font-size: 18px;
                color: #fff;
            }

            form input:focus,
            form input:valid {
                outline: 0;
                border-bottom-color: lightblue;
            }

            
        </style>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form method="POST" action="login">
            <label for="email">Email:</label> <input name="email" type="text">
            <br>
            <label for="password">Password:</label> <input name="password" type="password" >
            <br>
            <input type="submit" value="Log in">

        </form>
        ${failed}

        <br><a href="register">Register Now</a>
    </body>
</html>
