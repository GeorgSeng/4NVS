package at.htl.demoTest101.model;

public class Album {
    private long id;
    private String name;

    //region Constructors

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

    //endregion
}
