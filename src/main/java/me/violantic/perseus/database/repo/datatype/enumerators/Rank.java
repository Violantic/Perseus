package me.violantic.perseus.database.repo.datatype.enumerators;

/**
 * Created by Ethan on 6/23/2016.
 */
public enum Rank {

    OWNER,
    SRDEV,
    ADMIN,
    DEV,
    MOD,
    PROF,
    BUILD;

    public static boolean isRank(String rank) {
        for(Rank ranks : values()) {
            if(ranks.toString().toLowerCase().equalsIgnoreCase(rank.toString().toString())) {
                return true;
            }
        }

        return false;
    }

}
