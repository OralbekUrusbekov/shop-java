package kz.com.project.service;

import kz.com.project.Dto.CatDTO;
import kz.com.project.model.Cat;

import java.util.List;

public interface CatService {

    List<CatDTO> getAll();

    CatDTO getById(Long id);

    CatDTO create(CatDTO dto);

    CatDTO update(Long id, CatDTO dto);

    void delete(Long id);

    public Cat getEntity(Long id);

    public void save(Cat cat);
}