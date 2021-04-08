package codejava.src;
import java.sql.*;

class MysqlCon{
    public static void main(String args[]){
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
        }catch(Exception e) { System.out.println("Not connected, " + e.getMessage() +", "+ e.getStackTrace() +", "+ e.getCause());
            e.printStackTrace();
        }
    }
}
