package com.tvastra.gallery.artwork;

import com.tvastra.Price.Price;
import com.tvastra.gallery.Gallery;
import com.tvastra.gallery.category.Category;
import com.tvastra.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Builder
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private ArtworkInfo info;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User addedBy;

    @JoinColumn(name= "gallery_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Gallery gallery;

    private Price price;

    @Column(name = "art_image_path")
    private String artImagePath;
    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public Artwork() {
    }

    public Artwork( ArtworkInfo info, User addedBy, Gallery gallery, Price price, String artImagePath, Category category) {
        this.info = info;
        this.addedBy = addedBy;
        this.gallery = gallery;
        this.price = price;
        this.artImagePath = artImagePath;
        this.category = category;
    }

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

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artwork artwork = (Artwork) o;
        return id != null && Objects.equals(id, artwork.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
