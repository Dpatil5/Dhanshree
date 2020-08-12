import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class TestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 543);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pLable = new JLabel("ERROR REPORTING SYSTEM FOR C &C++");
		pLable.setForeground(Color.RED);
		pLable.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pLable.setBounds(12, 13, 826, 42);
		contentPane.add(pLable);
		
		JLabel error = new JLabel("Error");
		error.setForeground(Color.BLACK);
		error.setFont(new Font("Times New Roman", Font.BOLD, 17));
		error.setBounds(27, 69, 56, 16);
		contentPane.add(error);
		
		JTextArea errorArea = new JTextArea();
		errorArea.setBackground(Color.WHITE);
		errorArea.setLineWrap(true);
		errorArea.setRows(3);
		errorArea.setBounds(179, 53, 207, 48);
		contentPane.add(errorArea);
		
		JTextArea solArea = new JTextArea();
		solArea.setLineWrap(true);
		solArea.setBounds(179, 186, 226, 54);
		contentPane.add(solArea);
		
		JButton CButton = new JButton("C Error");
		CButton.setBackground(Color.WHITE);
		CButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CButton.setForeground(Color.BLUE);
		CButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==CButton)
				{
					String querry=errorArea.getText().trim();
					
					String errorType="c";
					String matchQuerry="%"+querry+"%";
					Connectivity ct1=new Connectivity();
					ResultSet obj=ct1.DisplayResult(matchQuerry,errorType);
					try
					{
						boolean flag=false;
						while(obj.next())
						{  flag=true;
							solArea.setText(obj.getString(0));
						}
						if(flag==false)
						{
		
							JOptionPane.showMessageDialog(CButton, "solution is not available");
						}
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			}
		});
		CButton.setBounds(23, 125, 97, 25);
		contentPane.add(CButton);
		
		JButton CPPButton = new JButton("C++Error");
		CPPButton.setBackground(Color.WHITE);
		CPPButton.setForeground(Color.BLUE);
		CPPButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CPPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==CPPButton)
				{
					String querry=errorArea.getText().trim();
					String errorType="cpp";
					String matchQuerry="%"+querry+"%";
					//JOptionPane.showMessageDialog(CPPButton, matchQuerry);
					Connectivity ct1=new Connectivity();
					ResultSet obj=ct1.DisplayResult(matchQuerry,errorType);
					
					try
					{
						boolean flag=false;
						
						while(obj.next())
						{flag=true;
							solArea.setText(obj.getString(1));
						}
						if(flag==false)
						{
							JOptionPane.showMessageDialog(CPPButton, "sorry solution is not available");
						}
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
				}
			
		});
		CPPButton.setBounds(240, 125, 133, 27);
		contentPane.add(CPPButton);
		
		JLabel solLabel = new JLabel("solution");
		solLabel.setForeground(Color.BLACK);
		solLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		solLabel.setBounds(27, 201, 63, 22);
		contentPane.add(solLabel);
		
		
	}
}
