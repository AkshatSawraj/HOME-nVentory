<%-- 
    Document   : report
    Created on : Nov 29, 2021, 1:47:43 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Page</title>
        <style>
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



            li a {
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
        </style>
    </head>
    <body>
        <h1>Excel Report</h1>
        <form method="post">
            <input type="submit" name="action" value="Click here to download CSV file">
        </form>
        <form method="post">
            <input type="submit" name="action" value="Click here to download CSV file of Active Users">
        </form>

    </body>
</html>
