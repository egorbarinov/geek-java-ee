package ru.geekbrains.order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/http-servlet/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("order", "Заказ");
        getServletContext().getRequestDispatcher("/order").include(req, resp);
        resp.getWriter().println("<a href='./main'> Главная");
        resp.getWriter().println("<a href='./catalog'> Каталог");
        resp.getWriter().println("<a href='./product'> Товар");
        resp.getWriter().println("<a href='./cart'> Корзина");
        resp.getWriter().println("<a href='./order'> Заказ");
    }
}
