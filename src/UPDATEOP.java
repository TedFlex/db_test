import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UPDATEOP  extends JFrame implements ActionListener {
    JTextField  fields;
    JTextField [] fieldss;
    JLabel  labels;
    JButton submit;
    Statement st;
    ButtonGroup bg;
    //id,Gender,Name,side,gunid,tier
    JRadioButton id;
    JRadioButton Gender;
    JRadioButton Name;
    JRadioButton side;
    JRadioButton gunid;
    JRadioButton tier;
    int fieldflag;

    UPDATEOP(Connection conn, Statement st, ResultSet rs){
        this.st=st;
        this.setSize(1280,720);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        int ecks=160;
        int x=100;
        fieldss=new JTextField[6];
        fields=new JTextField();
        labels=new JLabel("OPERATOR ID");
        for (int i = 0; i < 6; i++) {

            fieldss[i]=new JTextField();
            fieldss[i].setBounds(200,ecks,90,30);

            ecks+=40;;
            this.add(fieldss[i]);
            fieldss[i].setEnabled(false);
        }
        fields.setBounds(x,60,90,30);
        labels.setBounds(x,20,70,30);

        this.add(fields);
        this.add(labels);


        bg=new ButtonGroup();
        id=new JRadioButton("id");
        Gender=new JRadioButton("Gender");
        Name=new JRadioButton("Name");
        side=new JRadioButton("side");
        gunid=new JRadioButton("gunid");
        tier=new JRadioButton("tier");

        bg.add(id);
        bg.add(Gender);
        bg.add(Name);
        bg.add(side);
        bg.add(gunid);
        bg.add(tier);


        id.setBounds(x,160,90,30);
        Gender.setBounds(x,200,90,30);
        Name.setBounds(x,240,90,30);
        side.setBounds(x,280,90,30);
        gunid.setBounds(x,320,90,30);
        tier.setBounds(x,360,90,30);

        id.addActionListener(this);
        Gender.addActionListener(this);
        Name.addActionListener(this);
        side.addActionListener(this);
        gunid.addActionListener(this);
        tier.addActionListener(this);

        submit=new JButton("UPDATE");
        submit.addActionListener(this);
        submit.setBounds(100,100,100,30);
        this.add(submit);
        this.add(id);
        this.add(Gender);
        this.add(Name);
        this.add(side);
        this.add(gunid);
        this.add(tier);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==id){
            fieldss[0].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==0){
                    continue;
                }
                fieldss[i].setEnabled(false);

            }
            fieldflag=0;
        }else if (e.getSource()==Gender){
            fieldss[1].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==1){
                    continue;
                }
                fieldss[i].setEnabled(false);
            }
            fieldflag=1;

        }else if (e.getSource()==Name){
            fieldss[2].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==2){
                    continue;
                }
                fieldss[i].setEnabled(false);
            }
            fieldflag=2;

        }else if (e.getSource()==side){
            fieldss[3].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==3){
                    continue;
                }
                fieldss[i].setEnabled(false);
            }
            fieldflag=3;

        }else if (e.getSource()==gunid){
            fieldss[4].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==4){
                    continue;
                }
                fieldss[i].setEnabled(false);
            }
            fieldflag=4;

        }else if (e.getSource()==tier){
            fieldss[5].setEnabled(true);
            for (int i = 0; i < 6; i++) {
                if (i==5){
                    continue;
                }
                fieldss[i].setEnabled(false);
            }
            fieldflag=5;

        }



        if (e.getSource()==submit){
            try {
                st.execute("SET IDENTITY_INSERT OPS ON;");
            }catch (Exception a){
                System.out.println(a);
            }
            String rowdata="";
            String query="";
            switch(fieldflag){
                case 0:
                    rowdata=fieldss[0].getText();
                     query="UPDATE OPS SET id='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
                case 1:
                    rowdata=fieldss[1].getText();
                     query="UPDATE OPS SET gender='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
                case 2:
                    rowdata=fieldss[2].getText();
                     query="UPDATE OPS SET name='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
                case 3:
                    rowdata=fieldss[3].getText();
                     query="UPDATE OPS SET side='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
                case 4:
                    rowdata=fieldss[4].getText();
                     query="UPDATE OPS SET gunid='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
                case 5:
                    rowdata=fieldss[5].getText();
                     query="UPDATE OPS SET TIER='"+rowdata+"' WHERE ID = "+fields.getText();
                    break;
            }
            System.out.println(query);
            try {
                st.execute(query);
            }catch (Exception a){
                System.out.println(a);
            }
        }
    }
}
