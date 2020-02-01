package de.TntTastisch.BlackNerux;

import org.bukkit.*;
import java.sql.*;

public class MySQL
{
    public static String host = FileManager.configuration.getString("MySQL.Host");
    public static int port = FileManager.configuration.getInt("MySQL.Port");
    public static String database = FileManager.configuration.getString("MySQL.Database");
    public static String username = FileManager.configuration.getString("MySQL.User");
    public static String password = FileManager.configuration.getString("MySQL.Password");
    public static Connection con;

    
    public static boolean isConnected() {
        return MySQL.con != null;
    }
    
    public static void connect() {
        if (!isConnected()) {
            try {
                MySQL.con = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/" + MySQL.database, MySQL.username, MySQL.password);
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Main.pr) + "§aEs konnte erfolgreich mit der Datenbank verbunden werden");
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Main.pr) + "§cEs konnte nicht mit der Datenbank verbunden werden");
            }
        }
    }
    
    public static void disconnect() {
        try {
            MySQL.con.close();
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Main.pr) + "§aDie Verbindung zur Datenbank konnte erfolgreich geschlossen werden");
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Main.pr) + "§cDie Verbindung zur Datenbank konnte nicht geschlossen werden");
        }
    }
    
    public static PreparedStatement getStatement(final String sql) {
        if (isConnected()) {
            try {
                final PreparedStatement ps = MySQL.con.prepareStatement(sql);
                return ps;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static ResultSet getResult(final String sql) {
        if (isConnected()) {
            try {
                final PreparedStatement ps = getStatement(sql);
                final ResultSet rs = ps.executeQuery();
                return rs;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
