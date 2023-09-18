package controls;

import dao.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/logon"})
public class LogonServlet extends HttpServlet {
    private UserDAO userDAO;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter out = resp.getWriter();
            HttpSession session = req.getSession(true);
            userDAO = new UserDAO(session);

            String us = req.getParameter("UserName");
            String pws = req.getParameter("Password");
            boolean kq = userDAO.logon(us,pws);
            if(!kq){
                resp.getWriter().println("<h1>Invalid username or password</h1>");
            }else{
              Object obj=  session.getAttribute("ds");
                if(obj==null){
                    User user = (User) session.getAttribute("us");
                    out.println(user);
                }else{
                    List<User> lst = (List<User>) obj;
                    lst.forEach(out::println);
                }

            }


            ArrayList<User> list = (ArrayList<User>) session.getAttribute("ds");

        }catch  (Exception e){
            e.printStackTrace();
        }
    }
}
