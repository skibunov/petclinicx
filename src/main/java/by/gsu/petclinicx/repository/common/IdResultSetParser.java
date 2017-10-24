package by.gsu.petclinicx.repository.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class IdResultSetParser implements ResultSetParser<Long> {
    @Override
    public List<Long> parse(ResultSet rs) {
        try {
            rs.next();
            return Collections.singletonList(rs.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
