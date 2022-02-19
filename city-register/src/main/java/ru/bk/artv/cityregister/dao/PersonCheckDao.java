package ru.bk.artv.cityregister.dao;

import ru.bk.artv.cityregister.domain.PersonRequest;
import ru.bk.artv.cityregister.domain.PersonResponse;
import ru.bk.artv.cityregister.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    public static final String SQL_CHECK_PERSON = " ";

    public PersonResponse checkPerson(PersonRequest personRequest) throws PersonCheckException {
        PersonResponse personResponse = new PersonResponse();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL_CHECK_PERSON) ) {
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                personResponse.setRegistered(true);
                personResponse.setTemporal(rs.getBoolean("temporal"));
            }

        } catch (SQLException e) {
            new PersonCheckException(e);

        }

        return personResponse;
    }

    private Connection getConnection() {
        return null;
    }

}
