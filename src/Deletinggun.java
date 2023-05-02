import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Deletinggun extends JFrame implements ActionListener {
    JTextField  fields;
    JLabel  labels;
    JButton submit;
    Statement st;

    Deletinggun(Connection conn, Statement st, ResultSet rs){
        this.st=st;
        this.setSize(1280,720);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        int x=100;
        fields=new JTextField();
        labels=new JLabel("Gun_id");

            fields.setBounds(x,60,90,30);
            labels.setBounds(x,20,70,30);

            this.add(fields);
        this.add(labels);



        submit=new JButton("Create");
        submit.addActionListener(this);
        submit.setBounds(100,100,100,30);
        this.add(submit);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==submit){
            try {
                st.execute("SET IDENTITY_INSERT OPS ON;");
            }catch (Exception a){
                System.out.println(a);
            }
           String rowdata=fields.getText();


            String query="DELETE FROM GUNS WHERE ID="+rowdata;



            System.out.println(query);
            try {
                st.execute(query);
            }catch (Exception a){
                System.out.println(a);
            }
        }
    }
}
