package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.sql.SQLException;

/**
 * Created by amarendra on 21/4/17.
 */
@Path("/api")
public class WebStatResource {
    private static Logger logger = LoggerFactory.getLogger(WebStatResource.class);

    @Autowired
    private WebStatDao webStatDao;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSomething() throws SQLException {
        logger.info("new Request came to Get All!");
        StreamingOutput outputForSql = webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT", new Object[]{});
        return Response.ok(outputForSql).build();
    }

    @GET
    @Path("/getByDomain/{domain}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDomain(@PathParam("domain") String domain) throws SQLException {
        logger.info("new Request came to Get By Domain!");
        StreamingOutput outputForSql = webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT where DOMAIN = ?", new Object[]{domain});
        return Response.ok(outputForSql).build();
    }

    @GET
    @Path("/getByHost/{host}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByHost(@PathParam("host") String host) throws SQLException {
        logger.info("new Request came to Get By host!");
        StreamingOutput outputForSql = webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT where HOST = ?", new Object[]{host});
        return Response.ok(outputForSql).build();
    }
}
