import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;


public class StudentLeave extends JFrame implements ActionListener {
    JComboBox<String> crollno,ctime;
    JDateChooser dcdate;
    JButton submit, cancel;
    StudentLeave(){
        setSize(500,550);
        setLocation(550,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply Leave (Student)");
        //heading.setBackground(Color.WHITE);
        heading.setBounds(40,50,300,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel lblrollno = new JLabel("Search by Roll Number");
        lblrollno.setBackground(Color.WHITE);
        lblrollno.setBounds(60,100,200,20);
          lblrollno.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblrollno);

    crollno = new JComboBox<>();
    crollno.setBounds(60,130,200,20);
    add(crollno);

    try{
        Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("select * from student");
        while(rs.next()){
            
    String roll = rs.getString("rollno");
    System.out.println("Found roll: " + roll); // debug
     
            crollno.addItem(rs.getString("rollno"));
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    JLabel lbldate = new JLabel("Date");
    lbldate.setBackground(Color.WHITE);
    lbldate.setBounds(60,180,200,20);
    lbldate.setFont(new Font("Tahoma",Font.PLAIN,18));
    add(lbldate);
        
     dcdate = new JDateChooser();
    dcdate.setBounds(60,210,200,25);
    add(dcdate);

    JLabel lbltime = new JLabel("time");
    lbltime.setBackground(Color.WHITE);
    lbltime.setBounds(60,260,200,20);
    lbltime.setFont(new Font("Tahoma",Font.PLAIN,18));
    add(lbltime);
        
     ctime = new JComboBox<>();
    ctime.setBounds(60,290,200,20);
    ctime.addItem("Full Day");
    ctime.addItem("Half Day");
    add(ctime);
   
        submit = new JButton("Submit");
        submit.setBounds(60,350,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahona",Font.BOLD,15));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahona",Font.BOLD,15));
        cancel.addActionListener(this);
        add(cancel);
         setVisible(true);

    }
    
    //@Override
    public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()== submit){
      String rollno = (String)crollno.getSelectedItem();
      String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
      String duration = (String)ctime.getSelectedItem();
 
      String query = "insert into studentleave values('"+rollno+"','"+date+"','"+duration+"')";
      try{
         Conn c = new Conn();
         c.s.executeUpdate(query);
         JOptionPane.showMessageDialog(null, "Leave Confirmed");
         setVisible(false);
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    else{
        setVisible(false);
    }
    }
    public static void main(String[] args) {
        new StudentLeave();
    }
}
