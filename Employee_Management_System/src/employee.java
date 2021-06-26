import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class employee {

	private JFrame frame;
	private JTextField ename;
	private JTextField eid;
	private JTextField esalary;
	private JTextField edept;
	private JTextField search;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					employee window = new employee();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public employee() 
	{
		initialize();
		connect();
		table();
	}
	
	PreparedStatement p;
	Connection conn;
	ResultSet rs;
	
	public void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/employee_management_system","root","root");
		}
		catch(SQLException e)
		{
			System.out.println("SQL Exception");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class Not Found Exceptionn");
		}
	}
	
	void table()
	{
		try
		{
			p = conn.prepareStatement("select * from employee");
			rs = p.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			System.out.println("SQL Not worked");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1214, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Register", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(29, 77, 525, 339);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee_Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 44, 193, 33);
		panel.add(lblNewLabel);
		
		JLabel lblEmployeeid = new JLabel("Employee_ID");
		lblEmployeeid.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmployeeid.setBounds(10, 88, 193, 33);
		panel.add(lblEmployeeid);
		
		JLabel lblEmployeejoiningdate = new JLabel("Employee_Joining_Date");
		lblEmployeejoiningdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmployeejoiningdate.setBounds(10, 132, 193, 33);
		panel.add(lblEmployeejoiningdate);
		
		JLabel lblEmployeesalary = new JLabel("Employee_Salary");
		lblEmployeesalary.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmployeesalary.setBounds(10, 176, 193, 33);
		panel.add(lblEmployeesalary);
		
		JLabel lblEmployeedepartment = new JLabel("Employee_Department");
		lblEmployeedepartment.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmployeedepartment.setBounds(10, 220, 193, 33);
		panel.add(lblEmployeedepartment);
		
		ename = new JTextField();
		ename.setBounds(248, 44, 209, 33);
		panel.add(ename);
		ename.setColumns(10);
		
		eid = new JTextField();
		eid.setColumns(10);
		eid.setBounds(248, 90, 209, 33);
		panel.add(eid);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(248, 132, 209, 33);
		panel.add(dateChooser);
		
		esalary = new JTextField();
		esalary.setColumns(10);
		esalary.setBounds(248, 178, 209, 33);
		panel.add(esalary);
		
		edept = new JTextField();
		edept.setColumns(10);
		edept.setBounds(248, 222, 209, 33);
		panel.add(edept);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name,id,salary,dept;
				Date joiningdate;
				
				name = ename.getText();
				id = eid.getText();
				joiningdate = dateChooser.getDate();
				salary = esalary.getText();
				dept = edept.getText();
				
				String s=eid.getText();
				String reg = "[A-Z]{1}[0-9]{4}";
				Pattern pa = Pattern.compile(reg);
				Matcher m = pa.matcher(s);
				
				
				if(eid.getText().trim().isEmpty() && ename.getText().trim().isEmpty() && esalary.getText().trim().isEmpty() && edept.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "All Fileds are Required");
				}
				else if(ename.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Name is Empty");
				}
				else if(eid.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "ID is Empty");
				}
				else if(esalary.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Salary is Empty");
				}
				else if(edept.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Department is Empty");
				}
				else if(m.matches()==false)
				{
					JOptionPane.showMessageDialog(null, "ID is not Formatted");
				}
				
				else
				
				try
				{
					p = conn.prepareStatement("insert into employee(e_name,e_id,e_joining_date,e_salary,e_dept) values(?, ?, ?, ?, ?)");
					p.setString(1, name);
					p.setString(2, id);
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					String date = sdf.format(dateChooser.getDate());
					p.setString(3, date);
					p.setString(4, salary);
					p.setString(5, dept);
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "Inserted");
					table();
					ename.setText("");
					eid.setText("");
					dateChooser.setDate(null);
					esalary.setText("");
					edept.setText("");
					ename.requestFocus();
				}
				catch(SQLException e1)
				{
					System.out.println("SQL Exception");
				}
			}
		});
		btnNewButton.setBounds(26, 276, 120, 40);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(190, 276, 120, 40);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ename.setText("");
				eid.setText("");
				dateChooser.setDate(null);
				esalary.setText("");
				edept.setText("");
				search.setText("");
				ename.requestFocus();
			}
		});
		btnClear.setBounds(358, 276, 120, 40);
		panel.add(btnClear);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(29, 459, 525, 78);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmployeeid_1 = new JLabel("Employee_ID");
		lblEmployeeid_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmployeeid_1.setBounds(10, 20, 193, 33);
		panel_1.add(lblEmployeeid_1);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					String sid = search.getText();
					
					p = conn.prepareStatement("select e_name,e_id,e_joining_date,e_salary,e_dept from employee where e_id = ?");
					p.setString(1, sid);
					ResultSet rs = p.executeQuery();
					
					if(rs.next() == true)
					{
						String name = rs.getString(1);
						String id = rs.getString(2);
						Date joiningdate = rs.getDate(3);
						String salary = rs.getString(4);
						String dept = rs.getString(5);
						
						ename.setText(name);
						eid.setText(id);
						dateChooser.setDate(joiningdate);
						esalary.setText(salary);
						edept.setText(dept);
					}
					else
					{
						ename.setText("");
						eid.setText("");
						dateChooser.setDate(null);
						esalary.setText("");
						edept.setText("");
					}
				}
				catch(SQLException e2)
				{
					System.out.println("Not Updated");
				}
			}
		});
		search.setColumns(10);
		search.setBounds(254, 22, 209, 33);
		panel_1.add(search);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 76, 606, 339);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String sid;
				
				sid = search.getText();
				
				try
				{
					p = conn.prepareStatement("delete from employee where e_id=?");
					p.setString(1, sid);
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted");
					table();
					ename.setText("");
					eid.setText("");
					dateChooser.setDate(null);
					esalary.setText("");
					edept.setText("");
					search.setText("");
					ename.requestFocus();
				}
				catch(SQLException e1)
				{
					System.out.println("SQL Exception");
				}
			}
		});
		btnDelete.setBounds(641, 484, 120, 40);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear_1_1 = new JButton("UPDATE");
		btnClear_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 
			{
                String name,id,salary,dept,sid;
				Date joiningdate;
				name = ename.getText();
				id = eid.getText();
				joiningdate = dateChooser.getDate();
				salary = esalary.getText();
				dept = edept.getText();
				sid = search.getText();
				
				try
				{
					p = conn.prepareStatement("update employee set e_name=?,e_id=?,e_joining_date=?,e_salary=?,e_dept=? where e_id=?");
					p.setString(1, name);
					p.setString(2, id);
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
					String date = sdf.format(dateChooser.getDate());
					p.setString(3, date);
					p.setString(4, salary);
					p.setString(5, dept);
					p.setString(6, sid);
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "Updated");
					table();
					ename.setText("");
					eid.setText("");
					dateChooser.setDate(null);
					esalary.setText("");
					edept.setText("");
					search.setText("");
					ename.requestFocus();
				}
				catch(SQLException e1)
				{
					System.out.println("SQL Exception");
				}
			}
		});
		btnClear_1_1.setBounds(832, 484, 120, 40);
		frame.getContentPane().add(btnClear_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(259, 11, 635, 55);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Management System");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel_1.setBounds(85, 0, 465, 55);
		panel_2.add(lblNewLabel_1);
	}
}
