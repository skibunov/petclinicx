package by.gsu.petclinicx.repository.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementParamsProcessor {
    void process(PreparedStatement ps) throws SQLException;
}
