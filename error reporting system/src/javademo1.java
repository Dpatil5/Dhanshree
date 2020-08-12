import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class javademo1 extends JFrame {

	private JPanel contentPane;
	private JRadioButton CRButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javademo1 frame = new javademo1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public javademo1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 443);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Table.sortIconColor"));
		contentPane.setForeground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel prLabel = new JLabel("ERROR REPORTING SYSTEM FOR C AND C++");
		prLabel.setBackground(new Color(199, 21, 133));
		prLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		prLabel.setBounds(125, 13, 526, 28);
		contentPane.add(prLabel);
		
		JLabel ErrEntry = new JLabel("Enter Error");
		ErrEntry.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ErrEntry.setBounds(63, 114, 142, 16);
		contentPane.add(ErrEntry);
		
		JTextArea ErrText = new JTextArea();
		ErrText.setRows(3);
		ErrText.setForeground(new Color(255, 255, 0));
		ErrText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ErrText.setBackground(UIManager.getColor("TextArea.background"));
		ErrText.setBounds(183, 86, 506, 75);
		contentPane.add(ErrText);
		
		JTextArea solArea = new JTextArea();
		solArea.setRows(10);
		solArea.setBackground(UIManager.getColor("TextArea.background"));
		solArea.setBounds(183, 273, 506, 65);
		contentPane.add(solArea);
		
		
		CRButton = new JRadioButton("C ERROR");
		CRButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource()==CRButton)
				{
					String querry=ErrText.getText().trim();
					String errorType="c";
					String matchQuerry="%"+querry+"%";
					Connectivity ct1=new Connectivity();
					ResultSet obj=ct1.DisplayResult(matchQuerry,errorType);
					try
					{
						while(obj.next())
						{
							solArea.setText(obj.getString(2));
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			}
		});
		CRButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		CRButton.setBounds(63, 218, 127, 25);
		contentPane.add(CRButton);
		
		JRadioButton CPRButton = new JRadioButton("C++ ERROR");
		CPRButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query=ErrText.getText();
				if(e.getSource()==CPRButton)
				{
					String querry=ErrText.getText().trim();
					String errorType="cpp";
					String matchQuerry="%"+querry+"%";
					Connectivity dobj=new Connectivity(); 
					ResultSet ct=dobj.DisplayResult(matchQuerry,errorType);
					try
					{
						while (ct.next())
						{
							solArea.setText(ct.getString(3));
						}
					}
					catch(Exception ex2)
					{
						ex2.printStackTrace();
					}
				}
			}
		});
		CPRButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		CPRButton.setBounds(340, 219, 127, 25);
		contentPane.add(CPRButton);
		
		JLabel solLabel = new JLabel("SOLUTION");
		solLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		solLabel.setBounds(63, 289, 86, 20);
		contentPane.add(solLabel);
		
	
	}
}
