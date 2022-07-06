import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Loginpage implements ActionListener
{	
	JFrame frame=new JFrame();
	JButton loginbutton=new JButton("Login");
	JButton resetbutton=new JButton("Reset");
	JTextField userlogin=new JTextField();
	JPasswordField password=new JPasswordField();
	JLabel useridlabel=new JLabel("Account Number:");
	JLabel userpasslabel=new JLabel("PIN:");
	JLabel messagelabel=new JLabel();
	JLabel welcomelabel=new JLabel();
	String userid="",name;
	static String mode="";
	Atm_client a1;
	
	public Loginpage()
	{
		useridlabel.setBounds(50,100,150,25);
		userpasslabel.setBounds(50,150,150,25);
		messagelabel.setBounds(125,250,250,35);
		messagelabel.setFont(new Font(null,Font.ITALIC,25));
		welcomelabel.setBounds(75,50,400,25);
		welcomelabel.setFont(new Font(null,Font.BOLD,20));
		welcomelabel.setText("WELCOME TO ABC BANK ATM");
		userlogin.setBounds(200,100,200,25);
		password.setBounds(200,150,200,25);
		
		loginbutton.setBounds(125,200,100,25);
		loginbutton.setFocusable(false);
		loginbutton.addActionListener(this);
		resetbutton.setBounds(225,200,100,25);
		resetbutton.setFocusable(false);
		resetbutton.addActionListener(this);
		
		
		frame.add(useridlabel);
		frame.add(userpasslabel);
		frame.setTitle("ATM Login");
		frame.add(userlogin);
		frame.add(password);
		frame.add(messagelabel);
		frame.add(welcomelabel);
		frame.add(loginbutton);
		frame.add(resetbutton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==resetbutton)
		{
			userlogin.setText("");
			password.setText("");
		}
		if(e.getSource()==loginbutton)
		{
			userid=userlogin.getText();
			String password1=String.valueOf(password.getPassword());
			try 
			{
				mode=Atm_client.getdata(userid,password1);
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			if(mode.equals("valid"))
			{
				JOptionPane.showMessageDialog(frame,"You are sucessfully logged in!");
				frame.dispose();
				name=Atm_client.receivefromserver();
				new Atm_client(userid,name);
			}
			else	
			JOptionPane.showMessageDialog(frame,"Invalid Account number or PIN","Alert",JOptionPane.WARNING_MESSAGE);
		}	
	}

	public static void main(String args[])
	{
		Atm_client.getConnection();
		new Loginpage();
	}
}
