import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame implements ActionListener {
static JComboBox Side;
static JComboBox gender;
static JComboBox GunType;
static JComboBox tiers;
static JButton filter;
static Connection conn;
static JTextField opname;
static Statement st;
static ResultSet rs;
static JTable jt;
static String [][]data;
static    String [] column;
static DefaultTableModel tableModel;
static JButton Createop;
static JButton Creategun;



    public static void main(String[] args) {

	// write your code here
        try {
             conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=new_siege;trustServerCertificate=true;","Ted2","m16isopaf");
             st=conn.createStatement();
             rs=st.executeQuery("SELECT OPS.*,GUNS.name AS GunName FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id");


        }catch (Exception e){
            System.out.println(e);
        }
        gender=new JComboBox();
        tiers =new JComboBox();
        try{
            rs=st.executeQuery("SELECT DISTINCT OPS.tier  FROM OPS");
            while (rs.next()){
                tiers.addItem(rs.getString("tier"));
            }
            tiers.addItem("ALL");
            tiers.setSelectedItem("ALL");
        } catch (Exception e) {
                System.out.println(e);
        }


        Main main = new Main();
        main.setLayout(null);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setSize(1920, 1080);
        opname=new JTextField();


//        main.add(opname);
        opname.setBounds(300,80,100,30);
        Side = new JComboBox();
        main.add(Side);
        main.add(tiers);
        tiers.setBounds(550,50,100,30);
        Side.setBounds(100, 50, 100, 30);
        Side.addItem("ATTACK");
        Side.addItem("DEFENSE");
        Side.addItem("ALL");
        Side.setSelectedItem("ALL");
        GunType=new JComboBox();
        GunType.addItem("Assault Rifle");
        GunType.addItem("marksman Rifle");
        GunType.addItem("Shotgun");
        GunType.addItem("Submachine Gun");
        GunType.addItem("Light Machine Gun");
        GunType.addItem("ALL");
        GunType.setSelectedItem("ALL");
        column= new String[]{"id", "gender", "name", "side"," OP Tier", "Gun Name", "Gun Type", "Ammo","Gun rating"};
        data=new String[][]{};
        tableModel=new DefaultTableModel(column,0);
        jt=new JTable(tableModel);
        Createop=new JButton("Create Operator");
        Createop.setBounds(300,100,150,30);
        Createop.addActionListener(main);
        Creategun=new JButton("Create Gun");
        Creategun.setBounds(450,100,150,30);
        Creategun.addActionListener(main);
        main.add(Creategun);
        main.add(Createop);

        jt.setBounds(200,160,700,500);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane sp=new JScrollPane(jt);
        jt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jt.rowAtPoint(evt.getPoint());
                int col = jt.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(tableModel.getValueAt(row,0));

                }
            }
        });
        sp.setBounds(200,160,700,500);
        main.add(sp);
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
        JLabel tierslabel=new JLabel("OP Tier");
        main.add(sidelabel);
        main.add(genderlabel);
        sidelabel.setBounds(100,20,70,30);
        genderlabel.setBounds(250,20,70,30);
        guntypelabel.setBounds(400,20,70,30);
        tierslabel.setBounds(550,20,70,30);
        filter=new JButton("Filter");
        filter.addActionListener(main);
        main.add(filter);
        main.add(guntypelabel);
        main.add(tierslabel);
        filter.setBounds(100,100,70,30);

        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filter) {
            boolean flag=false;
                try {
                    String query="SELECT OPS.*, GUNS.name AS GunName,GUNS.type,GUNS.ammo,GUNS.rating FROM OPS JOIN GUNS ON OPS.gunid=GUNS.id";
                    if (!GunType.getSelectedItem().equals("ALL")){

                        if (!flag){query+=" WHERE ";}
                        else {
                            query+=" AND ";
                        }
                        flag=true;
                        query+="GUNS.type= '"+GunType.getSelectedItem()+"'";
                    }
                    if (!tiers.getSelectedItem().equals("ALL")){
                        if (!flag){query+=" WHERE ";}
                        else {
                            query+=" AND ";
                        }
                        flag=true;
                        query+="OPS.tier= '"+tiers.getSelectedItem()+"'";
                    }
                    if (!gender.getSelectedItem().equals("ALL")){
                        if (!flag){query+=" WHERE ";}
                        else {
                            query+=" AND ";
                        }
                        flag=true;
                        query+="OPS.gender= '"+gender.getSelectedItem()+"'";
                    }
                    if (!Side.getSelectedItem().equals("ALL")){
                        if (!flag){query+=" WHERE ";}
                        else {
                            query+=" AND ";
                        }
                        flag=true;
                        query+="OPS.side= '"+Side.getSelectedItem()+"'";
                    }
                    rs=st.executeQuery(query);
                    tableModel.setRowCount(0);
                    while (rs.next()){
                        String [] rowdata={rs.getString("id"),rs.getString("gender"),rs.getString("name"),rs.getString("side"),rs.getString("tier"),rs.getString("GunName"),rs.getString("type"),rs.getString("ammo"),rs.getString("rating")};
                        tableModel.addRow(rowdata);

                    }



                } catch (Exception a) {
                    System.out.println(a);
                }

            try {
                rs=st.executeQuery("Select * FROM OPS WHERE OPS.name='"+opname.getText()+"'");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource()==Createop){
            new CreateOP(conn,st,rs);
        }
        else if(e.getSource()==Creategun){
            new CreateGUN(conn,st,rs);
        }

        }
    }

