package at.htl.demoTest101.model;

public class Artist {
    private long id;
    private String Name;

    //region Constructors

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


}
