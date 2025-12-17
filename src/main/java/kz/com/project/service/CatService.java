package kz.com.project.service;


import kz.com.project.Dto.CatDTO;
import kz.com.project.mapper.CatMapper;
import kz.com.project.model.Cat;
import kz.com.project.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper;

    public List<CatDTO> getAll() {
        return catMapper.toDtoList(catRepository.findAll());
    }

    public CatDTO getById(Long id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
        return catMapper.toDto(cat);
    }

    public CatDTO create(CatDTO dto) {

        Cat cat = new Cat();
        cat.setImageUrl(dto.getImageUrl());
        cat.setPrice(dto.getPrice());
        cat.setName(dto.getName());
        cat.setBreed(dto.getBreed());
        cat.setAge(dto.getAge());
        if (dto.getImageUrl() != null) {
            cat.setImageUrl(dto.getImageUrl());
        }
        return catMapper.toDto(catRepository.save(cat));
    }

    public CatDTO update(Long id, CatDTO dto) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));

        cat.setName(dto.getName());
        cat.setBreed(dto.getBreed());
        cat.setAge(dto.getAge());
        cat.setPrice(dto.getPrice());

        if (dto.getImageUrl() != null) {
            cat.setImageUrl(dto.getImageUrl());
        }

        return catMapper.toDto(catRepository.save(cat));
    }

    public List<CatDTO> getAllCats(){
        return catMapper.toDtoList(catRepository.findAll());
    }

    public void delete(Long id) {
        catRepository.deleteById(id);
    }

    public Cat getEntity(Long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
    }

    public void save(Cat cat) {
        catRepository.save(cat);
    }
}
