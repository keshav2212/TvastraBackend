package com.tvastra.gallery;

import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gallery")
    private List<Artwork> artworks;

    @JoinColumn(name = "owner_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return Objects.equals(id, gallery.id) && Objects.equals(name, gallery.name) && Objects.equals(artworks, gallery.artworks) && Objects.equals(owner, gallery.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artworks, owner);
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artworks=" + artworks +
                ", owner=" + owner +
                '}';
    }
}
