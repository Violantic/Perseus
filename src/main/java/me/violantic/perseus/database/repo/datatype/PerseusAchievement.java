package me.violantic.perseus.database.repo.datatype;

/**
 * Created by Ethan on 6/24/2016.
 */
public class PerseusAchievement extends PerseusDataType {

    public String name;
    public String description;

    public PerseusAchievement(String name, String description) {
        super(name);
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
