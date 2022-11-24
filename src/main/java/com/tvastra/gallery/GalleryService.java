package com.tvastra.gallery;

import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    ArtworkService artworkService;

    public List<Artwork> getArtworks(String galleryName){
        Long id = galleryRepository.findByName(galleryName).getId();
        return artworkService.getGalleryArtworks(id);
    }
    public void saveGallery(String gallery) {
        galleryRepository.save(new Gallery(gallery));
    }

    public Gallery getById(Long id){
        return galleryRepository.findById(id).orElse(null);
    }
}
