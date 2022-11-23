package com.tvastra.gallery;

import com.tvastra.Price.Price;
import com.tvastra.TvastraApplication;
import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkInfo;
import com.tvastra.gallery.artwork.ArtworkService;
import com.tvastra.gallery.category.Category;
import com.tvastra.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GalleryServiceTest {

    @InjectMocks
    GalleryService galleryService;

    @Mock
    GalleryRepository galleryRepository;

    @Mock
    ArtworkService artworkService;

    @Test
    public void shouldSaveGalleryWhenGalleryNameIsGiven() {
        String inputGalleryName = "Test Gallery";
        Gallery gallery = Gallery.builder().name(inputGalleryName).build();
        when(galleryRepository.save(any(Gallery.class))).thenReturn(gallery);

        galleryService.saveGallery(gallery);

        verify(galleryRepository, times(1)).save(any(Gallery.class));
        verifyNoMoreInteractions(galleryRepository);
    }

    @Test
    public void shouldReturnGalleryWhenGalleryIdIsGiven() {
        Gallery gallery = Gallery.builder().name("testGallery").build();
        when(galleryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(gallery));

        Gallery actual = galleryService.getById(getRandomLong());

        assertEquals(actual, gallery);
        verify(galleryRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(galleryRepository);


    }

    @Test
    public void shouldGetGalleryArtworksWhenGalleryNameIsGiven() {
        String testGallery = "Test Gallery";
        Artwork firstArtwork = Artwork.builder().info(new ArtworkInfo())
                .addedBy(new User())
                .gallery(new Gallery())
                .price(new Price())
                .artImagePath("/testUploads")
                .category(new Category())
                .build();
        Artwork secondArtwork = Artwork.builder()
                .build();
        List<Artwork> artworks = new ArrayList<>();
        artworks.add(firstArtwork);
        artworks.add(secondArtwork);
        when(galleryRepository.findByName(testGallery)).thenReturn(Gallery.builder().id(1L).build());
        when(artworkService.getGalleryArtworks(anyLong())).thenReturn(artworks);

        List<Artwork> artworkList = galleryService.getArtworks(testGallery);

        assertEquals(artworks, artworkList);
        verify(galleryRepository, times(1)).findByName(anyString());
        verify(artworkService, times(1)).getGalleryArtworks(anyLong());
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(artworkService);
    }

    private Long getRandomLong() {
        return new Random().longs(1, 10).findFirst().getAsLong();
    }

}
