package de.TntTastisch.BlackNerux.system;

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
            connection = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aEine Verbindung zur MySQL-Datenbank wurde erfolgreich verbunden.");
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§aEine Verbindung zur MySQL-Datenbank war fehlerhaft.");
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection != null){
                connection.close();
                Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cDie Verbindung zur MySQL-Datenbak wurde erfolgreich getrennt.");
            }
        } catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(Data.prefixSQL + "§cDie Verbindung zur MySQL-Datenbank war fehlerhaft in der trennung!");
            e.printStackTrace();
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


}
