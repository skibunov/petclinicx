package by.gsu.petclinicx.repository.common;

import java.sql.ResultSet;
import java.util.List;

@FunctionalInterface
public interface ResultSetParser<T> {

    List<T> parse(ResultSet rs);

    default T parseOne(ResultSet rs) {
        List<T> list = parse(rs);
        return list
                .stream()
                .findFirst()
                .orElse(null);
    }
}
