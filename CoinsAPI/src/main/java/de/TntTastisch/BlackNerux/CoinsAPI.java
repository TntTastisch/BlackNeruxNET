package de.TntTastisch.BlackNerux;

import org.bukkit.entity.*;
import java.sql.*;

public class CoinsAPI
{
    public static void createTable() {
        try {
            final PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS Coins (Spielername VARCHAR(100), UUID VARCHAR(100), Coins INT(100))");
            ps.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void register(final Player p) {
        try {
            final PreparedStatement ps = MySQL.getStatement("INSERT INTO Coins (Spielername, UUID, Coins) VALUES (?, ?, ?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getUniqueId().toString());
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean isRegistered(final Player p) {
        try {
            final PreparedStatement ps = MySQL.getStatement("SELECT * FROM Coins WHERE UUID= ?");
            ps.setString(1, p.getUniqueId().toString());
            final ResultSet rs = ps.executeQuery();
            final boolean user = rs.next();
            rs.close();
            rs.close();
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean isRegistered(final String name) {
        try {
            final PreparedStatement ps = MySQL.getStatement("SELECT * FROM Coins WHERE Spielername= ?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            final boolean user = rs.next();
            rs.close();
            rs.close();
            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static int getCoins(final Player p) {
        try {
            final PreparedStatement ps = MySQL.getStatement("SELECT * FROM Coins WHERE UUID= ?");
            ps.setString(1, p.getUniqueId().toString());
            final ResultSet rs = ps.executeQuery();
            rs.next();
            final int coins = rs.getInt("Coins");
            rs.close();
            ps.close();
            return coins;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static void setCoins(final Player p, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE UUID= ?");
            ps.setInt(1, coins);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addCoins(final Player p, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE UUID= ?");
            ps.setInt(1, getCoins(p) + coins);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void removeCoins(final Player p, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE UUID= ?");
            ps.setInt(1, getCoins(p) - coins);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static int getCoins(final String name) {
        try {
            final PreparedStatement ps = MySQL.getStatement("SELECT * FROM Coins WHERE Spielername= ?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            final int coins = rs.getInt("Coins");
            rs.close();
            ps.close();
            return coins;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static void setCoins(final String name, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE Spielername= ?");
            ps.setInt(1, coins);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addCoins(final String name, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE Spielername= ?");
            ps.setInt(1, getCoins(name) + coins);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void removeCoins(final String name, final int coins) {
        try {
            final PreparedStatement ps = MySQL.getStatement("UPDATE Coins SET Coins= ? WHERE Spielername= ?");
            ps.setInt(1, getCoins(name) - coins);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
