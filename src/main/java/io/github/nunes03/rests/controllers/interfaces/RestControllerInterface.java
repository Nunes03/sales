package io.github.nunes03.rests.controllers.interfaces;

import java.util.List;

public interface RestControllerInterface<E, I> {

    List<E> getAll();

    E getById(I identifier);

    E postCreated(E entity);

    E putUpdated(E entity, I identifier);

    void deleteById(I identifier);
}
