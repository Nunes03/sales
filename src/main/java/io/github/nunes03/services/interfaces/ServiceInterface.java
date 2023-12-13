package io.github.nunes03.services.interfaces;

import java.util.List;

public interface ServiceInterface<E, I> {

    List<E> findAll();

    E findById(I identifier);

    E create(E entity);

    E update(I identifier, E entity);

    void deleteById(I identifier);
}
