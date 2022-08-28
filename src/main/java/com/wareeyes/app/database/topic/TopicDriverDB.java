package com.wareeyes.app.database.topic;

import com.wareeyes.app.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// database driver class for accessing the topic table
@Repository
public class TopicDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // INSERT SQL query to insert a new topic object to database
    public int insertTopic(Topic topic) {
        String query = "INSERT INTO TOPIC  (name, threshold, partitions, replicationFactor) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, topic.getName(), topic.getThreshold(), topic.getPartitions(), topic.getReplicationFactor());
    }

    // DELETE SQL query to delete specific topic row from database
    public int deleteTopic(Topic topic) {
        String query = "DELETE FROM TOPIC WHERE name = ?";
        try {
            int result = jdbcTemplate.update(query, topic.getName());
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // UPDATE SQL query to update new column values of a specific topic from database
    public int modifyTopic(Topic topic) {
        String query = "UPDATE TOPIC SET threshold = ?, partitions = ?, replicationFactor = ? WHERE name = ?";
        System.out.println(topic);
        return jdbcTemplate.update(query, topic.getThreshold(), topic.getPartitions(), topic.getReplicationFactor(), topic.getName());
    }

    // SELECT SQL query to select the threshold value of a specific topic from database
    public int getTopicThreshold(Topic topic) {
        String query = "SELECT * FROM TOPIC WHERE name = ?";
        Topic newTopic = jdbcTemplate.queryForObject(query, new TopicRowMapper(), topic.getName());
        return (int) newTopic.getThreshold();
    }

    // SELECT SQL query to select all Kafka topics in the database
    public List<Topic> selectAllTopics() {
        String query = "SELECT * FROM TOPIC";
        List<Topic> list = jdbcTemplate.query(query, new TopicRowMapper());
        return list;
    }

}
