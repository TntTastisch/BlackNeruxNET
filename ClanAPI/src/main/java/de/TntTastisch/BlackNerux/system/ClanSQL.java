package de.TntTastisch.BlackNerux.system;

import de.TntTastisch.BlackNerux.ClanAPI;
import org.bukkit.Bukkit;

import java.sql.*;

public class ClanSQL {

    public static String HOST;
    public static String PORT;
    public static String DATABASE;
    public static String USER;
    public static String PASSWORD;
    public static Connection connection;


    static {
        HOST = ClanAPI.getPlugin(ClanAPI.class).configuration.getString("MySQL.Host");
        PORT = ClanAPI.getPlugin(ClanAPI.class).configuration.getString("MySQL.Port");
        DATABASE = ClanAPI.getPlugin(ClanAPI.class).configuration.getString("MySQL.Database");
        USER = ClanAPI.getPlugin(ClanAPI.class).configuration.getString("MySQL.User");
        PASSWORD = ClanAPI.getPlugin(ClanAPI.class).configuration.getString("MySQL.Password");
    }

    public static void connect(){
        try {
             connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich aufgebaut!");
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cEine Verbindung zur MySQL-Datenbank war fehlerhaft...");
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            if(connection != null){
                connection.close();
                Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aDie Verbindung zur MySQL-Datenbank wurde erfolgreich getrennt!");
            }
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cDie trennung der Verbindung war fehlerhaft...");
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
            connect();
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
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public static String getClanName(final String UUID) {
        String i = "";
        try {
            final ResultSet rs = ClanAPI.mySQL.query("SELECT * FROM clans WHERE clanCreator='" + UUID + "'");
            if (rs.next()) {
                rs.getString("clanName");
            }
            i = rs.getString("clanName");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String getClanTag(final String UUID) {
        String i = "";
        try {
            final ResultSet rs = ClanAPI.mySQL.query("SELECT * FROM clans WHERE clanCreator='" + UUID + "'");
            if (rs.next()) {
                rs.getString("clanTag");
            }
            i = rs.getString("clanTag");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String getClanColor(final String UUID) {
        String i = "";
        try {
            final ResultSet rs = ClanAPI.mySQL.query("SELECT * FROM clans WHERE clanCreator='" + UUID + "'");
            if (rs.next()) {
                rs.getString("clanColor");
            }
            i = rs.getString("clanColor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
