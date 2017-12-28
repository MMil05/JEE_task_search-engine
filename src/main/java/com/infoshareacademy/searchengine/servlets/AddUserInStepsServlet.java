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
import java.io.PrintWriter;

@WebServlet("/AddUserStepsServlet")
public class AddUserInStepsServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao usersRepoDaoBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int step = Integer.valueOf(req.getParameter("step"));

        switch (step) {
            case 1: {
                req.getSession().setAttribute("id", req.getParameter("id"));
                req.getSession().setAttribute("login", req.getParameter("login"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/add-user-step-2.jsp");
                requestDispatcher.forward(req, resp);
                return; // forward nie przerywa dzialania servleta
                // break;
            }
            case 2: {
                req.getSession().setAttribute("name", req.getParameter("name"));
                req.getSession().setAttribute("surname", req.getParameter("surname"));
                req.getSession().setAttribute("age", req.getParameter("age"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/add-user-step-3.jsp");
                requestDispatcher.forward(req, resp);
                return;  // forward nie przerywa dzialania servleta
                // break;
            }
            case 3: {
                req.getSession().setAttribute("gender", req.getParameter("gender"));
                User newUser = new User();
                newUser.setId(Integer.parseInt((String) req.getSession().getAttribute("id")));
                newUser.setLogin((String) req.getSession().getAttribute("login"));
                newUser.setAge(Integer.parseInt((String) req.getSession().getAttribute("age")));

                Gender gender;
                if (req.getParameter("gender").equals("MAN")) {
                    gender = Gender.MAN;
                } else if (req.getParameter("gender").equals("WOMAN")) {
                    gender = Gender.WOMAN;
                } else {
                    gender = null;
                }
                newUser.setGender(gender);

                newUser.setName((String) req.getSession().getAttribute("name"));
                newUser.setSurname((String) req.getSession().getAttribute("surname"));

                usersRepoDaoBean.addUser(newUser);
                printAddedUser(resp, newUser.getId());

                // req.getSession().invalidate();  //  this method invalidates the session and it removes all attributes from the session object
                break;
            }
        }
    }

    private void printAddedUser(HttpServletResponse resp, int userId) throws IOException {
        User user = usersRepoDaoBean.getUserById(userId);

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body> Dodano uzytkownika: "
                + user.getName() + " " + user.getSurname() + " o loginie: "
                + user.getLogin() + ", płeć: " + user.getGender()
                + "</body></html>");
    }
}
