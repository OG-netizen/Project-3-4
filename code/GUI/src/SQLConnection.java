import java.sql.*;

public class SQLConnection {
    private Connection connectie;

    public SQLConnection() throws Exception{
        startConnection();
    }

    public void startConnection() throws Exception{
        // String url = "jdbc:mysql://127.0.0.1:3306/bank";
        // String gebruiker = "root";
        // String wachtwoord = "0000";
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11416191";
        String gebruiker = "sql11416191";
        String wachtwoord = "uGIUwBp9CN";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectie = DriverManager.getConnection(url, gebruiker, wachtwoord);
            System.out.println("Verbonden met SQL server");
        }catch(Exception e) { 
            System.out.println("Fout tijdens het verbinden met SQL server");
            throw e;
        }
    }

    public String getCode(String uid) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "select * from Debitcard where Debitcard_id = '" + uid + "';";
            ResultSet resultaat = stmt.executeQuery(statement);
            resultaat.next();
            if(resultaat.getBoolean(5) == true) {
                return "geblokkeerd!";
            } else {
                return resultaat.getString(3);
            }
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
        return null;
    }

    public String[] getDetails(String uid) {
        String[] result = {"0"};
        try {
            Statement stmt = connectie.createStatement();
            String statement = "select * from Accounts where Debitcard_id = '" + uid + "'";
            ResultSet resultaat = stmt.executeQuery(statement);
            resultaat.next();
            result[0] = String.valueOf(resultaat.getInt(5));
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
        return result;
    }

    public void blokkeerKaart(String uid, boolean block) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "";
            if(block) {
                statement = "update Debitcard set Blocked = true where Debitcard_id = '" + uid + "';";
            } else {
                statement = "update Debitcard set Blocked = false where Debitcard_id = '" + uid + "';";
            }
            stmt.executeUpdate(statement);
            setAantalPogingen(uid, 0);
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
    }

    public boolean isBlocked(String uid) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "select * from Debitcard where Debitcard_id = '" + uid + "';";
            ResultSet resultaat = stmt.executeQuery(statement);
            resultaat.next();
            return resultaat.getBoolean(5);
        } catch (SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
        return true;
    }

    public int aantalPogingen(String uid) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "select * from Debitcard where Debitcard_id = '" + uid + "';";
            ResultSet resultaat = stmt.executeQuery(statement);
            resultaat.next();
            return resultaat.getInt(3);
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
        return 3;
    }

    public void setAantalPogingen(String uid, int aantal) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "update Debitcard set Attemps = " + aantal + " where Debitcard_id = '" + uid + "';";
            stmt.executeUpdate(statement);
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een query op de SQL server: " + e);
        }
    }

    public void setSaldo(String uid, int aantal) {
        try {
            Statement stmt = connectie.createStatement();
            String statement = "update Accounts set Balance = " + aantal + " where Debitcard_id = '" + uid + "';";
            stmt.executeUpdate(statement);
        } catch(SQLException e) {
            System.out.println("Fout tijdens het uitvoeren van een wuery op de SQL server: " + e);
        }
    }
}
