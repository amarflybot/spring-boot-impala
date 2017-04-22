package com.example;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by amarendra on 22/04/17.
 */
@RequestMapping("/apimvc")
@RestController
public class WebStatResourceMvc {

    private static Logger logger = LoggerFactory.getLogger(WebStatResourceMvc.class);

    @Autowired
    private WebStatDao webStatDao;

    @GetMapping
    public ResponseEntity<?> getTest(){
        Gson gson = new Gson();
        String amar = gson.toJson("Amar");
        return ResponseEntity.ok(amar);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public void getSomething(HttpServletResponse response) throws SQLException, IOException {
        logger.info("new Request came to Get All!");
        PrintWriter writer = response.getWriter();
        webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT", new Object[]{}, writer);
    }

    @GetMapping("/getByDomain/{domain}")
    @ResponseBody
    public void getByDomain(@PathVariable String domain, HttpServletResponse response) throws SQLException, IOException {
        logger.info("new Request came to getByDomain!");
        PrintWriter writer = response.getWriter();
        webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT where DOM = ?", new Object[]{domain}, writer);
    }

    @GetMapping("//getByHost/{host}")
    @ResponseBody
    public void getByHost(@PathVariable String host,  HttpServletResponse response) throws SQLException, IOException {
        logger.info("new Request came to getByHost!");
        PrintWriter writer = response.getWriter();
        webStatDao.getStreamingOutputForSql("SELECT * FROM WEB_STAT where HOST = ?", new Object[]{host}, writer);
    }
}
