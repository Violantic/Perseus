package me.violantic.perseus;

import me.violantic.perseus.database.repo.Rank;
import me.violantic.perseus.database.repo.Spell;
import me.violantic.perseus.database.repo.datatype.PerseusAccount;
import me.violantic.perseus.database.vDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ethan on 5/8/2016.
 */

public class Perseus {

    public static Perseus instance;
    private static String EXIT_COMMAND = "exit";
    private static vDatabase _database;

    private String _testUUID = "abcd-efgh-ijkl-mnop";

    public Perseus(){
        instance = this;
        _database = new vDatabase();
    }

    public static Perseus getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Perseus newInstance = new Perseus();
        startConsole();
    }

    public vDatabase getDatabase() {
        return _database;
    }

    private static void startConsole() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----------------------");
            System.out.println("|      Welcome to    | ");
            System.out.println("|      ======[--     | ");
            System.out.println("|      Perseus       |");
            System.out.println("----------------------");


            while (true) {

                String input = br.readLine();
                System.out.println(input);

                if (input.length() == EXIT_COMMAND.length() && input.toLowerCase().equals(EXIT_COMMAND)) {
                    System.out.println("Exiting.");
                    return;
                }

                String[] args = input.split(" ");
                String command = args[0];
                int size = args.length - 1;
                if (command.equals("spells")) {
                    if (size == 2) {
                        if (args[1].equalsIgnoreCase("get")) {
                            String uuid = args[2];
                            System.out.println("-------------------------------------");
                            System.out.println(uuid + "'s spells: \n" + _database.getSpells().getSpells(uuid).toString());
                            System.out.println("-------------------------------------");

                        }
                    } else if (size == 3) {
                        String pet = args[2];
                        String uuid = args[3];
                        if (args[1].equalsIgnoreCase("add")) {
                            if (!Spell.isSpell(pet)) {
                                startConsole();
                            }
                            if (_database.getSpells().getSpells(uuid).contains(pet.toLowerCase())) {
                                System.out.println("[Perseus] The UUID '" + uuid + "' already owns the spell '" + pet + "'");
                                startConsole();
                            }
                            _database.getSpells().addSpell(uuid, pet);
                            System.out.println("-------------------------------------");
                            System.out.println("[Perseus] The spell '" + pet + "' has been added to '" + uuid + "'");
                            System.out.println("[Perseus] Updated spell list for '" + uuid + "': \n" + _database.getSpells().getSpells(uuid).toString());
                            System.out.println("-------------------------------------");

                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (!_database.getSpells().getSpells(uuid).contains(pet.toLowerCase())) {
                                System.out.println("[Perseus] The UUID '" + uuid + "' does not own the specified spell '" + pet + "'");
                                startConsole();
                            }

                            _database.getSpells().removeSpell(uuid, pet);

                            System.out.println("-------------------------------------");
                            System.out.println("[Perseus] The spell '" + pet + "' has been removed from '" + uuid + "'");
                            System.out.println("[Perseus] Updated spell list for '" + uuid + "': \n" + _database.getSpells().getSpells(uuid).toString());
                            System.out.println("-------------------------------------");


                        }
                    } else {
                        System.out.println("[Perseus] Usage: spells <get, add, remove> [uuid] (pet)");
                    }

                } else if (command.equals("eco")) {
                    if (size == 1) {
                        String uuid = args[1];
                        System.out.println("[Perseus] money FOR '" + uuid + "': " + _database.getEco().getMoney(uuid));
                    } else if (size == 3) {
                        if (args[1].equalsIgnoreCase("set")) {
                            int money = Integer.parseInt(args[2]);
                            String uuid = args[3];
                            _database.getEco().setMoney(uuid, money);
                            System.out.println("-------------------------------------");
                            System.out.println("[Perseus] money SET TO: " + money + " FOR '" + uuid + "'");
                            System.out.println("-------------------------------------");
                        }
                    }

                } else if (command.equals("id")) {
                    if (size == 1) {
                        String uuid = args[1];
                        System.out.println("-------------------------------------");
                        System.out.println("[Perseus] id FOR '" + uuid + "': '" + _database.getID(uuid) + "'");
                        System.out.println("-------------------------------------");
                    }

                } else if (command.equals("check")) {
                    if (size == 1) {
                        String uuid = args[1];
                        System.out.println("-------------------------------------");
                        System.out.println("[Perseus] overview of '" + uuid + "'\n" + new PerseusAccount(uuid).simpleFormat());
                        System.out.println("-------------------------------------");
                    }

                } else if (command.equals("jsoncheck")) {
                    if (size == 1) {
                        String uuid = args[1];
                        System.out.println("-------------------------------------");
                        System.out.println("[Perseus] overview of '" + uuid + "'\n" + new PerseusAccount(uuid).toString());
                        System.out.println("-------------------------------------");
                    }

                } else if (command.equals("rank")) {
                    if (size == 1) {
                        String uuid = args[1];
                        System.out.println("-------------------------------------");
                        System.out.println("[Perseus] rank FOR '" + uuid + "': '" + _database.getRank(uuid) + "'");
                        System.out.println("-------------------------------------");
                    } else if (size == 3) {
                        if (args[1].equalsIgnoreCase("set")) {
                            String rank = args[2];
                            String uuid = args[3];

                            if (!Rank.isRank(rank)) {
                                System.out.println("[Perseus] That is not a valid rank.");
                                startConsole();
                            }

                            _database.setRank(uuid, rank);
                            System.out.println("-------------------------------------");
                            System.out.println("[Perseus] Rank has been updated to '" + rank + "' for '" + uuid + "'");
                            System.out.println("-------------------------------------");
                        }
                    }

                } else if (command.equals("register")) {
                    if (size == 2) {
                        String name = args[1];
                        String uuid = args[2];
                        _database.addAccount(uuid, name);
                        System.out.println("-------------------------------------");
                        System.out.println("[Perseus] '" + uuid + "' was added to the database");
                        System.out.println("-------------------------------------");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
