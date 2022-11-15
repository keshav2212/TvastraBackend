package com.tvastra.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
//    @Query("SELECT G.ID FROM GALLERY G WHERE G.NAME=:name")
//    Long findByName(@Param("name") String name);
}
