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
        prepareAndDispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareAndDispatch(req, resp);
    }

    private void prepareAndDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<User, Integer> stats = statsBean.getStatsRepo();

        req.setAttribute("statList", stats);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
