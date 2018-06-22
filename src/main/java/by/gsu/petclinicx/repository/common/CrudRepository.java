package by.gsu.petclinicx.repository.common;

import by.gsu.petclinicx.model.NamedEntity;

public interface CrudRepository<T extends NamedEntity> extends GetRepository<T> {
    int delete(Long id);
    int update(T t);
    T create(T t);
}
