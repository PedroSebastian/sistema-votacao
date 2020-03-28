package br.edu.unipampa.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
