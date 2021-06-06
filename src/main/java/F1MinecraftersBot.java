
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class F1MinecraftersBot {

    public static String DATABASE_NAME = "sqlite";
    public static String CONFIG_NAME = "files/config.json";
    public static JSONObject config;
    public static String token;

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
    }

    public static boolean databaseExists() {
        return new File(DATABASE_NAME).exists();
    }

    public static boolean connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./" + DATABASE_NAME);
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
