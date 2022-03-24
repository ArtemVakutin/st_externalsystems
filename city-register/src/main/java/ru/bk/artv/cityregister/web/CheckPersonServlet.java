package ru.bk.artv.cityregister.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bk.artv.cityregister.dao.PersonCheckDao;
import ru.bk.artv.cityregister.dao.PoolConnectionBuilder;
import ru.bk.artv.cityregister.domain.PersonRequest;
import ru.bk.artv.cityregister.domain.PersonResponse;
import ru.bk.artv.cityregister.exception.PersonCheckException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {
    private static PersonCheckDao dao;
    private static Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    @Override
    public void init() throws ServletException {
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
        logger.info("Servlet is created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        PersonRequest pr = new PersonRequest();
        pr.setSurname(req.getParameter("surname"));
        pr.setGivenName(req.getParameter("givenName"));
        pr.setPatronymic(req.getParameter("patronymic"));
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        pr.setDateOfBirth(dateOfBirth);
        pr.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        pr.setBuilding(req.getParameter("building"));
        pr.setExtension(req.getParameter("extension"));
        pr.setApartment(req.getParameter("apartments"));
        System.out.println(pr);

        try {
            PersonResponse ps = dao.checkPerson(pr);
            if(ps.isRegistered()){
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("registered " + pr.getSurname() + " " + pr.getGivenName());
            } else {
                resp.getWriter().write("not registered");
            }
        } catch (PersonCheckException e) {
            e.printStackTrace();
            resp.getWriter().write("BUG");
        }

    }
}
