package table;

import javax.persistence.*;

/**
 * Created by pavlo on 14.07.15.
 */

@Entity
@Table(name="item")
public class Item {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="url")
    private  String  url;

    @Column(name="description")
    private  String description;

    @Column(name="picture")
    private  String picture;


    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

}
