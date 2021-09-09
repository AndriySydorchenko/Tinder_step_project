<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
<#--    <link href="/css/bootstrap.min.css" rel="stylesheet">-->
    <#include "/css/bootstrap.min.css">
    <#include "/css/style.css">
    <!-- Custom styles for this template -->
<#--    <link rel="stylesheet" href="/css/style.css" type="text/css">-->
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <form method="post" id ="idOfYourForm">
                            <table class="table-users table" border="0">
                                <tbody>
                                <#list users as user>
                                            <tr class ="button"  name="id" value ="${user.id}">
                                                <td width="10">
                                                    <div class="avatar-img">
                                                        <img class="img-circle" src=${user.photo} />&nbsp;&nbsp;
                                                    </div>

                                                </td>
                                                <td class="align-middle">
                                                    ${user.name} ${user.surname}
                                                </td>
                                                <td class="align-middle">
                                                    ${user.profession}
                                                </td>
                                                <td  class="align-middle">
                                                    Last Login:  6/10/2017<br><small class="text-muted">5 days ago</small>
                                                </td>
                                            </tr>
                                </#list>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    const row = document.querySelectorAll('.button');
    const form = document.getElementById("idOfYourForm");
    for (let i = 0; i < row.length; i++) {
        row[i].onclick = function(){
            const hiddenField = document.createElement("input");
            hiddenField.setAttribute("name", row[i].getAttribute("name"));
            hiddenField.setAttribute("value", row[i].getAttribute("value"));
            form.appendChild(hiddenField);
            form.submit();
        };
    }

</script>
</body>
</html>