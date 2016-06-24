package me.violantic.perseus.database.repo.datarepo;

import me.violantic.perseus.Perseus;
import me.violantic.perseus.database.repo.datatype.PerseusMoney;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ethan on 6/22/2016.
 */
public class EcoRepository extends Repository<PerseusMoney> {

    public String GET_MONEY = "SELECT money FROM economy WHERE accountID=(SELECT id FROM accounts WHERE uuid='{uuid}')";
    public String SET_MONEY = "UPDATE economy SET money='{$}' WHERE accountID=(SELECT id FROM accounts WHERE uuid='{uuid}');";

    public EcoRepository() {
        super("economy");
    }

    public double getMoney(String uuid) {
        try {
            PreparedStatement statement = Perseus.getInstance().getDatabase()._connection.prepareStatement(GET_MONEY.replace("{uuid}", uuid));
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                double money = set.getInt("money");
                return money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0D;
    }

    public void setMoney(String uuid, int money) {
        try {
            PreparedStatement statement = Perseus.getInstance().getDatabase()._connection.prepareStatement(SET_MONEY.replace("{uuid}", uuid).replace("{$}", money + ""));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTable() {
        return "economy";
    }

    @Override
    public String createTableQuery() {
        return "CREATE TABLE IF NOT EXISTS economy (id INT NOT NULL AUTO_INCREMENT, accountID INT, money DOUBLE, PRIMARY KEY(id))";
    }
}
