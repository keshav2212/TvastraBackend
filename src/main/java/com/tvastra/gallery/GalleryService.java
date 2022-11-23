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
    ArtistService artistService;


    public List<Artwork> getArtworks(String name){
        Long galleryId = galleryRepository.findByName(name).getId();
        return artistService.getArtworks(galleryId);
    }

}
