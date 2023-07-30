package com.syberry.server.service.impl;

import com.syberry.server.entity.Type;
import com.syberry.server.exception.TypeAlreadyExistsException;
import com.syberry.server.exception.TypeNotFoundException;
import com.syberry.server.repo.TypeRepo;
import com.syberry.server.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepo typeRepo;

    @Override
    public Type getById(Long id) {
        return typeRepo.findById(id).orElseThrow(TypeNotFoundException::new);
    }

    @Override
    public Iterable<Type> getTypes() {
        return typeRepo.findAll();
    }

    @Override
    public Type getByName(String name) {
        return typeRepo.findByName(name).orElseThrow(TypeNotFoundException::new);
    }

    @Override
    public Type addType(Type type) {
        if (typeRepo.findByName(type.getName()).isPresent()) {
            throw new TypeAlreadyExistsException("Type with such name exists");
        }
        return typeRepo.save(type);
    }

    @Override
    public Long deleteById(Long id) {
        if (typeRepo.findById(id).isEmpty()) {
            throw new TypeNotFoundException();
        }
        typeRepo.deleteById(id);
        return id;
    }

    @Override
    public Type updateType(Type newType, Long id) {
        return typeRepo.findById(id)
                .map(type -> {
                    type.setName(newType.getName());
                    return typeRepo.save(type);
                })
                .orElseGet(() -> typeRepo.save(newType));
    }
}
