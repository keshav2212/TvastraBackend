package com.tvastra.gallery.category;


import com.tvastra.gallery.artwork.Artwork;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "category")
    List<Artwork> artwork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Artwork> getArtwork() {
        return artwork;
    }

    public void setArtwork(List<Artwork> artwork) {
        this.artwork = artwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(imagePath, category.imagePath) && Objects.equals(artwork, category.artwork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imagePath, artwork);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", artwork=" + artwork +
                '}';
    }
}
