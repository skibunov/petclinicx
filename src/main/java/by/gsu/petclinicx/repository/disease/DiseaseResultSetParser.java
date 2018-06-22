package by.gsu.petclinicx.repository.disease;

import by.gsu.petclinicx.model.Disease;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DiseaseResultSetParser
        implements RowMapper<Disease> {

    @Nullable
    @Override
    public Disease mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        return new Disease(id, name);
    }

}
