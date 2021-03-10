package geekbrains.ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import geekbrains.ru.geekbrains.persist.Role;
import geekbrains.ru.geekbrains.persist.User;
import geekbrains.ru.geekbrains.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        if (userRepository == null) {
            throw new ServletException("UserRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user/user.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            User user = userRepository.findById(id);
            if (user == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user/user_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            userRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } else if (req.getPathInfo().equals("/add")) {
            getServletContext().getRequestDispatcher("/WEB-INF/user/add_user.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        if (req.getPathInfo().equals("/edit")) {
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            String pwd;
            Role role;
            if (req.getParameter("password_new").isBlank() || req.getParameter("password_new").isEmpty()) {
                pwd = userRepository.findById(id).getPwd();
            } else pwd = req.getParameter("password_new");
            if (req.getParameter("role").isBlank() || req.getParameter("role").isEmpty()) {
                role = userRepository.findById(id).getRole();
            } else role = Role.valueOf(req.getParameter("role"));

            User user = new User(id, req.getParameter("name"), pwd, role);
            userRepository.saveOrUpdate(user);
            resp.sendRedirect(getServletContext().getContextPath() + "/user");

        } else if (req.getPathInfo().equals("/add")) {
            User user = new User(null, req.getParameter("name"), req.getParameter("password_new"), Role.valueOf(req.getParameter("role")));
            userRepository.saveOrUpdate(user);
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        }
    }
}
