package by.gsu.petclinicx.repository.common;

import java.sql.*;
import java.util.List;

public class QueryExecutor {

    private final IdResultSetParser idRSParser;

    public QueryExecutor(IdResultSetParser idRSParser) {
        this.idRSParser = idRSParser;
    }

    public <T> List<T> getAll(String query, ResultSetParser<T> parser) {
        try (Connection conn =
                     DriverManager.getConnection("jdbc:sqlite:animals.db")) {

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
        try (Connection conn =
                     DriverManager.getConnection("jdbc:sqlite:animals.db")) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            paramsProcessor.process(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long executeCreate(String sql, PreparedStatementParamsProcessor paramsProcessor) {
        try (Connection conn =
                     DriverManager.getConnection("jdbc:sqlite:animals.db")) {
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
