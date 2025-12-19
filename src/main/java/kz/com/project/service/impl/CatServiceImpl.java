package kz.com.project.service.impl;

import kz.com.project.Dto.CatDTO;
import kz.com.project.mapper.CatMapper;
import kz.com.project.model.Cat;
import kz.com.project.repository.CatRepository;
import kz.com.project.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper;

    @Override
    public List<CatDTO> getAll() {
        return catMapper.toDtoList(catRepository.findAll());
    }

    @Override
    public CatDTO getById(Long id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
        return catMapper.toDto(cat);
    }

    @Override
    public CatDTO create(CatDTO dto) {
        Cat cat = new Cat();
        cat.setName(dto.getName());
        cat.setBreed(dto.getBreed());
        cat.setAge(dto.getAge());
        cat.setPrice(dto.getPrice());
        cat.setImageUrl(dto.getImageUrl());

        return catMapper.toDto(catRepository.save(cat));
    }

    @Override
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

    @Override
    public void delete(Long id) {
        catRepository.deleteById(id);
    }
}
