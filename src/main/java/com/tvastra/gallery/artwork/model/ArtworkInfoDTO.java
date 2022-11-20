package com.tvastra.gallery.artwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkInfoDTO {
    private String title;

    private String description;
}
