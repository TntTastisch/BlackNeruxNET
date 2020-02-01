package de.TntTastisch.BlackNerux.systems;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class MySQL {

    public String HOST;
    public String PORT;
    public String DATABASE;
    public String USER;
    public String PASSWORD;
    public static Connection connection;

    public MySQL(String host, String port, String database, String user, String password){
        HOST = "";
        PORT = "";
        DATABASE = "";
        USER = "";
        PASSWORD = "";

        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;


        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE, this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage(Data.mysql + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich aufgebaut.");
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.mysql + "§cEine Verbindung zur MySQL-Datenbank wurde fehlerhaft aufgebaut.");
        }
    }

    public static void disconnect(){
        if(connection != null){
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage(Data.mysql + "§aDie Verbindung zur MySQL-Datenbank wurde erfolgreich getrennt.");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Data.mysql + "§cDie Verbindung zur MySQL-Datenbank wurde fehlerhaft getrennt.");
            }
        }
    }

    public void update(final String qry) {
        try {
            final Statement st = this.connection.createStatement();
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
            final Statement st = this.connection.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
        return rs;
    }

    public static boolean playerExists(final String UUID) {
        try {
            final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
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


    public static void createPlayer(final String UUID, final Player player, final double X, final double Y, final double Z, final float Yaw, final float Pitch, final String World) {
        if (!playerExists(UUID)) {
            LobbySystem.mySQL.update("INSERT INTO lobby (UUID, X, Y, Z, Yaw, Pitch, World, playerhider, particle, gadgets, skull, fly, shield) VALUES " +
                    "('" + UUID + "', '" + X + "', '" + Y + "', '" + Z + "', '" + Yaw + "', '" + Pitch + "', '" + World + "', '0', '0', '0', '0', '0', '0');");
        }
    }

    public static void setX(final String UUID, final double X){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET X=" + X + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setY(final String UUID, final double Y){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET Y=" + Y + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setZ(final String UUID, final double Z){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET Z=" + Z + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setYaw(final String UUID, final double Yaw){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET Yaw=" + Yaw + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setPitch(final String UUID, final double Pitch){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET Pitch=" + Pitch + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setWorld(final String UUID, final String World){
        if(playerExists(UUID)){
            LobbySystem.mySQL.update("UPDATE lobby SET World=" + World + " WHERE UUID= '" + UUID + "'");
        }
    }

    public static Double getX(final String UUID){
        double i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getDouble("X");
                }
                i = rs.getDouble("X");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Double getY(final String UUID){
        double i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getDouble("Y");
                }
                i = rs.getDouble("Y");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Double getZ(final String UUID){
        double i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getDouble("Z");
                }
                i = rs.getDouble("Z");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static float getYaw(final String UUID){
        float i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getFloat("Yaw");
                }
                i = rs.getFloat("Yaw");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static float getPitch(final String UUID){
        float i = 0;
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getFloat("Pitch");
                }
                i = rs.getFloat("Pitch");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static String getWorld(final String UUID){
        String i = " ";
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + UUID + "'");
                if (rs.next()) {
                    rs.getString("World");
                }
                i = rs.getString("World");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setPlayerhiderData(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET playerhider= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }


    public static Integer getPlayerHider(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("playerhider");
                }
                i = rs.getInt("playerhider");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setGadgets(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET gadgets= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getGadgets(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("gadgets");
                }
                i = rs.getInt("gadgets");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }


    public static void setParticle(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET particle= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getPaticle(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("particle");
                }
                i = rs.getInt("particle");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setFlyTool(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET fly= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }


    public static Integer getFlyTool(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("fly");
                }
                i = rs.getInt("fly");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setKoepfe(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET skull= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }


    public static Integer getKoepfe(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("skull");
                }
                i = rs.getInt("skull");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    public static void setSchutzschild(String UUID, int State){
        if (playerExists(UUID)) {
            LobbySystem.mySQL.update("UPDATE lobby SET shield= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }


    public static Integer getSchutzschild(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = LobbySystem.mySQL.query("SELECT * FROM lobby WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("shield");
                }
                i = rs.getInt("shield");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

}
