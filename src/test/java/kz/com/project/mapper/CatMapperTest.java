package kz.com.project.mapper;

import kz.com.project.Dto.CatDTO;
import kz.com.project.model.Cat;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatMapperTest {

    private final CatMapper catMapper = Mappers.getMapper(CatMapper.class);

    @Test
    void testToDto() {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("Murka");
        cat.setPrice(50.0);

        CatDTO dto = catMapper.toDto(cat);
        assertEquals(cat.getId(), dto.getId());
        assertEquals(cat.getName(), dto.getName());
        assertEquals(cat.getPrice(), dto.getPrice());
    }

    @Test
    void testToEntity() {
        CatDTO dto = new CatDTO();
        dto.setId(2L);
        dto.setName("Murka");
        dto.setPrice(60.0);

        Cat entity = catMapper.toEntity(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getPrice(), entity.getPrice());
    }

    @Test
    void testToDtoList() {
        Cat cat = new Cat();
        cat.setId(3L);
        List<CatDTO> dtoList = catMapper.toDtoList(Collections.singletonList(cat));
        assertEquals(1, dtoList.size());
        assertEquals(cat.getId(), dtoList.get(0).getId());
    }

    @Test
    void testToEntityList() {
        CatDTO dto = new CatDTO();
        dto.setId(4L);
        List<Cat> entityList = catMapper.toEntityList(Collections.singletonList(dto));
        assertEquals(1, entityList.size());
        assertEquals(dto.getId(), entityList.get(0).getId());
    }
}
