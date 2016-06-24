package me.violantic.perseus.database.repo.util;

import me.violantic.perseus.database.repo.datatype.PerseusSpell;

/**
 * Created by Ethan on 6/23/2016.
 */
public class PerseusSpellUtil {

    public static PerseusSpell newInstance(String name) {
        return new PerseusSpell(name);
    }

}
