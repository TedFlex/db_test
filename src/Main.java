import javax.swing.*;
import java.sql.*;
public class Main extends JFrame {
static JComboBox jc;
static JButton jb;

    public static void main(String[] args) {
	// write your code here
        try {
            Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;","Ted2","m16isopaf");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT OPS.*,GUNS.name AS GunName FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id");
            while (rs.next())
            {
                System.out.print(rs.getString(4)+" "+rs.getString("GunName"));
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e);
        }
//        Main main = new Main();
//        main.setLayout(null);
//        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        main.setSize(500, 500);
//        jc = new JComboBox();
//        main.add(jc);
//        jc.setBounds(100, 50, 70, 30);
//        jc.addItem("jager");
//        jc.addItem("ash");
//
//        main.setVisible(true);
    }
}
