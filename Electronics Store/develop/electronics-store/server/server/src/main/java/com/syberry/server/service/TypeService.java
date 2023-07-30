package com.syberry.server.service;

import com.syberry.server.entity.Type;
import com.syberry.server.exception.TypeAlreadyExistsException;

public interface TypeService {
    Type getById(Long id);
    Iterable<Type> getTypes();
    Type getByName(String name);
    Type addType(Type type);
    Long deleteById(Long id);
    Type updateType(Type newType, Long id);
}
