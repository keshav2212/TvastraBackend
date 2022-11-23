package com.tvastra.gallery.artwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

//    @Query("FROM ARTWORK WHERE ")
//    public List<Artwork> findByGalleryId(@Param("gallery_id") Long galleryId);
}
