package kz.com.project.service;

import kz.com.project.Dto.CatDTO;

import java.util.List;

public interface CatService {

    List<CatDTO> getAll();

    CatDTO getById(Long id);

    CatDTO create(CatDTO dto);

    CatDTO update(Long id, CatDTO dto);

    void delete(Long id);

    public Cat getEntity(Long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
    }

    public void save(Cat cat) {
        catRepository.save(cat);
    }
}