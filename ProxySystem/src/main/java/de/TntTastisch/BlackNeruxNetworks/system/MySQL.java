package de.TntTastisch.BlackNeruxNetworks.system;

import de.TntTastisch.BlackNeruxNetworks.ProxySystem;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
            createTables();
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefixMySQL + "§aEine Verbindung zur MySQL-Datenbank wurde erforeich hergestellt."));
        } catch (SQLException e){
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefixMySQL + "§cEine Verbindunh zur MySQL-Datenbank war fehlerhaft!"));
            e.getSQLState();
        }
    }

    public void createTables(){
        this.update("CREATE TABLE IF NOT EXISTS system_reports(playerUUID varchar(120), reporterUUID varchar(120), reason varchar(100), status varchar(20), created varchar(100));");
        this.update("CREATE TABLE IF NOT EXISTS system_filters(playerUUID varchar(120), ip varchar(64), serverId varchar(64), blockedMessage varchar(100), TimeUnit varchar(64));");
        this.update("CREATE TABLE IF NOT EXISTS system_onlinetime(name varchar(20), uuid varchar(120), time varchar(120));");
    }

    public void disconnect(){
        try {
            if(connection != null){
                connection.close();
                BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefixMySQL + "§aDie Verbindung zur MySQl-Datenbank wurde erfolgreich getrennt!"));
            }
        } catch (SQLException e){
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(Data.prefixMySQL + "§cDas Trennen der Verbindung war fehlerhaft!"));
            e.getSQLState();
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

    public static boolean playerExists(final String uuid) {
        try {
            final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_players WHERE uuid= '" + uuid + "'");
            return rs.next() && rs.getString("uuid") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException error) {
            return false;
        }
        return false;
    }

    public static void createPlayer(final ProxiedPlayer player) {
        if(playerExists(player.getUniqueId().toString()) == false) {
            ProxySystem.mySQl.update("INSERT INTO system_players(name, uuid, ip, report, teamchat) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', '" + player.getAddress().getAddress().getHostAddress() + "', '0', '0');");
        }
    }

    public static void createBlockedLogs(final String UUID, String ip, String server, String message, String time){

        ProxySystem.mySQl.update("INSERT INTO system_filters(playerUUID, ip, serverId, blockedMessage, TimeUnit) VALUES ('" + UUID + "', '" + ip + "', '" + server +  "', '" + message + "', '" + time + "');");

    }

    public static Integer getTeamChat(final String uuid) {
        Integer i = -1;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_players WHERE uuid= '" + uuid + "'");
                if (rs.next()) {
                    rs.getBoolean("teamchat");
                }
                i = rs.getInt("teamchat");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setTeamChat(String UUID, String status){
        if (playerExists(UUID)) {
            ProxySystem.mySQl.update("UPDATE system_players SET teamchat= '" + status + "' WHERE uuid= '" + UUID + "';");
        }
    }

    public static void setReport(String UUID, String status){
        if (playerExists(UUID)) {
            ProxySystem.mySQl.update("UPDATE system_players SET report= '" + status + "' WHERE uuid= '" + UUID + "';");
        }

    }

    public static String getIpFechter(ProxiedPlayer proxiedPlayer){
            ProxySystem.mySQl.update("SELECT * FROM system_players GROUP BY ip = '" + proxiedPlayer.getAddress().getAddress().getHostAddress() + "'");
        return null;
    }

    public static Integer getReport(final String uuid) {
        Integer i = -1;
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_players WHERE uuid= '" + uuid + "'");
                if (rs.next()) {
                    rs.getBoolean("report");
                }
                i = rs.getInt("report");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setIp(String UUID, String status){
        if (playerExists(UUID)) {
            ProxySystem.mySQl.update("UPDATE system_players SET ip= '" + status + "' WHERE uuid= '" + UUID + "';");
        }

    }

    public static String getIp(final String uuid) {
        String i = "";
        if (playerExists(uuid)) {
            try {
                final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_players WHERE uuid= '" + uuid + "'");
                if (rs.next()) {
                    rs.getString("ip");
                }
                i = rs.getString("ip");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    // system_reports(playerUUID varchar(120), reporterUUID varchar(120), reason varchar(100), status varchar(20), created varchar(100));


    public static void createReport(ProxiedPlayer player, ProxiedPlayer target, String reason, String status, String createdTime){

        ProxySystem.mySQl.update("INSERT INTO system_reports(playerUUID, reporterUUID, reason, status, created) VALUES ('" + player.getUniqueId() +"', '" + target.getUniqueId() + "', '" + reason + "', " +
                "'" + status + "', '" + createdTime + "')");

    }


    public static String getReportStatus(final String uuid) {
        String i = " ";
        try {
            final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_reports WHERE playerUUID= '" + uuid + "'");
            if (rs.next()) {
                rs.getString("status");
            }
            i = rs.getString("status");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void setReportStatus(String UUID, String status){
        ProxySystem.mySQl.update("UPDATE system_reports SET status= '" + status + "' WHERE playerUUID= '" + UUID + "';");
    }


// system_onlinetime(name varchar(20), uuid varchar(120), time varchar(120));
    public static void updateTime(final ProxiedPlayer player) {
        if (isRegistered(player.getUniqueId())) {
            ProxySystem.mySQl.update("UPDATE system_onlinetime SET time = '" + (getTime(player.getUniqueId()) + 1) + "' WHERE uuid = '" + player.getUniqueId().toString() + "'");
        } else {
            ProxySystem.mySQl.update("INSERT INTO system_onlinetime (name, uuid, time) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', '1')");
        }
    }

    public static int getTime(final UUID uuid) {
        if (connection != null) {
            try {
                final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_onlinetime WHERE uuid = '" + uuid.toString() + "'");
                if (rs.next()) {
                    return rs.getInt("time");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private static boolean isRegistered(final UUID uuid) {
        if (connection != null) {
            try {
                final ResultSet rs = ProxySystem.mySQl.query("SELECT * FROM system_onlinetime WHERE uuid = '" + uuid.toString() + "'");
                if (rs.next()) {
                    return rs.getInt("time") != 0;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
