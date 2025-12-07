package kz.com.project.service;


import kz.com.project.Dto.CatDTO;
import kz.com.project.model.Cat;
import kz.com.project.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    private CatDTO mapToDTO(Cat cat) {
        return new CatDTO(cat.getId(), cat.getName(), cat.getBreed(), cat.getAge(), cat.getPrice());
    }

    public List<CatDTO> getAllCats() {
        return catRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CatDTO getCatById(Long id) {
        return catRepository.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
    }

    public CatDTO createCat(CatDTO catDTO) {
        Cat cat = new Cat(
                null,
                catDTO.getName(),
                catDTO.getBreed(),
                catDTO.getAge(),
                catDTO.getPrice());
        return mapToDTO(catRepository.save(cat));
    }

    public CatDTO updateCat(Long id, CatDTO catDTO) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found"));
        cat.setName(catDTO.getName());
        cat.setBreed(catDTO.getBreed());
        cat.setAge(catDTO.getAge());
        cat.setPrice(catDTO.getPrice());
        return mapToDTO(catRepository.save(cat));
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }
}
