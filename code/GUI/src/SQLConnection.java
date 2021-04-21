import java.sql.*;

public class SQLConnection {
    private GUI gui;
    private Connection connection;

    public SQLConnection(GUI gui) throws Exception{
        this.gui = gui;
        startConnection();
    }

    public void startConnection() throws Exception{
        String url = "jdbc:mysql://127.0.0.1:3306/bank";
        String user = "root";
        String password = "0000";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        }catch(Exception e) { 
            System.out.println("Not connected");
            throw e;
        }
    }

    public String getCode(String uid) {
        try {
            Statement stmt = connection.createStatement();
            String statement = "select * from debitcard where debitcard_id = '" + uid + "';";
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            if(rs.getBoolean(4) == true) {
                return "geblokkeerd!";
            } else {
                return rs.getString(2);
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public String[] getDetails(String uid) {
        String[] result = new String[1];
        try {
            Statement stmt = connection.createStatement();
            String statement = "select * from accounts";
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            result[0] = String.valueOf(rs.getInt(5));
        } catch (SQLException e) {

        }
        return result;
    }

    public void blokkeerKaart(String uid, boolean block) {
        try {
            Statement stmt = connection.createStatement();
            String statement = "";
            if(block) {
                statement = "update debitcard set Blocked = true where debitcard_id = '" + uid + "';";
            } else {
                statement = "update debitcard set Blocked = false where debitcard_id = '" + uid + "';";
            }
            ResultSet rs = stmt.executeQuery(statement);
        } catch (SQLException e) {
            
        }
    }

    public boolean isBlocked(String uid) {
        try {
            Statement stmt = connection.createStatement();
            String statement = "select * from debitcard where debitcard_id = '" + uid + "';";
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            return rs.getBoolean(4);
        } catch (SQLException e) {

        }
        return true;
    }
}
