package servlet;
 
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import bean.User;
import dao.UserDAO;
 
public class UserListServlet extends HttpServlet {
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
         
        List<User> users = new UserDAO().list();
 
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>id</td><td>name</td></tr>\r\n");
 
        String trFormat = "<tr><td>%d</td><td>%s</td></tr>\r\n";
 
        for (User hero : users) {
            String tr = String.format(trFormat, hero.getId(), hero.getName());
            sb.append(tr);
        }
 
        sb.append("</table>");
 
        response.getWriter().write(sb.toString());
 
    }
}