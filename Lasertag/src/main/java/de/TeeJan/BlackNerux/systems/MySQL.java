package de.TeeJan.BlackNerux.systems;

import de.TeeJan.BlackNerux.LaserTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class MySQL {

    public String HOST;
    public String PORT;
    public String DATABASE;
    public String USER;
    public String PASSWORD;
    public static Connection connection;

    public MySQL(String host, String port, String database, String user, String password){
        this.HOST = "";
        this.PORT = "";
        this.DATABASE = "";
        this.USER = "";
        this.PASSWORD = "";

        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }


    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE, this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage(Data.prefix + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich hergestellt!");
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefix + "§cEine Verbindung zur MySQL-Datenbank war fehlerhaft!");
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection != null) {
                connection.close();
                Bukkit.getConsoleSender().sendMessage(Data.prefix + "§aDie Verbindung zur MySQL-Datenbank wurde erfolgreich getrennt!");
            }
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefix + "§cDie trennung der Verbindung zur MySQL-Datenbank war fehlerhaft!");
            e.printStackTrace();
        }
    }


    public void update(final String qry) {
        try {
            final Statement st = connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
    }

    public ResultSet query(final String qry) {
        ResultSet rs = null;
        try {
            final Statement st = connection.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
        return rs;
    }

    // LaserTag(UUID varchar(120), Ranking varchar(64), Wins varchar(64), PlayedGames varchar(64), Kills varchar(64), Deaths varchar(64)
    public static boolean playerExists(final String UUID) {
        try {
            final ResultSet rs = LaserTag.mySQL.query("SELECT * FROM LaserTag WHERE UUID= '" + UUID + "'");
            return rs.next() && rs.getString("UUID") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException error) {
            return false;
        }
        return false;
    }


    public static void createPlayer(final String UUID) {
        if (!playerExists(UUID)) {
            LaserTag.mySQL.update("INSERT INTO LaserTag (UUID, Ranking, Wins, PlayedGames, Kills, Deaths) VALUES " +
                    "('" + UUID + "', '0', '0', '0', '0', '0');");
        }
    }

}
