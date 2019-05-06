package org.fundacionjala.calculator.pages;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** This class contains unit test for HelloServlet .**/
public class HelloServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    /** Before hook. **/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /** Test to verify get method. **/
    @Test
    public void doGet() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);

        when(this.response.getWriter()).thenReturn(printWriter);

        new HelloServlet().doGet(this.request, this.response);

        assertEquals("Hello, World!", stringWriter.toString());
    }

    /** Test to verify post method. **/
    @Test
    public void doPostWithoutName() throws Exception {
        when(this.request.getRequestDispatcher("response.jsp"))
            .thenReturn(this.requestDispatcher);

        new HelloServlet().doPost(this.request, this.response);

        verify(this.request).setAttribute("user", "World");
        verify(this.requestDispatcher).forward(this.request, this.response);
    }

    /** Test to verify post method. **/
    @Test
    public void doPostWithName() throws Exception {
        when(this.request.getParameter("name")).thenReturn("Dolly");
        when(this.request.getRequestDispatcher("response.jsp"))
            .thenReturn(this.requestDispatcher);

        new HelloServlet().doPost(this.request, this.response);

        verify(this.request).setAttribute("user", "Dolly");
        verify(this.requestDispatcher).forward(this.request, this.response);
    }
}
