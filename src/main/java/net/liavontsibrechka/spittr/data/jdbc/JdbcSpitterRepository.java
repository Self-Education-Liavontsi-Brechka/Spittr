package net.liavontsibrechka.spittr.data.jdbc;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSpitterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Spitter save(Spitter spitter) {
//        jdbcTemplate.update();
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Spitter findOne(long id) {
        return jdbcTemplate.queryForObject(
                "select id, username, password, firstName, lastName, email, updateByEmail from Spitter where id=?",
                this::mapSpitter, id);
    }

    @Override
    public List<Spitter> findAll() {
        return null;
    }

    private Spitter mapSpitter(ResultSet rs, int row) throws SQLException {
        return new Spitter(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("lastName"));
    }
}
