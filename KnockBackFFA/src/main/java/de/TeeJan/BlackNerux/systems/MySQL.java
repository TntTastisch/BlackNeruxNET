package de.TeeJan.BlackNerux.systems;

import de.TeeJan.BlackNerux.KnockBackFFA;
import org.bukkit.Bukkit;

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
    // KnockbackFFA(UUID varchar(120), Ranking varchar(64), Kills varchar(64), Deaths varchar(64), Coins varchar(64)
    public static boolean playerExists(final String uuid) {
        try {
            final ResultSet rs = KnockBackFFA.mySQL.query("SELECT * FROM KnockbackFFA WHERE UUID= '" + uuid + "'");
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

    public static void createPlayer(final String uuid) {
        if(!playerExists(uuid)) {
            KnockBackFFA.mySQL.update("INSERT INTO KnockbackFFA(UUID, Ranking, Kills, Deaths, Coins)" +
                    " VALUES ('" + uuid + "', '0', '0', '0', '0');");
        }
    }

    public static Integer getKills(final String uuid) {
        Integer i = -1;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = KnockBackFFA.mySQL.query("SELECT * FROM KnockbackFFA WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("Kills");
                }
                i = rs.getInt("Kills");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setKills(String UUID, Integer kills){
        if (playerExists(UUID)) {
            KnockBackFFA.mySQL.update("UPDATE KnockbackFFA SET Kills= '" + kills + "' WHERE UUID= '" + UUID + "';");
        }
    }

    public static void addKills(String UUID, Integer kills){
        if (playerExists(UUID)) {
            setKills(UUID, getKills(UUID) + kills);
        }
    }

    public static Integer getDeath(final String uuid) {
        Integer i = -1;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = KnockBackFFA.mySQL.query("SELECT * FROM KnockbackFFA WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("Deaths");
                }
                i = rs.getInt("Deaths");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setDeath(String UUID, Integer death){
        if (playerExists(UUID)) {
            KnockBackFFA.mySQL.update("UPDATE KnockbackFFA SET Deaths= '" + death + "' WHERE UUID= '" + UUID + "';");
        }
    }

    public static void addDeath(String UUID, Integer death){
        if (playerExists(UUID)) {
            setDeath(UUID, getDeath(UUID) + death);
        }
    }

    public static Integer getCoins(final String uuid) {
        Integer i = -1;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = KnockBackFFA.mySQL.query("SELECT * FROM KnockbackFFA WHERE UUID= '" + uuid + "'");
                if (rs.next()) {
                    rs.getInt("Coins");
                }
                i = rs.getInt("Coins");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setCoins(String UUID, Integer coins){
        if (playerExists(UUID)) {
            KnockBackFFA.mySQL.update("UPDATE KnockbackFFA SET Coins= '" + coins + "' WHERE UUID= '" + UUID + "';");
        }
    }

    public static void addCoins(String UUID, Integer coins){
        if (playerExists(UUID)) {
            setCoins(UUID, getCoins(UUID) + coins);
        }
    }

    public static void removeCoins(String UUID, Integer coins){
        if (playerExists(UUID)) {
            setCoins(UUID, getCoins(UUID) - coins);
        }
    }
}
