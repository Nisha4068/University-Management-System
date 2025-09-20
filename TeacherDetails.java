import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class TeacherDetails extends JFrame implements ActionListener {

    JTable table;
    JComboBox<String> cempId;

    JButton search, print, update, add, cancel;
   TeacherDetails(){
    getContentPane().setBackground(Color.WHITE);
    setLayout(null);

    JLabel heading = new JLabel("Search by Employee Id");
    heading.setBackground(Color.WHITE);

    heading.setBounds(20,20,150,20);
    add(heading);

    cempId = new JComboBox<>();
    cempId.setBounds(180,20,150,20);
    add(cempId);

    try{
        Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("select * from teacher");
        while(rs.next()){
            
    // String roll = rs.getString("rollno");
    // System.out.println("Found roll: " + roll); // debug
     
            cempId.addItem(rs.getString("empId"));
        }

    }catch(Exception e){
        e.printStackTrace();
    }
    table = new  JTable();

    try{
        Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("select * from teacher");
       table.setModel(DbUtils.resultSetToTableModel(rs));

    }catch(Exception e){
        e.printStackTrace();
    }

    JScrollPane jsp = new JScrollPane(table);
    jsp.setBounds(0,100,900,600);
    add(jsp);

    search = new JButton("Search");
    search.setBounds(20,70,80,20);
    search.addActionListener(this);
    add(search);

    print = new JButton("Print");
    print.setBounds(120,70,80,20);
    print.addActionListener(this);
    add(print);

    add = new JButton("Add");
    add.setBounds(220,70,80,20);
    add.addActionListener(this);
    add(add);

    update = new JButton("Update");
    update.setBounds(320,70,80,20);
    update.addActionListener(this);
    add(update);

    cancel = new JButton("Cancel");
    cancel.setBounds(420,70,80,20);
    cancel.addActionListener(this);
    add(cancel);


    setSize(900,700);
    setLocation(300,100);
    setVisible(true);
   }

    //@Override
    public void actionPerformed(ActionEvent ae) {
   
        if(ae.getSource()== search){
            String query = "select * from teacher where empId = '"+cempId.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()== print){
            try{
                table.print();

            }catch(Exception e){
               e.printStackTrace();
            }        
        }
        else if(ae.getSource()== add){
            setVisible(false);
            new AddStudent();
        }
         else if(ae.getSource()== update){
            setVisible(false);
            new UpdateTeacher();
        }
        else{
             setVisible(false);
        }
    }
public static void main(String[] args) {
        new TeacherDetails();
        
    }
}