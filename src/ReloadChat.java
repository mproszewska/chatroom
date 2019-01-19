import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class ReloadChat extends HttpServlet {
    final String DB_URL = "jdbc:postgresql://localhost/mpro";
    final String USER = "mpro";
    final String PASS = "pass";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Connection conn;
        try {
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

            String query = "SELECT * from messages ORDER BY 1 LIMIT 100";
            Statement ps = conn.createStatement();

            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {

                String login = rs.getString(2);
                String msg = rs.getString(3);
                String date = rs.getString(4);
                String time = rs.getString(5);
                List<String> msgList = Arrays.asList(msg.split("[:][)]"));
                String addEmoji = "<img src='img/emoji.jpg' style='height:12px;width:12px;'/>";

                out.println("<p><font size=1>"+date+" "+time+"</font><font size=2><b> "+login+"</b><g>");
                for(int i=0;i<msgList.size();i++){
                    out.println(msgList.get(i));
                    if(i<msgList.size()-1)out.println(addEmoji);
                }
                if(msg.length()>=2 && msg.substring(msg.length()-2).equals(":)")) out.println(addEmoji);
                out.println("</g></font></p>");
            }
            sleep(1000);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
