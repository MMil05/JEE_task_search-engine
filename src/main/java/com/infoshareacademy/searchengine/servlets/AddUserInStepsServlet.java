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
                if (!areParamsValidStep1(req, resp))
                    return;

                req.getSession().setAttribute("id", req.getParameter("id"));
                req.getSession().setAttribute("login", req.getParameter("login"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/add-user-step-2.jsp");
                requestDispatcher.forward(req, resp);
                return; // forward nie przerywa dzialania servleta
                // break;
            }
            case 2: {
                if (!areParamsValidStep2(req, resp))
                    return;

                req.getSession().setAttribute("name", req.getParameter("name"));
                req.getSession().setAttribute("surname", req.getParameter("surname"));
                req.getSession().setAttribute("age", req.getParameter("age"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/add-user-step-3.jsp");
                requestDispatcher.forward(req, resp);
                return;
            }
            case 3: {
                if (!areParamsValidStep3(req, resp))
                    return;

                if (req.getSession().getAttribute("edit_user_data") != null) {
                    updateUser(req, resp);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/PrintStats");
                    requestDispatcher.forward(req, resp);
                } else {
                    addUser(req, resp);
                }
                // req.getSession().invalidate();  //  this method invalidates the session and it removes all attributes from the session object
                break;
            }
        }
    }

    private boolean areParamsValidStep1(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = !((req.getParameter("id") == null)
                || (req.getParameter("login") == null)
                || req.getParameter("id").isEmpty()
                || req.getParameter("login").isEmpty()
        );

        if (!valid) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return valid;
        }

        if (req.getSession().getAttribute("edit_user_data") != null)
            return valid;

        int id = Integer.parseInt(req.getParameter("id"));
        valid = !userAlreadyExists(id);
        if (!valid) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return valid;
    }

    private boolean areParamsValidStep2(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = !((req.getParameter("name") == null)
                || req.getParameter("name").isEmpty()
                || (req.getParameter("surname") == null)
                || req.getParameter("surname").isEmpty()
                || (req.getParameter("age") == null)
                || req.getParameter("age").isEmpty()
        );

        if (!valid)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return valid;
    }

    private boolean areParamsValidStep3(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = req.getParameter("gender").equals("MAN")
                || req.getParameter("gender").equals("WOMAN");

        if (!valid)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return valid;
    }


    private boolean userAlreadyExists(int userId) {
        return (usersRepoDaoBean.getUserById(userId) != null);
    }


    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User newUser = new User();
        newUser.setId(Integer.parseInt((String) req.getSession().getAttribute("id")));

        setUserData(newUser, req);

        usersRepoDaoBean.addUser(newUser);
        printAddedUser(resp, newUser.getId());

    }


    private void printAddedUser(HttpServletResponse resp, int userId) throws IOException {
        User user = usersRepoDaoBean.getUserById(userId);

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body> Dodano uzytkownika: "
                + user.getName() + " " + user.getSurname() + " o loginie: `"
                + user.getLogin() + "`, wiek: " + user.getAge()
                + "</body></html>");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("edit_user_data", null);

        User user = (User) req.getSession().getAttribute("edited_user");
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        setUserData(user, req);
        req.getSession().invalidate();
    }

    private void setUserData(User user, HttpServletRequest req) {
        user.setLogin((String) req.getSession().getAttribute("login"));
        user.setName((String) req.getSession().getAttribute("name"));
        user.setSurname((String) req.getSession().getAttribute("surname"));
        user.setAge(Integer.parseInt((String) req.getSession().getAttribute("age")));

        Gender gender;
        if (req.getParameter("gender").equals("MAN")) {
            gender = Gender.MAN;
        } else if (req.getParameter("gender").equals("WOMAN")) {
            gender = Gender.WOMAN;
        } else {
            gender = null;
        }
        user.setGender(gender);
    }
}
