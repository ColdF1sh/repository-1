package com.company;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {

    String email;
    String pass;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

         email = request.getParameter("email");
         pass = request.getParameter("pass");
        if(checkUser())
        {
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }
    }
    public boolean checkUser(){
    boolean st =false;
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql:/ /https://databases-auth.000webhost.com:3306/id12703259_javajunior",
                "id12703259_javajunior",
                "javajunior123");
        PreparedStatement ps = con.prepareStatement("select * from users where email=? and pass=?");
        ps.setString(1, email);
        ps.setString(2, pass);
        ResultSet rs =ps.executeQuery();
        st = rs.next();

    }
        catch(Exception e) {
        e.printStackTrace();
    }
        return st;
}
}