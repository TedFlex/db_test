import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateOP extends JFrame implements ActionListener {
    JTextField [] fields;
    JLabel [] labels;
    JButton submit;
Statement st;

    CreateOP(Connection conn, Statement st, ResultSet rs){
            this.st=st;
            this.setSize(1280,720);
//            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(null);
            int x=100;
            fields=new JTextField[5];
            labels=new JLabel[5];
        for (int i = 0; i < 5; i++) {
            labels[i]=new JLabel();
            fields[i]=new JTextField();
            fields[i].setBounds(x,60,90,30);
            labels[i].setBounds(x,20,70,30);
            x+=100;
            this.add(fields[i]);

        }
        labels[0].setText("Gender");
        labels[1].setText("Name");
        labels[2].setText("Side");
        labels[3].setText("gunid");
        labels[4].setText("tier");
        for (int i = 0; i < 5; i++) {
            this.add(labels[i]);
        }
        submit=new JButton("Create");
        submit.addActionListener(this);
        submit.setBounds(100,100,100,30);
        this.add(submit);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            String[]rowdata=new String[5];
            for (int i = 0; i < 5; i++) {
                rowdata[i]=fields[i].getText();
            }






            String query="INSERT INTO OPS(gender,name,side,gunid,tier) VALUES (";
            for (int i = 0; i < 5; i++) {
                if (i!=3){
                     if (i==4){
                         query+="'"+rowdata[i]+"'";
                    }else {
                         query+="'"+rowdata[i]+"',";
                     }

            }else {
                    query+=rowdata[i]+",";
                }
            }
            query+=")";
            try {
                st.execute(query);
            }catch (Exception a){
                System.out.println(a);
            }
        }
    }
}
