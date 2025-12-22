package kz.com.project.service;

import kz.com.project.Dto.CatDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CatServiceTest {

    @Autowired
    private CatService catService;

    @Test
    void create_and_getById() {
        CatDTO dto = new CatDTO();
        dto.setName("Murka");
        dto.setBreed("Siam");
        dto.setAge(3);
        dto.setPrice(6000.0);
        dto.setImageUrl("img.png");

        CatDTO saved = catService.create(dto);
        CatDTO found = catService.getById(saved.getId());

        Assertions.assertEquals(dto.getName(), found.getName());
        Assertions.assertEquals(dto.getBreed(), found.getBreed());
        Assertions.assertEquals(dto.getAge(), found.getAge());
        Assertions.assertEquals(dto.getPrice(), found.getPrice());
        Assertions.assertEquals(dto.getImageUrl(), found.getImageUrl());
    }

    @Test
    void getAll_with_existing_data() {
        int initialSize = catService.getAll().size();

        CatDTO cat1 = new CatDTO();
        cat1.setName("Murka");
        cat1.setPrice(4000.0);

        CatDTO cat2 = new CatDTO();
        cat2.setName("Meow");
        cat2.setPrice(5000.0);

        catService.create(cat1);
        catService.create(cat2);

        List<CatDTO> cats = catService.getAll();

        Assertions.assertEquals(initialSize + 2, cats.size());
    }

    @Test
    void getAllCats_with_existing_data() {
        int initialSize = catService.getAll().size();

        CatDTO cat1 = new CatDTO();
        cat1.setName("Murka");
        cat1.setPrice(3500.0);

        CatDTO cat2 = new CatDTO();
        cat2.setName("Margau");
        cat2.setPrice(4500.0);

        catService.create(cat1);
        catService.create(cat2);

        List<CatDTO> cats = catService.getAll();

        Assertions.assertEquals(initialSize + 2, cats.size());
    }

    @Test
    void update_cat() {
        CatDTO dto = new CatDTO();
        dto.setName("Kindless");
        dto.setBreed("Kazakh");
        dto.setAge(2);
        dto.setPrice(3000.0);
        dto.setImageUrl("meow.png");

        CatDTO saved = catService.create(dto);

        CatDTO updateDto = new CatDTO();
        updateDto.setName("Happy");
        updateDto.setBreed("Usa");
        updateDto.setAge(3);
        updateDto.setPrice(5500.0);
        updateDto.setImageUrl("new.png");

        CatDTO updated = catService.update(saved.getId(), updateDto);

        Assertions.assertEquals(updateDto.getName(), updated.getName());
        Assertions.assertEquals(updateDto.getBreed(), updated.getBreed());
        Assertions.assertEquals(updateDto.getAge(), updated.getAge());
        Assertions.assertEquals(updateDto.getPrice(), updated.getPrice());
        Assertions.assertEquals(updateDto.getImageUrl(), updated.getImageUrl());
    }

    @Test
    void delete_cat() {
        CatDTO dto = new CatDTO();
        dto.setName("Kuleihan");
        dto.setPrice(2000.0);

        CatDTO saved = catService.create(dto);
        Long id = saved.getId();

        catService.delete(id);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> catService.getById(id)
        );
    }


}
