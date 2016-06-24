package me.violantic.perseus.database.repo.datarepo;

import me.violantic.perseus.database.repo.datatype.PerseusDataType;

/**
 * Created by Ethan on 6/22/2016.
 */
public abstract class Repository<t extends PerseusDataType> {

    public String name;

    public Repository(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public abstract String getTable();

    public abstract String createTableQuery();
}
