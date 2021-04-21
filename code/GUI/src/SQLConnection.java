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
            //here test is database name, root is username and password
            /*Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from accounts");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();*/
        }catch(Exception e) { 
            System.out.println("Not connected");
            throw e;
        }
    }

    public void uidRecieved(String uid) {
        try {
            Statement stmt = connection.createStatement();
            String statement = "select * from debitcard where debitcard_id = '" + uid + "';";
            //String statement = "select * from debitcard";
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                //System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getByte(4));
                System.out.println(rs.getString(2));
            }
        } catch(SQLException e) {

        }
    }

    public String getCode(String uid) {
        try {
            Statement stmt = connection.createStatement();
            String statement = "select * from debitcard where debitcard_id = '" + uid + "';";
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            return rs.getString(2);
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
}
