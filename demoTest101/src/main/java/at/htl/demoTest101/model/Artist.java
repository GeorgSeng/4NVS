package at.htl.demoTest101.model;

public class Artist {
    private long id;
    private String Name;

    //region Constructors

    public Artist(long id, String name) {
        this.id = id;
        Name = name;
    }

    public Artist(String name) {
        Name = name;
    }

    public Artist(){}

    //endregion

    //region Properties

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }

    //endregion

}
