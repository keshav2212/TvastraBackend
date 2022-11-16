package com.tvastra.gallery;

import com.tvastra.gallery.artwork.Artwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    public List<Artwork> getArtworks(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.map(Artist::getArtworks).orElse(null);
    }
}
