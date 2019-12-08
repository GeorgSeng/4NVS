package at.htl.demoTest101.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Album {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    private Artist artist;

    //region Constructors


    public Album(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album(String name) {
        this.name = name;
    }

    public Album(){}
    //

    //region Properties

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                '}';
    }


    //endregion
}
