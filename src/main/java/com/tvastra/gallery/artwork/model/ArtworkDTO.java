package com.tvastra.gallery.artwork.model;

import com.tvastra.Price.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkDTO {

    private ArtworkInfoDTO info;
    private Price price;

    private Long categoryId;

    private Long galleryId;

    private String user;
}
