package com.tvastra.gallery.artwork;

import com.tvastra.Price.Price;
import com.tvastra.gallery.category.Category;

public class ArtworkDTO {

    private ArtworkInfo info;

    private Long artistId;

    private Long GalleryId;

    private Price price;

    private String artImagePath;

    private Category category;

    public ArtworkDTO(ArtworkInfo info, Long artistId, Long galleryId, Price price, String artImagePath, Category category) {
        this.info = info;
        this.artistId = artistId;
        GalleryId = galleryId;
        this.price = price;
        this.artImagePath = artImagePath;
        this.category = category;
    }

    public ArtworkInfo getInfo() {
        return info;
    }

    public void setInfo(ArtworkInfo info) {
        this.info = info;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getGalleryId() {
        return GalleryId;
    }

    public void setGalleryId(Long galleryId) {
        GalleryId = galleryId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getArtImagePath() {
        return artImagePath;
    }

    public void setArtImagePath(String artImagePath) {
        this.artImagePath = artImagePath;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
