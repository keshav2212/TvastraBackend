package com.tvastra.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

//    public Long getByName(String name){
//        return galleryRepository.findByName(name);
//    }

}
