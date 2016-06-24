package me.violantic.perseus.database.repo.datarepo;

import me.violantic.perseus.database.repo.datatype.PerseusAchievement;

/**
 * Created by Ethan on 6/24/2016.
 */
public class AchievementRepository extends Repository<PerseusAchievement> {

    public String GET_ACHIEVEMENT  = "";
    public String GIVE_ACHIEVEMENT = "";

    public AchievementRepository() {
        super("achievements");
    }

    public void give() {

    }

    public void remove() {

    }

    public boolean has() {
        return false;
    }

    @Override
    public String getTable() {
        return null;
    }

    @Override
    public String createTableQuery() {
        return null;
    }

}
