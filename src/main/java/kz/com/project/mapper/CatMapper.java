package kz.com.project.mapper;

import kz.com.project.Dto.CatDTO;
import kz.com.project.model.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {
    CatDTO toDto(Cat cat);
    Cat toEntity(CatDTO catDTO);
    List<CatDTO> toDtoList(List<Cat> catList);
    List<Cat> toEntityList(List<CatDTO> catDTOList);
}
