package me.violantic.perseus.database.repo.datatype;

/**
 * Created by Ethan on 6/22/2016.
 */
public class PerseusSpell extends PerseusDataType {

    public String name;

    public PerseusSpell(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
