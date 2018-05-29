package by.gsu.petclinicx.repository.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.List;

@Component
public class QueryExecutor {

    private IdResultSetParser idRSParser;
    private Connection conn;
    private JdbcTemplate jdbcTemplate;

    public QueryExecutor(IdResultSetParser idRSParser,
                         JdbcTemplate jdbcTemplate) {
        this.idRSParser = idRSParser;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() throws SQLException {
        System.out.println("initializing connection");
        conn = DriverManager.getConnection("jdbc:sqlite:animals.db");
    }

    @PreDestroy
    public void destroy() throws SQLException {
        System.out.println("closing connection");
        conn.close();
    }

    public <T> List<T> getAll(String query, RowMapper<T> parser) {
        return jdbcTemplate.query(query, parser);

    }

    public <T> T getById(String query, RowMapper<T> parser) {
        List<T> list = getAll(query, parser);
        return list
                .stream()
                .findFirst()
                .orElse(null);
    }

    public int executeUpdate(String sql, PreparedStatementParamsProcessor paramsProcessor) {
        return jdbcTemplate.update(sql, paramsProcessor::process);
    }

    public long executeCreate(String sql, PreparedStatementParamsProcessor paramsProcessor) {
        try  {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            paramsProcessor.process(preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            return idRSParser.mapRow(rs, 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
