package me.violantic.perseus.database.repo.datarepo;

import me.violantic.perseus.Perseus;
import me.violantic.perseus.database.repo.Repository;
import me.violantic.perseus.database.repo.datatype.PerseusSpell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 6/22/2016.
 */
public class SpellRepository extends Repository<PerseusSpell> {

    public String CREATE_TAB = "CREATE TABLE IF NOT EXISTS spells (id INT NOT NULL AUTO_INCREMENT, accountID INT, spellName VARCHAR(100), PRIMARY KEY (id));";
    public String GET_SPELLS = "SELECT spellName FROM spells WHERE accountID=(SELECT id FROM accounts WHERE uuid='{uuid}');";
    public String ADD_SPELL  = "INSERT INTO spells VALUES (NULL, (SELECT id FROM accounts WHERE UUID='{uuid}'), '{spell}')";;
    public String REM_SPELL  = "DELETE FROM spells WHERE accountID='{id}' AND spellName='{spell}'";

    public SpellRepository() {
        super("spells");
    }

    public void addSpell(String uuid, String spell) {
        try {
            PreparedStatement statement = Perseus.getInstance().getDatabase()._connection.prepareStatement(ADD_SPELL.replace("{uuid}", uuid).replace("{spell}", spell));
            statement.executeUpdate();
            statement.close();
            System.out.println("[Perseus] QUERY: ADDED '" + spell + "' FOR '" + uuid + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSpell(String uuid, String spell) {
        try {
            PreparedStatement statement = Perseus.getInstance().getDatabase()._connection.prepareStatement(REM_SPELL.replace("{id}", Perseus.getInstance().getDatabase().getID(uuid) + "").replace("{spell}", spell));

            statement.close();
            System.out.println("[Perseus] QUERY: REMOVED '" + spell + "' FROM '" + uuid + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSpells(final String uuid) {
        return new ArrayList<String>() {
            {
                try {
                    PreparedStatement preparedStatement = Perseus.getInstance().getDatabase()._connection.prepareStatement(GET_SPELLS.replace("{uuid}", "" + uuid + ""));
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()) {
                        String pet = resultSet.getString("spellName");
                        this.add(pet.toLowerCase());
                    }
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public String getTable() {
        return "spells";
    }

    @Override
    public String createTableQuery() {
        return CREATE_TAB;
    }
}
