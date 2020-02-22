package com.testing.repository;

import com.testing.domain.UserAnswer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserAnswerRepository implements UserAnswerRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserAnswerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserAnswer> findLastResultByUsername(String username) {
        String sql = "SELECT * FROM useranswer WHERE username = '" + username + "' ORDER BY id DESC LIMIT 5";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new UserAnswer(rs.getLong("id"),
                               rs.getString("username"),
                               rs.getString("questions"),
                               rs.getString("answers"),
                               rs.getBoolean("correct")));
    }

    @Override
    public List<UserAnswer> findAllResultsByUsername(String username) {
        String sql = "SELECT * FROM useranswer WHERE username = '" + username + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new UserAnswer(rs.getLong("id"),
                               rs.getString("username"),
                               rs.getString("questions"),
                               rs.getString("answers"),
                               rs.getBoolean("correct")));
    }

    @Override
    public void save(List<UserAnswer> userAnswers) {
        String sql = "INSERT INTO useranswer (username, questions, answers, correct) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, userAnswers.get(i).getUsername());
                ps.setString(2, userAnswers.get(i).getQuestion());
                ps.setString(3, userAnswers.get(i).getAnswer());
                ps.setBoolean(4, userAnswers.get(i).isCorrect());
            }

            public int getBatchSize() {
                return userAnswers.size();
            }
        });
    }
}
