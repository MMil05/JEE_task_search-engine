package com.infoshareacademy.searchengine.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome-user")
public class WelcomeUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name") == null) {
            // resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        req.setAttribute("name", req.getParameter("name"));
        req.getSession().setAttribute("sessionName", req.getParameter("name"));

        /* ponizszy setAttribute jest juz niepotrzebny, bo w filtrze `SalaryIncrementFilter`
         jest wykonywany servletRequest.setAttribute("salary", tmpSalary);
        // req.setAttribute("salary", req.getParameter("salary"));  */
        req.getSession().setAttribute("sessionSalary", req.getParameter("salary"));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/welcome-user.jsp");
        requestDispatcher.forward(req, resp);

         /*   PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><html><body> czesc: "+req.getParameter("name")+"</body></html>");
          */
    }
}
