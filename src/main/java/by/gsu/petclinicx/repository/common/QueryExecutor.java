package by.gsu.petclinicx.repository.common;

import java.sql.*;
import java.util.List;

public class QueryExecutor {

    private final IdResultSetParser idRSParser;
    private Connection conn;

    public QueryExecutor(IdResultSetParser idRSParser) {
        this.idRSParser = idRSParser;
    }

    public void init() throws SQLException {
        System.out.println("initializing connection");
        conn = DriverManager.getConnection("jdbc:sqlite:animals.db");
    }

    public void destroy() throws SQLException {
        System.out.println("closing connection");
        conn.close();
    }

    public <T> List<T> getAll(String query, ResultSetParser<T> parser) {
        try {
            Statement s3 = conn.createStatement();
            ResultSet rs = s3.executeQuery(query);

            List<T> result = parser.parse(rs);
            if (rs.isClosed()) {
                rs.close();
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getById(String query, ResultSetParser<T> parser) {
        List<T> list = getAll(query, parser);
        return list
                .stream()
                .findFirst()
                .orElse(null);
    }

    public int executeUpdate(String sql, PreparedStatementParamsProcessor paramsProcessor) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            paramsProcessor.process(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long executeCreate(String sql, PreparedStatementParamsProcessor paramsProcessor) {
        try  {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            paramsProcessor.process(preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            return idRSParser.parseOne(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
