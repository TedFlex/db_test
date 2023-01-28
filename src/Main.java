import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Main extends JFrame implements ActionListener {
static JComboBox Side;
static JComboBox gender;
static JButton jb;
static JButton filter;
static Connection conn;
static Statement st;
static ResultSet rs;

    public static void main(String[] args) {
	// write your code here
        try {
             conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;","Ted2","m16isopaf");
             st=conn.createStatement();
             rs=st.executeQuery("SELECT OPS.*,GUNS.name AS GunName FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id");
            while (rs.next())
            {
                System.out.print(rs.getString(1)+" "+rs.getString("GunName"));
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        gender=new JComboBox();

        Main main = new Main();
        main.setLayout(null);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setSize(800, 500);
        Side = new JComboBox();
        main.add(Side);
        Side.setBounds(100, 50, 100, 30);
        Side.addItem("ATTACKER");
        Side.addItem("DEFENDER");
        Side.addItem("ALL");
        Side.setSelectedItem("ALL");

        main.add(gender);
        gender.setBounds(250,50,100,30);
        gender.addItem("FEMALE");
        gender.addItem("ATTACKER");
        gender.addItem("BOTH");
        gender.setSelectedItem("ALL");
        JLabel sidelabel=new JLabel("Side");
        JLabel genderlabel=new JLabel("Gender");
        main.add(sidelabel);
        main.add(genderlabel);
        sidelabel.setBounds(100,20,70,30);
        genderlabel.setBounds(250,20,70,30);
        filter=new JButton("Filter");
        filter.addActionListener(main);
        main.add(filter);
        filter.setBounds(100,100,70,30);

        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filter) {
            if (gender.getSelectedItem().equals("FEMALE") && Side.getSelectedItem().equals("ATTACKER")) {
                try {
                rs=st.executeQuery("SELECT OPS.*, GUNS.name,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id WHERE OPS.side='ATTACKER' AND OPS.gender='FEMALE'");
                while (rs.next()){
                    System.out.print(rs.getString("name"));
                    System.out.println();
                }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }
            else if (gender.getSelectedItem().equals("FEMALE")  && Side.getSelectedItem().equals("DEFENDER")){
                try {
                    rs=st.executeQuery("SELECT OPS.*, GUNS.name,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id WHERE OPS.side='DEFENDER' AND OPS.gender='FEMALE'");
                    while (rs.next()){
                        System.out.print(rs.getString("name"));
                        System.out.println();
                    }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }
        }
    }
}
