package com.testing.repository;

import com.testing.domain.Role;
import com.testing.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user(username, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole().name());
    }

    @Override
    public List<User> findByName(String username) {
        String sql = "SELECT * FROM user WHERE username = '" + username + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(rs.getLong("id"),
                         rs.getString("username"),
                         rs.getString("password"),
                         Role.valueOf(rs.getString("role"))));
    }
}
