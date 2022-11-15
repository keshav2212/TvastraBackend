package com.tvastra.gallery.artwork;

import com.tvastra.Price.Price;
import com.tvastra.gallery.Artist;
import com.tvastra.gallery.Gallery;
import com.tvastra.gallery.category.Category;
import com.tvastra.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private ArtworkInfo info;

    @JoinColumn(name = "artist_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;
    private Price price;

    @Column(name = "art_image_path")
    private String artImagePath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="gallery_id")
    private Gallery gallery;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtworkInfo getInfo() {
        return info;
    }

    public void setInfo(ArtworkInfo info) {
        this.info = info;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return Objects.equals(id, artwork.id) && Objects.equals(info, artwork.info) && Objects.equals(artist, artwork.artist) && Objects.equals(price, artwork.price) && Objects.equals(artImagePath, artwork.artImagePath) && Objects.equals(gallery, artwork.gallery) && Objects.equals(category, artwork.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, artist, price, artImagePath, gallery, category);
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "id=" + id +
                ", info=" + info +
                ", artist=" + artist +
                ", price=" + price +
                ", artImagePath='" + artImagePath + '\'' +
                ", gallery=" + gallery +
                ", category=" + category +
                '}';
    }
}
