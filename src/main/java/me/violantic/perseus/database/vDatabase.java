package me.violantic.perseus.database;

import me.violantic.perseus.database.repo.datarepo.Repository;
import me.violantic.perseus.database.repo.datarepo.EcoRepository;
import me.violantic.perseus.database.repo.datarepo.SpellRepository;

import java.sql.*;

/**
 * Created by Ethan on 5/8/2016.
 */
public class vDatabase {

    private String _connectionString = "jdbc:mysql://localhost:3306/mcc";
    private String _userName = "root";
    private String _password = "1024311";

    private static String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS accounts (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100), uuid VARCHAR(256), rank VARCHAR(256), PRIMARY KEY (id));";
    private static String RETRIEVE_ACCOUNT_ID = "SELECT id FROM accounts WHERE uuid='{uuid}'";

    public Repository[] getRepos() {
        return new Repository[] {
                new SpellRepository(),
                new EcoRepository()
        };
    }

    public SpellRepository getSpells() {
        return (SpellRepository) getRepos()[0];
    }

    public EcoRepository getEco() {
        return (EcoRepository) getRepos()[1];
    }

    public void createTables() {
        for(Repository repo : getRepos()) {
            try {
                PreparedStatement statement = _connection.prepareStatement(repo.createTableQuery());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection _connection;

    public vDatabase() {

        PreparedStatement preparedStatement = null;

        try {

            Class clazz = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) clazz.newInstance();
            DriverManager.registerDriver(driver);

            if (_connection == null || _connection.isClosed())
                _connection = DriverManager.getConnection(_connectionString, _userName, _password);

            preparedStatement = _connection.prepareStatement(CREATE_ACCOUNT_TABLE);
            preparedStatement.execute();
            preparedStatement.close();

            createTables();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }

    public void addAccount(String uuid, String ign) {
        String query = "INSERT INTO accounts VALUES (NULL, '" + ign + "', '" + uuid + "', 'regular')";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getID(String uuid) {

        try {
            PreparedStatement statement = _connection.prepareStatement(RETRIEVE_ACCOUNT_ID.replace("{uuid}", uuid));
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                return set.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getName(String uuid) {
        String query = "SELECT name FROM accounts WHERE uuid='" + uuid + "'";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                String rank = set.getString("name");
                return rank;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRank(String uuid) {
        String query = "SELECT rank FROM accounts WHERE uuid='" + uuid + "'";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                String rank = set.getString("rank");
                return rank;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setRank(String uuid, String rank) {
        String query = "UPDATE accounts SET rank='" + rank + "' WHERE uuid='" + uuid + "'";
        try {
            PreparedStatement statement = _connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
