package com.tvastra.gallery.artwork;

import com.tvastra.Price.Price;
import com.tvastra.gallery.GalleryService;
import com.tvastra.gallery.artwork.model.ArtworkDTO;
import com.tvastra.gallery.category.CategoryService;
import com.tvastra.user.UserPrincipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    @Lazy
    GalleryService galleryService;

    @Autowired
    UserPrincipleService principleService;

    @Autowired
    CategoryService categoryService;

    public List<Artwork> getGalleryArtworks(Long id) {
        return artworkRepository.findByGalleryId(id);
    }

    public void saveArtwork(ArtworkDTO artworkDTO, String path) {
        Artwork artwork = new Artwork(new ArtworkInfo(artworkDTO.getInfo().getTitle(), artworkDTO.getInfo().getDescription()), principleService.findByUsername(artworkDTO.getUser()), galleryService.getById(artworkDTO.getGalleryId()), new Price(artworkDTO.getPrice().getAmount(), artworkDTO.getPrice().getCurrency()), path, categoryService.getById(artworkDTO.getCategoryId()));
        artworkRepository.save(artwork);
    }
}
