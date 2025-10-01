package com.banky.pret.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banky.pret.repository.TypeInteretRepository;
import com.banky.pret.model.TypeInteret;

@Service
public class TypeInteretService {
    private TypeInteretRepository typeInteretRepository;

    public TypeInteretService(TypeInteretRepository typeInteretRepository) {
        this.typeInteretRepository = typeInteretRepository;
    }

    public TypeInteret addTypeInteret(TypeInteret tI) {
        return typeInteretRepository.save(tI);
    }

    public List<TypeInteret> getAllTypeInteret() {
        return typeInteretRepository.findAll();
    }

    public TypeInteret updateTypeInteret(TypeInteret tI) {
        return typeInteretRepository.save(tI);
    }

    public void deleteTypeInteretById(Long id) {
        typeInteretRepository.deleteById(id);
    }

}
