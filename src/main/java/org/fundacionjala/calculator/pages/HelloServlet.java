package org.fundacionjala.calculator.pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** This class have the servlet for hello page. **/
@WebServlet(name = "HelloServlet", urlPatterns = {"hello"}, loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

    /**
     * Get method for /hello path.
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Hello, World!");
    }

    /**
     * Post method for /hello path.
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) {
            name = "World";
        }
        request.setAttribute("user", name);
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
}
