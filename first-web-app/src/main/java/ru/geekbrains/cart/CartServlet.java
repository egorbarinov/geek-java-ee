package ru.geekbrains.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/http-servlet/cart/","/http-servlet/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cart", "Корзина");
        getServletContext().getRequestDispatcher("/cart").include(req, resp);

        resp.getWriter().println("<ul>");
        resp.getWriter().println("<li><a href='" + getServletContext().getContextPath() + "/http-servlet/main'> Главная");
        resp.getWriter().println("<li><a href='" + getServletContext().getContextPath() + "/http-servlet/catalog'> Каталог");
        resp.getWriter().println("<li><a href='" + getServletContext().getContextPath() + "/http-servlet/products'> Товар");
        resp.getWriter().println("<li><a href='" + getServletContext().getContextPath() + "/http-servlet/cart'> Корзина");
        resp.getWriter().println("<li><a href='" + getServletContext().getContextPath() + "/http-servlet/order'> Заказ");
        resp.getWriter().println("</ul>");
    }
}
