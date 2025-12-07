package kz.com.project.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponce {
    private Long id;
    private String username;
    private String email;
    private String password;

}

