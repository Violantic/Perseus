package me.violantic.perseus.database.repo.datatype.enumerators;

import me.violantic.perseus.database.repo.datatype.PerseusAchievement;
import me.violantic.perseus.database.repo.util.PerseusAchievementUtil;

/**
 * Created by Ethan on 6/24/2016.
 */
public enum Achievement {

    FIRST_LOGIN("First Login", "Log into the network for the first time!");

    String name;
    String description;

    Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAchievement(String name) {
        for(Achievement a : values()) {
            if(a.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public PerseusAchievement getObject() {
        return PerseusAchievementUtil.newInstance(getName(), getDescription());
    }

}
