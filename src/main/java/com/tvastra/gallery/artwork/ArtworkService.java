package com.tvastra.gallery.artwork;

import com.tvastra.gallery.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    GalleryService galleryService;





}
