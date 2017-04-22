package com.example;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.sql.SQLException;

/**
 * Created by amarendra on 21/4/17.
 */
@Repository
public class WebStatDao {

    @Autowired
    JdbcStream jdbcStream;

    public StreamingOutput getStreamingOutputForSql(String sql, Object[] args) {
        JdbcStream.StreamableQuery streamableQuery = null;
        try {
            streamableQuery = jdbcStream.streamableQuery(sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StreamingOutput streamingOutput = getStreamingOutput(streamableQuery);
        streamableQuery.close();
        return streamingOutput;
    }

    private StreamingOutput getStreamingOutput(final JdbcStream.StreamableQuery streamableQuery) {

        Gson gson = new Gson();

        return new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException, WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write("[");
                final boolean[] first = {false};
                try {
                    streamableQuery
                            .stream()
                            .map(sqlRow -> {
                                try {
                                    WebStat webStat = WebStatMapper.mapWebStat(sqlRow);
                                    return webStat;
                                } catch (RuntimeException e) {
                                    throw new RuntimeException("Cannot convert SqlRom to WebStat");
                                }
                            }).reduce((webStat1, webStat2) -> {
                        Assert.notNull(webStat1, "Webstat cannot be null");
                        try {
                            if (first[0]) {
                                writer.write(",");
                            } else if (!first[0]){
                                writer.write(gson.toJson(webStat1));
                                writer.write(",");
                            }
                            first[0] = true;
                            writer.write(gson.toJson(webStat2));
                            writer.flush();
                            //TimeUnit.MILLISECONDS.sleep(500);
                        } catch (IOException e) {
                            throw new RuntimeException("Cannot write to Stream back");
                        }
                        return webStat1;
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                writer.write("]");
                writer.flush();
            }
        };
    }
}
