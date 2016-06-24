package me.violantic.perseus.database.repo.datatype;

import com.google.gson.JsonObject;
import me.violantic.perseus.Perseus;

import java.util.List;

/**
 * Created by Ethan on 6/23/2016.
 */
public class PerseusAccount extends PerseusDataType {

    String uuid;

    public PerseusAccount(String uuid) {
        super("{PerseusAccount:{}}");
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public JsonObject getJson() {
        JsonObject object = new JsonObject();
        object.addProperty("name", Perseus.getInstance().getDatabase().getName(getUuid()));
        object.addProperty("uuid", getUuid());
        object.addProperty("rank", Perseus.getInstance().getDatabase().getRank(getUuid()));
        object.addProperty("money", Perseus.getInstance().getDatabase().getEco().getMoney(getUuid()));

        JsonObject spells = new JsonObject();
        List<String> spellList = Perseus.getInstance().getDatabase().getSpells().getSpells(getUuid());
        for(String spell : spellList) {
            spells.addProperty("[" + (spellList.indexOf(spell)+1) + "]", spell);
        }
        object.add("spells", spells);

        JsonObject outerObject = new JsonObject();
        outerObject.add("perseusaccount", object);
        return outerObject;
    }

    @Override
    public String toString() {
        return getJson().toString();
    }

    public String simpleFormat() {
        List<String> spellList = Perseus.getInstance().getDatabase().getSpells().getSpells(getUuid());
        return "name: " + Perseus.getInstance().getDatabase().getName(getUuid()) + "\nuuid: " + getUuid() + "\nrank: " + Perseus.getInstance().getDatabase().getRank(getUuid()) + "\nspells: " + spellList.toString() + " \n" +
                "money: " + Perseus.getInstance().getDatabase().getEco().getMoney(getUuid());
    }

}
