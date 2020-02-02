package de.TntTastisch.BlackNerux.systems;

import de.TntTastisch.BlackNerux.CityBuildSystem;
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
        this.HOST = " ";
        this.PORT = " ";
        this.DATABASE = " ";
        this.USER = " ";
        this.PASSWORD = " ";

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

            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich hergestellt!");
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cEine Verbindung zur MySQL-Datenbank konnte nicht aufgebaut werden...");
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection != null){
                connection.close();
                Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aDie Verbindung wurde erfolgreich getrennt!");
            }
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cDie Verbindung konnte nicht getrennt werden...");
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

    // players(UUID varchar(120), Name varchar(64), MsgEnable varchar(10), SocialSpyEnable varchar(10), TpaEnable varchar(10), VanishEnable varchar(10), FlyEnabled varchar(10)
    public static boolean playerExists(final String UUID) {
        try {
            final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + UUID + "'");
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


    public static void createPlayer(final Player player) {
        if (!playerExists(player.getUniqueId().toString())) {
            CityBuildSystem.mySQL.update("INSERT INTO players (UUID, Name, MsgEnable, SocialSpyEnable, TpaEnable, VanishEnable, FlyEnabled) VALUES " +
                    "('" + player.getUniqueId().toString() + "', '" + player.getName() + "', '1', '0', '1', '0', '0');");
        }
    }

    public static void setVanish(String UUID, int State){
        if (playerExists(UUID)) {
            CityBuildSystem.mySQL.update("UPDATE players SET VanishEnable= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getVanish(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("VanishEnable");
                }
                i = rs.getInt("VanishEnable");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setFly(String UUID, int State){
        if (playerExists(UUID)) {
            CityBuildSystem.mySQL.update("UPDATE players SET FlyEnabled= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getFly(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("FlyEnabled");
                }
                i = rs.getInt("FlyEnabled");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setMsgEnable(String UUID, int State){
        if (playerExists(UUID)) {
            CityBuildSystem.mySQL.update("UPDATE players SET MsgEnable= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getMsgEnable(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("MsgEnable");
                }
                i = rs.getInt("MsgEnable");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setSocialSpy(String UUID, int State){
        if (playerExists(UUID)) {
            CityBuildSystem.mySQL.update("UPDATE players SET SocialSpyEnable= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getSocialSpy(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("SocialSpyEnable");
                }
                i = rs.getInt("SocialSpyEnable");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setTpaToggle(String UUID, int State){
        if (playerExists(UUID)) {
            CityBuildSystem.mySQL.update("UPDATE players SET TpaEnable= '" + State + "' WHERE UUID= '" + UUID + "';");
        }

    }

    public static Integer getTpaToggle(final String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = CityBuildSystem.mySQL.query("SELECT * FROM players WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("TpaEnable");
                }
                i = rs.getInt("TpaEnable");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
