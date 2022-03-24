package ru.bk.artv.cityregister.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.bk.artv.cityregister.domain.PersonRequest;
import ru.bk.artv.cityregister.domain.PersonResponse;
import ru.bk.artv.cityregister.exception.PersonCheckException;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonCheckDaoTest {

    @Test
    public void checkPerson() throws PersonCheckException, SQLException {
        PersonRequest pr = new PersonRequest();
        pr.setSurname("Васильев");
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse ps = dao.checkPerson(pr);
        assertTrue(ps.isRegistered());
        assertFalse(ps.isTemporal());
    }
    @Test
    public void checkPerson2() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurname("Васильева");
        pr.setGivenName("Ирина");
        pr.setPatronymic("Петровна");
        pr.setDateOfBirth(LocalDate.of(1997, 8, 21));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse ps = dao.checkPerson(pr);
        assertTrue(ps.isRegistered());
        assertFalse(ps.isTemporal());
    }
}