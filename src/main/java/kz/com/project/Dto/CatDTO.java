package kz.com.project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatDTO {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private Double price;
}
