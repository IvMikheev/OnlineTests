package com.testing.repository;

import com.testing.domain.Answer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAnswerRepository implements AnswerRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAnswerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(List<Answer> answers) {
        String sql = "INSERT INTO answer (text, parent_question, correct_answer) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, answers.get(i).getText());
                ps.setString(2, answers.get(i).getParentQuestion());
                ps.setBoolean(3, answers.get(i).isCorrectAnswer());
            }

            public int getBatchSize() {
                return answers.size();
            }
        });
    }

    @Override
    public List<Answer> findByQuestion(String question) {
        String sql = "SELECT * FROM answer WHERE parent_question = '" + question + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Answer(rs.getLong("id"),
                           rs.getString("text"),
                           rs.getString("parent_question"),
                           rs.getBoolean("correct_answer")));
    }

    @Override
    public List<Answer> findCorrectAnswerByQuestion(String question) {
        String sql = "SELECT * FROM answer WHERE parent_question = '" + question + "' AND correct_answer = true";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Answer(rs.getLong("id"),
                           rs.getString("text"),
                           rs.getString("parent_question"),
                           rs.getBoolean("correct_answer")));
    }
}
