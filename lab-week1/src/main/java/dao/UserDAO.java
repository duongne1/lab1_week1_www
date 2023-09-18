package dao;

import entity.User;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDAO {
    private Connection connection;
    private HttpSession session;
    public UserDAO(HttpSession session) throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/logondb","root", "sapassword");
        this.session = session;
    }
public boolean logon(String us, String psw) throws SQLException {
        String sql = "SELECT * FROM dbuser WHERE user_name =? AND psw =?";
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, us);
    ps.setString(2, psw);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        String role = rs.getString("role");
        if(role.equalsIgnoreCase("admin")){
            Statement stm = connection.createStatement();
          ResultSet rsU=   stm.executeQuery("select * from dbuser");
            List<User> list = new ArrayList<>();
            while(rsU.next()){
                User u= new User(rsU.getString(1),rsU.getString(1),
                        rsU.getString(1),
                        rsU.getString(1),
                        rsU.getString(1));
            list.add(u);

            }
            session.setAttribute("ds",list);
        }else{
            User u= new User(rs.getString(1),rs.getString(1),
                    rs.getString(1), rs.getString(1),
                    rs.getString(1));
            session.setAttribute("us",u);
        }
        return true;
    }
    return false;
}
}
