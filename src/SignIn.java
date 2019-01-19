
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignIn extends HttpServlet {
    final String DB_URL = "jdbc:postgresql://localhost/mpro";
    final String USER = "mpro";
    final String PASS = "pass";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        Connection conn;
        try{
            resp.setContentType("text/html");
            // setting up database
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                out.println("Include your PostrgreSQL JDBC Driver in your library path.");
                e.printStackTrace();
                return;
            }
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // add user if possible
            Statement s = conn.createStatement();
            String login = req.getParameter("login");
            String pw = req.getParameter("pw");

            String query = "SELECT login FROM users WHERE login='"+login+"'";
            ResultSet rs = s.executeQuery(query);

            if(!rs.next() && login.length()<=20) {
                query = "INSERT INTO users VALUES('" + login + "','" + pw + "')";
                HttpSession hs = req.getSession();
                hs.setAttribute("login", login);
                out.println("OK");
                s.executeUpdate(query);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
    }
}
