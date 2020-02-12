package de.TntTastisch.BlackNerux.systems;

import de.TntTastisch.BlackNerux.EscapeTheDemons;
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

    // EscapeTheDemons(UUID varchar(64), Played varchar(120), Lost varchar(120), Points varchar(120))
    public static boolean playerExists(final String UUID) {
        try {
            final ResultSet rs = EscapeTheDemons.mySQL.query("SELECT * FROM EscapeTheDemons WHERE UUID= '" + UUID + "'");
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
            EscapeTheDemons.mySQL.update("INSERT INTO EscapeTheDemons (UUID, Played, Lost, Points, DemonPass, PolicePass) VALUES " +
                    "('" + UUID + "', '0', '0', '0', '0', '0');");
        }
    }

    public static Integer getDemonPasses(final String UUID){
        int i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = EscapeTheDemons.mySQL.query("SELECT * FROM EscapeTheDemons WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getInt("DemonPass");
                }
                i = rs.getInt("DemonPass");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setDemonPasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            EscapeTheDemons.mySQL.update("UPDATE players SET DemonPass= '" + pass + "' WHERE UUID= '" + UUID + "';");
        }
    }

    public static void addDemonPasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            setDemonPasses(UUID, getDemonPasses(UUID) + pass);
        }
    }

    public static void removeDemonPasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            setDemonPasses(UUID, getDemonPasses(UUID) - pass);
        }
    }

    public static Integer getPolicePasses(final String UUID){
        int i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = EscapeTheDemons.mySQL.query("SELECT * FROM EscapeTheDemons WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getInt("PolicePass");
                }
                i = rs.getInt("PolicePass");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setPolicePasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            EscapeTheDemons.mySQL.update("UPDATE players SET PolicePass= '" + pass + "' WHERE UUID= '" + UUID + "';");
        }
    }

    public static void addPolicePasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            setDemonPasses(UUID, getDemonPasses(UUID) + pass);
        }
    }

    public static void removePolicePasses(String UUID, Integer pass){
        if (playerExists(UUID)) {
            setDemonPasses(UUID, getDemonPasses(UUID) - pass);
        }
    }

}
