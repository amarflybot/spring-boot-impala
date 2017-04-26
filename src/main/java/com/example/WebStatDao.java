package com.example;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by amarendra on 21/4/17.
 */
@Repository
public class WebStatDao {

    @Autowired
    JdbcStream jdbcStream;

    public void getStreamingOutputForSql(String sql, Object[] args, PrintWriter writer) throws SQLException {
        JdbcStream.StreamableQuery streamableQuery = jdbcStream.streamableQuery(sql, args);
        Gson gson = new Gson();
        writer.write("[");
        final boolean[] first = {false};
        try {
            streamableQuery
                    .stream()
                    .forEach(sqlRow -> {
                        try {
                            WebLog webLog = WebLogMapper.mapWebLog(sqlRow);
                            if (first[0]) {
                                writer.write(",");
                            }
                            first[0] = true;
                            writer.write(gson.toJson(webLog));
                            writer.flush();
                            //TimeUnit.MILLISECONDS.sleep(500);
                            //return webLog;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("Cannot convert SqlRom to WebLog");
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.write("]");
        writer.flush();
        writer.close();
        streamableQuery.close();
    }
}
