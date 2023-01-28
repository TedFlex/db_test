import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class Main extends JFrame implements ActionListener {
static JComboBox Side;
static JComboBox gender;
static JComboBox GunType;
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
        GunType=new JComboBox();
        GunType.addItem("AR");
        GunType.addItem("DMR");
        GunType.addItem("SLUG");
        GunType.addItem("SMG");
        GunType.addItem("LMG");
        GunType.addItem("ALL");
        GunType.setSelectedItem("ALL");

        main.add(gender);
        main.add(GunType);
        gender.setBounds(250,50,100,30);
        GunType.setBounds(400,50,100,30);
        gender.addItem("FEMALE");
        gender.addItem("MALE");
        gender.addItem("ALL");
        gender.setSelectedItem("ALL");
        JLabel sidelabel=new JLabel("Side");
        JLabel genderlabel=new JLabel("Gender");
        JLabel guntypelabel=new JLabel("Guntype");
        main.add(sidelabel);
        main.add(genderlabel);
        sidelabel.setBounds(100,20,70,30);
        genderlabel.setBounds(250,20,70,30);
        guntypelabel.setBounds(400,20,70,30);
        filter=new JButton("Filter");
        filter.addActionListener(main);
        main.add(filter);
        main.add(guntypelabel);
        filter.setBounds(100,100,70,30);

        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filter) {
            if (gender.getSelectedItem().equals("FEMALE") && Side.getSelectedItem().equals("ATTACKER")) {
                try {
                rs=st.executeQuery("SELECT OPS.*, GUNS.name AS GunName,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id WHERE OPS.side='ATTACKER' AND OPS.gender='FEMALE'");
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
                        System.out.print(rs.getString("name")+" "+rs.getString("tier"));
                        System.out.println();
                    }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }else if (gender.getSelectedItem().equals("ALL")  && Side.getSelectedItem().equals("ALL")){
                try {
                    rs=st.executeQuery("SELECT OPS.*, GUNS.name,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id");
                    while (rs.next()){
                        System.out.print(rs.getString("name"));
                        System.out.println();
                    }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }else if (gender.getSelectedItem().equals("MALE")  && Side.getSelectedItem().equals("DEFENDER")){
                try {
                    rs=st.executeQuery("SELECT OPS.*, GUNS.name,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id WHERE OPS.side='DEFENDER' AND OPS.gender='MALE'");
                    while (rs.next()){
                        System.out.print(rs.getString("name")+" "+rs.getString("tier"));
                        System.out.println();
                    }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }else if (gender.getSelectedItem().equals("MALE")  && Side.getSelectedItem().equals("ATTACKER")){
                try {
                    rs=st.executeQuery("SELECT OPS.*, GUNS.name,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id WHERE OPS.side='ATTACKER' AND OPS.gender='MALE'");
                    while (rs.next()){
                        System.out.print(rs.getString("name")+" "+rs.getString("tier"));
                        System.out.println();
                    }
                } catch (Exception a) {
                    System.out.println(a);
                }
            }
        }
    }
}
