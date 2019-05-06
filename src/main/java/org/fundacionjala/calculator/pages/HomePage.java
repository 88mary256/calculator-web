package org.fundacionjala.calculator.pages;

import org.fundacionjala.calculator.core.CalculatorUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** This class have the servlet for calculate page. **/
@WebServlet(
        name = "calculateservlet",
        urlPatterns = "/calculate"
)
public class HomePage extends HttpServlet {

    /**
     * Post method for /calculate path.
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String text = request.getParameter("text");

        final double result = CalculatorUtils.calculate(text);

        request.setAttribute("result", result);
        final RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}
