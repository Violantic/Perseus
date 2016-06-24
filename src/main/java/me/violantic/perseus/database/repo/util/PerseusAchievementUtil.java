package me.violantic.perseus.database.repo.util;

import me.violantic.perseus.database.repo.datatype.PerseusAchievement;

/**
 * Created by Ethan on 6/24/2016.
 */
public class PerseusAchievementUtil {

    public static PerseusAchievement newInstance(String name, String description) {
        return new PerseusAchievement(name, description);
    }

}
