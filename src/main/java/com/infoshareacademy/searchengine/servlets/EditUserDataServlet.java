package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("EditUser")
public class EditUserDataServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao usersRepoDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isParamValid(req, resp)) {
            User user = usersRepoDaoBean.getUserById(Integer.parseInt(req.getParameter("id")));
            req.getSession().setAttribute("edited_user", user);
            req.getSession().setAttribute("gender", user.getGender().equals(Gender.MAN) ? "MAN" : "WOMAN");
            req.getSession().setAttribute("edit_user_data", 1);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/add-user-step-1.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private boolean isParamValid(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = (req.getParameter("id") != null)
                && (userExists(Integer.parseInt(req.getParameter("id"))));

        if (!valid)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return valid;
    }

    private boolean userExists(int userId) {
        return (usersRepoDaoBean.getUserById(userId) != null);
    }
}
