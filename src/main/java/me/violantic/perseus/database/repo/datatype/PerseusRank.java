package me.violantic.perseus.database.repo.datatype;

/**
 * Created by Ethan on 6/22/2016.
 */
public class PerseusRank extends PerseusDataType {

    public String name;
    public String prefix;

    public PerseusRank(String name, String prefix) {
        super(name);
        this.name = name;
        this.prefix = prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPrefix() {

        return prefix;
    }

    public String getName() {

        return name;
    }

    public PerseusRank newInstance(String name, String prefix) {
        return new PerseusRank(name, prefix);
    }

    public PerseusRank[] ranks = {new PerseusRank("Owner", "&c[Owner]"), new PerseusRank("SrDev", "&c[SRDEV]"), new PerseusRank("Admin", "&c[ADMIN]"), new PerseusRank("Dev", "&c[DEV]")};
}
