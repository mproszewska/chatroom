import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMsg extends HttpServlet {
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

            // insert message to table messages
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement s = conn.createStatement();

            String login = req.getParameter("login");
            String msg = req.getParameter("msg");
            if(msg.isEmpty()) return;
            Date date = new Date();
            SimpleDateFormat day = new SimpleDateFormat("dd/MMM/yy");
            SimpleDateFormat hour = new SimpleDateFormat("hh:mm:ss a");

            String query = "INSERT INTO messages(login,msg,date,time) VALUES('"+login+"','"+
                    msg+"','"+day.format(date)+"','"+hour.format(date)+"')";
            s.executeUpdate(query);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
