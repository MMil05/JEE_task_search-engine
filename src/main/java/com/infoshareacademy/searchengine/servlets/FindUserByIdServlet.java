package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.cdibeans.MaxPulse;
import com.infoshareacademy.searchengine.dao.SearchStatistics;
import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.dao.UsersRepositoryDaoBean;
import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet  extends HttpServlet {

    @EJB
    private UsersRepositoryDao daoBean;
    @EJB
    private SearchStatistics searchStatsBean;

    @Inject
    private MaxPulse maxPulse;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = Integer.valueOf(req.getParameter("id"));
        if (userId == null) {
            // resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // UsersRepositoryDao daoBean = new UsersRepositoryDaoBean();  <- to  zastapione przez @EJB
        User user = daoBean.getUserById(userId);

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            searchStatsBean.incStatCounter(userId);

            double pulse = user.getGender().equals(Gender.MAN) ?
                    maxPulse.calculateMaxPulseMen(user.getAge()) :
                    maxPulse.calculateMaxPulseWomen(user.getAge());

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><html><body> czesc: "
                    +user.getName() + ", Twoj maksymalny puls to: " + pulse
                    + ", liczba zapytan: " + searchStatsBean.getStatForUser(user)
                    +"</body></html>");
        }



    }
}
