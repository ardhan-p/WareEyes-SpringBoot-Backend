package com.wareeyes.app.database.topic;

import com.wareeyes.app.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertTopic(Topic topic) {
        String query = "INSERT INTO TOPIC (name, threshold, partitions, replicationFactor)";
        return jdbcTemplate.update(query, topic.getName(), topic.getThreshold(), topic.getPartitions(), topic.getReplicationFactor());
    }

//    public int deleteTopic(Topic topic) {
//
//    }

    public List<Topic> selectAllTopics() {
        String query = "SELECT * FROM TOPIC";
        List<Topic> list = jdbcTemplate.query(query, new TopicRowMapper());
        return list;
    }

}
