package ru.bk.artv.cityregister.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bk.artv.cityregister.dao.PersonCheckDao;
import ru.bk.artv.cityregister.dao.PoolConnectionBuilder;
import ru.bk.artv.cityregister.domain.PersonRequest;
import ru.bk.artv.cityregister.domain.PersonResponse;
import ru.bk.artv.cityregister.exception.PersonCheckException;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
@Singleton //чтобы объект не убивался каждый раз
@Path("/check")
public class CheckPersonService {
    private static Logger logger = LoggerFactory.getLogger(CheckPersonService.class);
    private static PersonCheckDao dao;
    @PostConstruct
    public void init(){
        logger.info("Start");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @PreDestroy
    public void destroy(){
        logger.info("Destroyed");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{id}") // ссылка первая после check будет восприниматься как id
    @Produces(MediaType.APPLICATION_JSON) //преобразовать в JSON возвращаемый параметр
    public PersonResponse checkPerson(PersonRequest pr) throws PersonCheckException {
        logger.info(pr.toString());
        return dao.checkPerson(pr);

    }
}
