package de.TntTastisch.BlackNerux.systems;

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
}
