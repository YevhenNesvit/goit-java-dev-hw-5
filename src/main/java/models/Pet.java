package models;

import java.util.Arrays;

public class Pet {
    Long id;
    Category category;
    String name;
    String[] photoUrls;
    Tag[] tags;
    String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet {" +
                "id = " + id +
                ", category = " + category +
                ", name = '" + name + '\'' +
                ", photoUrls = " + Arrays.toString(photoUrls) +
                ", tags = " + Arrays.toString(tags) +
                ", status = '" + status + '\'' +
                '}';
    }
}
