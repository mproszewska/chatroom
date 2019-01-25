
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

public class Chat extends HttpServlet {
    final String DB_URL = "jdbc:postgresql://localhost/user";
    final String USER = "user";
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

            // find user in table users
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement s = conn.createStatement();
            String login = req.getParameter("login");
            String pw = req.getParameter("pw");
            if(!login.matches("[a-zA-Z0-9 ]*")) return;
            if(!pw.matches("[a-zA-Z0-9]*")) return;
            String query = "SELECT login FROM users WHERE login='"+login+"' AND password='"+pw+"'";

            ResultSet rs = s.executeQuery(query);
            if(rs.next()) {
                login = rs.getString("login");
                HttpSession hs = req.getSession();
                hs.setAttribute("login", login);

                out.println("OK");
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

