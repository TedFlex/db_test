import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    static Connection conn;
    static JTextField opname;
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) {
        try {
            conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=SIEGE STUFF;trustServerCertificate=true;","Ted2","m16isopaf");
            st=conn.createStatement();
            rs=st.executeQuery("SELECT OPS.*,GUNS.name AS GunName FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id");


        }catch (Exception e){
            System.out.println(e);
        }
        new CreateOP(conn,st,rs);
    }
}
