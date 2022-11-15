package com.tvastra.gallery.artwork;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ArtworkInfo {
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworkInfo that = (ArtworkInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return "ArtworkInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
