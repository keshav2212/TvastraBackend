package com.tvastra.gallery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkService;
import com.tvastra.gallery.artwork.model.ArtworkDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private Environment environment;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ArtworkService artworkService;


    @GetMapping("/{galleryName}")
    public List<ArtworkDTO> getArtwork(@PathVariable String galleryName) {

        List<Artwork> artworks = galleryService.getArtworks(galleryName);

        List<ArtworkDTO> artworkDTOS = artworks.stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());

        return artworkDTOS;
    }

    @PostMapping("/create-gallery")
    public void createGallery(@RequestBody GalleryDTO galleryDTO) {
        galleryService.saveGallery(new Gallery(galleryDTO.getName()));
    }

    @PostMapping("/upload-your-artwork")
    public void postArtwork(@RequestParam("image") MultipartFile multipartFile, @RequestParam("model") String data) throws IOException {

        StringBuilder sb = new StringBuilder();
        Path filePath = Paths.get(environment.getProperty("file.uploads.path", "src/main/uploads"), multipartFile.getOriginalFilename());
        Files.write(filePath, multipartFile.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        ArtworkDTO artworkDTO = mapper.readValue(data, ArtworkDTO.class);

        artworkService.saveArtwork(artworkDTO, String.valueOf(filePath));


    }

}
