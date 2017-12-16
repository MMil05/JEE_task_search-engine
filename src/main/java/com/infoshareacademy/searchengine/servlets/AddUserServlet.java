package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao daoBean;

    private String name;
    private String surname;
    private String login;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!areParamsValid(req)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        if (userAlreadyExists(id)) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        addUserFromRequest(req, id);
        printAddedUser(resp, id);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!areParamsValid(req)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        if (userAlreadyExists(id)) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        addUserFromRequest(req, id);
        printAddedUser(resp, id);

    }

    private boolean areParamsValid(HttpServletRequest req) {

        return !(
                (req.getParameter("id") == null) ||
                        (req.getParameter("name") == null) ||
                        (req.getParameter("surname") == null) ||
                        (req.getParameter("age") == null) ||
                        (req.getParameter("login") == null) ||
                        (req.getParameter("gender") == null)
        );
    }


    private boolean userAlreadyExists(int userId) {
        return (daoBean.getUserById(userId) != null);
    }

    private void addUserFromRequest(HttpServletRequest req, int id) {
        int age = Integer.parseInt(req.getParameter("age"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        Gender gender = null;
        if (req.getParameter("gender") == "MAN")
            gender = Gender.MAN;
        else if (req.getParameter("gender") == "WOMAN")
            gender = Gender.WOMAN;
        else gender = null;

        daoBean.addUser(new User(id, name, surname, login, age, gender));
    }

    private void printAddedUser(HttpServletResponse resp, int userId) throws IOException {
        User user = daoBean.getUserById(userId);

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body> Dodano uzytkownika: "
                + user.getName() + " " + user.getSurname() + " o loginie: "
                + user.getLogin()
                + "</body></html>");
    }
}
