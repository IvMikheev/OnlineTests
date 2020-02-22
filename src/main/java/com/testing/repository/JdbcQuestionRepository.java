package com.testing.repository;

import com.testing.domain.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Question question) {
        String sql = "INSERT INTO question(text) VALUES (?)";
        jdbcTemplate.update(sql, question.getText());
    }

    @Override
    public List<String> findFiveRandomQuestions() {
        String sql = "SELECT id, text FROM question ORDER BY RAND() LIMIT 5";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("text"));
    }

    @Override
    public List<String> findQuestionByText(String text) {
        String sql = "SELECT text FROM question WHERE text = '" + text + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(text));
    }
}
