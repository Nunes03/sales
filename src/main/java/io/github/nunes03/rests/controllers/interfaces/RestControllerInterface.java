package io.github.nunes03.rests.controllers.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestControllerInterface<E, I> {

    @GetMapping()
    List<E> getAll();

    @GetMapping(value = "/{id}")
    E getById(@PathVariable(value = "id") I identifier);

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    E postCreated(@RequestBody E entity);

    @PutMapping(value = "/{id}")
    E putUpdated(@RequestBody E entity, @PathVariable(value = "id") I identifier);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable(value = "id") I identifier);
}
