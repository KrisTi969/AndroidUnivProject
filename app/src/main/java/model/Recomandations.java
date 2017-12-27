package model;

/**
 * Created by crys_ on 27.12.2017.
 */
public class Recomandations {
    private Integer id;
    private String name;
    private String first_aired;

    public Recomandations(Integer id, String name, String first_aired) {
        this.id = id;
        this.name = name;
        this.first_aired = first_aired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_aired() {
        return first_aired;
    }

    public void setFirst_aired(String first_aired) {
        this.first_aired = first_aired;
    }

    @Override
    public String toString() {
        return "" + name + '\'' +
                " " + first_aired + '\''
                ;
    }
}
