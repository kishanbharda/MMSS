import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.sql.*;
import java.awt.event.*;

public class MMSS implements ActionListener
{
	JFrame f;

	JLabel lblUsername,lblPassword;
	JTextField txtUsername;
	JPasswordField pfPassword;
	JButton btnLogin,btnExit;
	
	JPanel lp;
    
	MMSS()
	{
		f=new JFrame("MEERA MEDICAL STORE SYSTEM");
		
		f.setLayout(null);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		ImageIcon icon=new ImageIcon("icon/mmss1.png");
		JLabel lblIcon=new JLabel(icon);
		lblIcon.setBounds(80,20,1200,200);
		f.add(lblIcon);
		
		lp=new JPanel();
		
		lp.setLayout(null);
		lp.setBounds(400,300,500,300);		
		lp.setBorder(BorderFactory.createTitledBorder("LOG IN"));
		
		lblUsername=new JLabel("Username");
        lblPassword=new JLabel("Password");
                
		txtUsername=new JTextField(15);
        pfPassword=new JPasswordField(8);
		
		btnLogin=new JButton("Login");
		btnExit=new JButton("Exit");
		
		//Font layout
		lblUsername.setFont(new Font("Arial",Font.BOLD,18));
		txtUsername.setFont(new Font("Arial",Font.BOLD,18));
		lblPassword.setFont(new Font("Arial",Font.BOLD,18));

		//Implementation of component
		lblUsername.setBounds(10,50,100,50);
		lp.add(lblUsername);
		txtUsername.setBounds(160,50,250,50);
		lp.add(txtUsername);
		lblPassword.setBounds(10,110,100,50);
		lp.add(lblPassword);
		pfPassword.setBounds(160,110,250,50);
		lp.add(pfPassword);
		btnLogin.setBounds(160,220,100,30);
		lp.add(btnLogin);
		btnExit.setBounds(305,220,100,30);
		lp.add(btnExit);
                
                
        //event
        btnExit.addActionListener(this);
        btnLogin.addActionListener(this);
	
		
		f.add(lp);
		
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
        
    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()==btnExit)
      {
        System.exit(0);
      }
            
      if(ae.getSource()==btnLogin)
      {
		login();
	  }
            
    }
		
	public void login()
	{
				
		String uname=txtUsername.getText().trim();
		String pass=new String(pfPassword.getText().trim());
			
		int flag=0;
        try
        {
           
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://database\\MMSS.accdb");
            String query="Select * from valid_users where USERNAME=? and PASSWORD=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,uname);
			pst.setString(2,pass);			
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				home h=new home();
				f.setVisible(false);
				f.dispose();
			}
			else
			{
				String msg=new String("Sorry!!! Incorrect username or password...!");
                JOptionPane op=new JOptionPane();
                op.showMessageDialog(lp,msg);
			}
			pst.close();
            con.close();
               
        }
        catch(Exception e)
        {
			System.out.println(e);
        }
	}
	
	public static void main(String args[])
	{
		MMSS M1=new MMSS();
	}
	
}