package de.TntTastisch.BlackNerux.systems;

import de.TntTastisch.BlackNerux.ClanSystem;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.*;

public class MySQL {

    public String HOST;
    public String PORT;
    public String DATABASE;
    public String USER;
    public String PASSWORD;
    public Connection connection;

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
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefix + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich hergestellt!"));
            this.update("CREATE TABLE IF NOT EXISTS clans(clanName varchar(64), clanTag varchar(64), clanColor varchar(64), clanCreator varchar(64))");

        } catch (SQLException e){
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefix + "§cEine Verbindung zur MySQL-Datenbank war fehlerhaft!"));
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection != null) {
                connection.close();
                BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefix + "§aDie Verbindung zur MySQL-Datenbank wurde erfolgreich getrennt!"));
            }
        } catch (SQLException e){
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefix + "§cDie trennung der Verbindung zur MySQL-Datenbank war fehlerhaft!"));
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

    public static boolean playerExists(final String UUID) {
        try {
            final ResultSet rs = ClanSystem.mySQL.query("SELECT * FROM clanPlayers WHERE UUID= '" + UUID + "'");
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

    public static boolean clanExists(final String clanName){
        try {
            final ResultSet rs = ClanSystem.mySQL.query("SELECT * FROM clans WHERE clanName= '" + clanName + "'");
            return rs.next() && rs.getString("clanName") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException error) {
            return false;
        }
        return false;
    }

    public static String isPlayerInClan(final String UUID){
        String i = "";
        if (playerExists(UUID)) {
            try {
                final ResultSet rs = ClanSystem.mySQL.query("SELECT * FROM clanPlayers WHERE UUID='" + UUID + "'");
                if (rs.next()) {
                    rs.getString("Clan");
                }
                i = rs.getString("Clan");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    // UUID varchar(64), Clan varchar(120), ClanRank varchar(64)
    public static void createPlayer(final String UUID) {
        if (!playerExists(UUID)) {
            ClanSystem.mySQL.update("INSERT INTO clanPlayers (UUID, Clan, ClanRank, invites) VALUES " +
                    "('" + UUID + "', 'null', 'null', '1');");
        }
    }

    public static void setClanPlayer(String UUID, String clanname){
        if(playerExists(UUID)){
            ClanSystem.mySQL.update("UPDATE clanPlayers SET Clan=" + clanname + " WHERE UUID='" + UUID + "'");
        }
    }

    public static void setRank(String UUID, String rank){
        if(playerExists(UUID)){
            ClanSystem.mySQL.update("UPDATE clanPlayers SET ClanRank=" + rank + " WHERE UUID='" + UUID + "'");
        }
    }


    // clans(clanName varchar(64), clanTag varchar(64), clanColor varchar(64), clanCreator varchar(64)
    public static void createClan(final String UUID, String clanname, String clantag) {
        if (playerExists(UUID)) {
            ClanSystem.mySQL.update("INSERT INTO clans (clanName, clanTag, clanColor, clanCreator) VALUES " +
                    "('" + clanname + "', '" + clantag + "', '" + "§7" + "', '" + UUID + "');");
        }
    }


}
