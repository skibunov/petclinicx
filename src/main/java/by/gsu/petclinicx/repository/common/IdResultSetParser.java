package by.gsu.petclinicx.repository.common;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IdResultSetParser implements RowMapper<Long> {

    @Nullable
    @Override
    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getLong(1);
    }
}
