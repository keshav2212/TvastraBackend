package com.tvastra.artwork;

import com.tvastra.Price.Price;
import com.tvastra.TvastraApplication;
import com.tvastra.gallery.Gallery;
import com.tvastra.gallery.GalleryService;
import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkRepository;
import com.tvastra.gallery.artwork.ArtworkService;
import com.tvastra.gallery.artwork.model.ArtworkDTO;
import com.tvastra.gallery.artwork.model.ArtworkInfoDTO;
import com.tvastra.gallery.category.Category;
import com.tvastra.gallery.category.CategoryService;
import com.tvastra.user.User;
import com.tvastra.user.UserPrincipleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtworkServiceTest {

    @InjectMocks
    ArtworkService artworkService;
    @Mock
    ArtworkRepository artworkRepository;

    @Mock
    UserPrincipleService principleService;

    @Mock
    GalleryService galleryService;

    @Mock
    CategoryService categoryService;

    @Test
    public void shouldSaveArtworkToRepository() {
        ArtworkDTO artworkDTO = ArtworkDTO.builder()
                .info(new ArtworkInfoDTO())
                .user("testUser")
                .galleryId(1L)
                .price(new Price())
                .categoryId(1L)
                .build();
        Artwork artwork = Artwork.builder().build();
        when(principleService.findByUsername(anyString())).thenReturn(new User());
        when(galleryService.getById(anyLong())).thenReturn(new Gallery());
        when(categoryService.getById(anyLong())).thenReturn(new Category());
        when(artworkRepository.save(any(Artwork.class))).thenReturn(artwork);


        artworkService.saveArtwork(artworkDTO, "testPath");

        verify(principleService, times(1)).findByUsername(anyString());
        verify(galleryService, times(1)).getById(anyLong());
        verify(categoryService, times(1)).getById(anyLong());
        verify(artworkRepository, times(1)).save(any(Artwork.class));

//        relook
//        verifyNoInteractions(principleService);
//        verifyNoInteractions(galleryService);
//        verifyNoMoreInteractions(categoryService);
//        verifyNoInteractions(artworkRepository);
    }



}
