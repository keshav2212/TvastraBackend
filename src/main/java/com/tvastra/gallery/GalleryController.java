package com.tvastra.gallery;

import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkDTO;
import com.tvastra.gallery.artwork.ArtworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GalleryService galleryService;

    @GetMapping("/{galleryName}")
    public List<ArtworkDTO> getArtwork(@PathVariable String galleryName) {

        List<Artwork> artworks = galleryService.getArtworks(galleryName);

        List<ArtworkDTO> artworkDTOS = artworks.stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());

        return artworkDTOS;
    }
}
