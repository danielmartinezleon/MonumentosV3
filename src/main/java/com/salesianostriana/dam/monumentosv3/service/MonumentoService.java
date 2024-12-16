package com.salesianostriana.dam.monumentosv3.service;

import com.salesianostriana.dam.monumentosv3.model.Monumento;
import com.salesianostriana.dam.monumentosv3.repository.MonumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonumentoService {

    private final MonumentoRepository monumentoRepository;

    public Monumento addMonumento(Monumento monumento) {
        if (monumentoRepository.get(monumento.getId()).isPresent()) {
            throw new IllegalArgumentException("El monumento con este ID ya existe");
        }
        return monumentoRepository.add(monumento);
    }

    public List<Monumento> getAllMonumentos() {
        return monumentoRepository.getAll();
    }

    public Optional<Monumento> getMonumentoById(Long id) {
        return monumentoRepository.get(id);
    }

    public Optional<Monumento> editMonumento(Long id, Monumento nuevoMonumento) {
        if (!monumentoRepository.get(id).isPresent()) {
            throw new IllegalArgumentException("No se encontró el monumento con el ID especificado");
        }
        return monumentoRepository.edit(id, nuevoMonumento);
    }

    public void deleteMonumento(Long id) {
        if (!monumentoRepository.get(id).isPresent()) {
            throw new IllegalArgumentException("No se encontró el monumento con el ID especificado para eliminar");
        }
        monumentoRepository.delete(id);
    }
}
