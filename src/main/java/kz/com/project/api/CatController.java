package kz.com.project.api;

import kz.com.project.Dto.CatDTO;
import kz.com.project.service.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/cats")
@PreAuthorize("hasAuthority('ADMIN')")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<CatDTO>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @PostMapping
    public ResponseEntity<CatDTO> createCat(@RequestBody CatDTO catDTO) {
        return ResponseEntity.ok(catService.createCat(catDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatDTO> updateCat(@PathVariable Long id, @RequestBody CatDTO catDTO) {
        return ResponseEntity.ok(catService.updateCat(id, catDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }
}
