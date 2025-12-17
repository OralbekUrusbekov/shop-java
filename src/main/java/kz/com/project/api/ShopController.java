package kz.com.project.api;

import kz.com.project.Dto.CatDTO;
import kz.com.project.Dto.OrderDTO;
import kz.com.project.service.CatService;
import kz.com.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shop")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class ShopController {

    private final CatService catService;

    // Витрина
    @GetMapping("/cats")
    public ResponseEntity<List<CatDTO>> getCats() {
        return ResponseEntity.ok(catService.getAll());
    }

    // Detail page
    @GetMapping("/cats/{id}")
    public ResponseEntity<CatDTO> getCat(@PathVariable Long id) {
        return ResponseEntity.ok(catService.getById(id));
    }
}


