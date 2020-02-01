package net.bote.nicklobby.mysql;

import net.bote.nicklobby.main.*;
import java.sql.*;

public class MySQL
{
    private String HOST;
    private String DATABASE;
    private String USER;
    private String PASSWORD;
    private Connection con;
    
    public MySQL(final String host, final String database, final String user, final String password) {
        this.HOST = "";
        this.DATABASE = "";
        this.USER = "";
        this.PASSWORD = "";
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.connect();
    }
    
    public void connect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        }
        catch (SQLException e) {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }
    
    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        }
        catch (SQLException e) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }
    
    public boolean isConnected() {
        return this.con != null;
    }
    
    public void update(final String qry) {
        try {
            final Statement st = this.con.createStatement();
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
            final Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
        return rs;
    }
    
    public static boolean contains(final String uuid) {
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM NickSystem WHERE UUID= '" + uuid + "'");
            return rs.next() && rs.getString("UUID") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void addPlayer(final String uuid) {
        if (!contains(uuid)) {
            Main.mysql.update("INSERT INTO NickSystem (UUID, STATE) VALUES ('" + uuid + "', 'true');");
        }
    }
    
    public static void removePlayer(final String uuid) {
        if (contains(uuid)) {
            try {
                final PreparedStatement ps = Main.mysql.con.prepareStatement("DELETE FROM NickSystem WHERE UUID = ?");
                ps.setString(1, uuid);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
