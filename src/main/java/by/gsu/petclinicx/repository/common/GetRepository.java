package by.gsu.petclinicx.repository.common;

import by.gsu.petclinicx.model.NamedEntity;

import java.util.List;

public interface GetRepository<T extends NamedEntity> {
    List<T> getAll();

    T getById(Long id);
}
