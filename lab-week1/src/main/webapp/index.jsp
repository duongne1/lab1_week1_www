<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
   <style>
       div{
           text-align: center;
           height: 400px;
       }
       input{
           height: 25px;

       }
   </style>
</head>
<body>
<div >
    <h1>LOGON</h1>
    <form action="logon" method="post">
        UserName: <label ><input type="text" name="UserName" ></label> <br> <br>
        Password:  <label ><input type="password" name="Password" ></label> <br> <br>
        <input type="submit" value="Submid">
        <input type="reset" value="Clear">
    </form>
</div>


</body>
</html>