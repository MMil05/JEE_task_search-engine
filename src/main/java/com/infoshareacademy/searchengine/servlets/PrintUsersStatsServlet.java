package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.SearchStatistics;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("PrintStats")
public class PrintUsersStatsServlet extends HttpServlet {

    @EJB
    private SearchStatistics statsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.setContentType("text/html;charset=UTF-8");

        HashMap<User, Integer> stats =  statsBean.getStatsRepo();

        req.setAttribute("statList", stats);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users-list.jsp");
        requestDispatcher.forward(req, resp);
    /*    PrintWriter printWriter = resp.getWriter();

        StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html><html><body>");
        stringBuilder.append("Statystyki:<br><br>")
                .append(statsBean.printStatistics())
                .append("</body></html>");

        printWriter.println(stringBuilder);
    */

    }
}
