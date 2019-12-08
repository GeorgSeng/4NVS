package at.htl.demoTest101.model;

public class Genre {
    private long id;
    private String name;

    //region Constructors

    public Genre(String name) {
        this.name = name;
    }

    public Genre(){}

    //endregion

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
