package com.linkeriyo.f1minecraftersbot;

import org.json.JSONObject;
import org.json.JSONTokener;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class F1MinecraftersBot {

    public static String DATABASE_NAME = "C:\\sqlite";
    public static String CONFIG_NAME = "files/config.json";
    public static JSONObject config;
    public static String token, prefix;
    public static String url = "jdbc:sqlite:" + DATABASE_NAME;
    public static DiscordConnection discordConnection;

    public static void main(String[] args) {
        if (databaseExists()) {
            connectToDatabase();
        } else {
            System.out.println("database does not exist at \"sqlite\"");
            return;
        }
        config = readJSON(CONFIG_NAME);
        if (config == null) {
            System.out.println("no such file: files/config.json");
            return;
        }
        token = config.getString("token");
        prefix = config.getString("prefix");

        try {
            discordConnection = new DiscordConnection(token);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static boolean databaseExists() {
        return new File(DATABASE_NAME).exists();
    }

    public static boolean connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println(connection.getSchema());
            System.out.println("Connection to SQLite has been established.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static JSONObject readJSON(String path) {
        try {
            FileReader fr = new FileReader(path);
            JSONTokener tokener = new JSONTokener(fr);
            return new JSONObject(tokener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
