package kz.com.project.api;

import kz.com.project.Dto.CatDTO;
import kz.com.project.model.Cat;
import kz.com.project.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin/cats")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    /**
     * Returns a list of all cats (products).
     * Accessible only by ADMIN.
     */
    @GetMapping
    public ResponseEntity<List<CatDTO>> getAll() {
        return ResponseEntity.ok(catService.getAll());
    }

    /**
     * Creates a new cat (product).
     * Supports image upload using multipart/form-data.
     * Saves image to the server and stores image URL in database.
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CatDTO> create(
            @RequestParam("name") String name,
            @RequestParam("breed") String breed,
            @RequestParam("age") Integer age,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws IOException {

        CatDTO dto = new CatDTO();
        dto.setName(name);
        dto.setBreed(breed);
        dto.setAge(age);
        dto.setPrice(price);

        if (imageFile != null && !imageFile.isEmpty()) {
            String folder = "uploads/";
            Path uploadPath = Paths.get(folder);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = System.currentTimeMillis() + "_" +
                    imageFile.getOriginalFilename()
                            .replaceAll("[^a-zA-Z0-9\\.]", "_");

            Path path = uploadPath.resolve(filename);
            Files.write(path, imageFile.getBytes());

            dto.setImageUrl("/" + folder + filename);
        }

        return ResponseEntity.ok(catService.create(dto));
    }

    /**
     * Updates an existing cat by ID.
     * If a new image is uploaded, deletes the old image from the server.
     */
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CatDTO> update(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("breed") String breed,
            @RequestParam("age") Integer age,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) throws IOException {

        CatDTO dto = new CatDTO();
        dto.setName(name);
        dto.setBreed(breed);
        dto.setAge(age);
        dto.setPrice(price);

        if (imageFile != null && !imageFile.isEmpty()) {
            CatDTO existingCat = catService.getById(id);

            if (existingCat.getImageUrl() != null) {
                Path oldFilePath = Paths.get(
                        existingCat.getImageUrl().replaceFirst("/", "")
                );
                if (Files.exists(oldFilePath)) {
                    Files.delete(oldFilePath);
                }
            }

            String folder = "uploads/";
            Path uploadPath = Paths.get(folder);
            Files.createDirectories(uploadPath);

            String filename = System.currentTimeMillis() + "_" +
                    imageFile.getOriginalFilename()
                            .replaceAll("[^a-zA-Z0-9\\.]", "_");

            Path path = uploadPath.resolve(filename);
            Files.write(path, imageFile.getBytes());

            dto.setImageUrl("/" + folder + filename);
        }

        return ResponseEntity.ok(catService.update(id, dto));
    }

    /**
     * Deletes a cat (product) by ID.
     * Accessible only by ADMIN.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        catService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
