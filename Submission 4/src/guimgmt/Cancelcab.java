package guimgmt;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import CabB.CabDB;
import CabB.ConnectCab;
import CabB.SWD;
import Login.User;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cancelcab {

	JFrame frameCancelcab;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Cancelcab(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final User user) {
		frameCancelcab = new JFrame();
		frameCancelcab.setBounds(100, 100, 450, 300);
		frameCancelcab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCancelcab.getContentPane().setLayout(null);
		
		JButton btnReturnToPrevious = new JButton("Return to previous page");
		btnReturnToPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameCancelcab.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							CabChoice window1 = new CabChoice(user);
							window1.frameCabChoice.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnReturnToPrevious.setBounds(191, 212, 206, 39);
		frameCancelcab.getContentPane().add(btnReturnToPrevious);
		
		
		
		try {
			CabDB cdb = new CabDB();
			long x = cdb.CabCheck(user);
			if(x==0){
				JLabel lblYourEarlierBookings = new JLabel("You do not have any bookings.");
				lblYourEarlierBookings.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblYourEarlierBookings.setBounds(58, 75, 302, 62);
				frameCancelcab.getContentPane().add(lblYourEarlierBookings);
			}
			else{	JLabel lblYourEarlierBookings = new JLabel("Your earlier bookings are :-");
			lblYourEarlierBookings.setBounds(10, 0, 152, 26);
			frameCancelcab.getContentPane().add(lblYourEarlierBookings);
				
				ConnectCab cc = new ConnectCab();
				String sql = "SELECT * FROM `oop`.`cab` WHERE `Student` = '"+user.name+"'";
				String[] entries = cc.Connection2(sql); 
				int total=0;
				for(int i=0;i<entries.length;i++){
					if(entries[i]==null){
						break;
					}
					else{
						total++;
					}
				}
				String[] finale = new String[total];
				for(int i=0;i<total;i++){
					finale[i]=entries[i];
				}
				final JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(finale));
				comboBox.setBounds(10, 22, 414, 20);
				frameCancelcab.getContentPane().add(comboBox);
				
				JButton btnCancelBooking = new JButton("Cancel booking");
				btnCancelBooking.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ConnectCab cc = new ConnectCab();
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						String inp = comboBox.getSelectedItem().toString();
						String inpid = inp.substring(7, 9);
						int cabid = Integer.parseInt(inpid);
						//System.out.println(cabid);
						String query = "SELECT * FROM `oop`.`cab` WHERE `cab`.`AppNo` = "+cabid;
						String bookdate = cc.getDate(query);
						try {
							Date dobook = sdf.parse(bookdate);
							Calendar cal = Calendar.getInstance();
							Calendar cal1 = Calendar.getInstance();
							cal.setTime(new Date());
							cal1.setTime(dobook);// sets calendar time/date
							cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
							if(cal1.compareTo(cal)<0){ //checks whether cal>cal1
								JOptionPane.showMessageDialog(null, "You cannot cancel your cab within in LAST HOUR before journey", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else{
								SWD swd = new SWD();
								try {
									CabDB db2 = new CabDB();
									swd.refund(user, cabid);
									db2.cancel(user,cabid);
									JOptionPane.showMessageDialog (null, "Cab has been successfully cancelled and amount refunded.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
									EventQueue.invokeLater(new Runnable() {
										public void run() {
											try {
												frameCancelcab.setVisible(false);
												CabChoice window1 = new CabChoice(user);
												window1.frameCabChoice.setVisible(true);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									});
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				btnCancelBooking.setBounds(191, 162, 206, 39);
				frameCancelcab.getContentPane().add(btnCancelBooking);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
