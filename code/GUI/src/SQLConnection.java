import java.sql.*;

public class SQLConnection {
    private GUI gui;
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
            Connection connection = DriverManager.getConnection(url, user, password);
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
        System.out.println(uid);
    }
}
