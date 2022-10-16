<%@ page import="com.technology.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.14/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,800&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/styleTables.css">
    <title>Users</title>
</head>
<body>
<div class="container p-30">
    <div class="row">
        <div class="col-md-12 main-datatable">
            <div class="card_body">
                <div class="row d-flex">
                    <div class="col-sm-12 add_flex">
                        <div class="form-group searchInput">
                            <input type="search" class="form-control" id="filterbox" placeholder=" " name="search">
                        </div>
                    </div>
                </div>
                <div class="overflow-x">
                    <table style="width:100%;"
                           id="filtertable"
                           class="table cust-datatable dataTable no-footer"
                           aria-describedby="users">
                        <thead>
                        <tr>
                            <th style="min-width:50px;">ID</th>
                            <th style="min-width:150px;">Name</th>
                            <th style="min-width:150px;">Password</th>
                            <th style="min-width:100px;">Date</th>
                            <th style="min-width:150px;">Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%List<User> users = (List<User>) request.getAttribute("users");
                            for (int i = 0; i < users.size(); i++) {%>
                        <tr>
                            <td >
                                <%=i + 1%>
                            </td>
                            <td>
                                <%=users.get(i).getName()%>
                            </td>
                            <td>
                                <%=users.get(i).getPassword()%>
                            </td>
                            <td>
                                <%=LocalDate.now()%>
                            </td>
                            <td><span class="mode mode_on">Active</span></td>
                        </tr>
                        <%} %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
