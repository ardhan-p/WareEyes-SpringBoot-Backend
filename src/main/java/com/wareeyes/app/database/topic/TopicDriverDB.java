package com.wareeyes.app.database.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO: insert SQL statements here to fetch and update topic object data
}
