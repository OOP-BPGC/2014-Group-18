package guimgmt;

import java.awt.EventQueue;

import CabB.*;
import Login.User;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import javax.swing.JSpinner;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.SpinnerDateModel;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bookcab {

	JFrame frameBookcab;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Bookcab(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final User user) {
		frameBookcab = new JFrame();
		frameBookcab.setBounds(100, 100, 450, 300);
		frameBookcab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBookcab.getContentPane().setLayout(null);
		
		JLabel lblEnterDetailsOf = new JLabel("Enter Details of the CAB");
		lblEnterDetailsOf.setBounds(158, 0, 266, 32);
		frameBookcab.getContentPane().add(lblEnterDetailsOf);
		
		JLabel lblStartingPointOf = new JLabel("Starting Point of Jouney");
		lblStartingPointOf.setBounds(10, 55, 180, 20);
		frameBookcab.getContentPane().add(lblStartingPointOf);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bits", "Vasco", "Panjim", "Airport", "Madgaon"}));
		comboBox.setBounds(233, 55, 95, 20);
		frameBookcab.getContentPane().add(comboBox);
		
		
		JLabel lblEndingPointOf = new JLabel("Ending Point of Journey");
		lblEndingPointOf.setBounds(10, 86, 180, 20);
		frameBookcab.getContentPane().add(lblEndingPointOf);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Bits", "Vasco", "Panjim", "Airport", "Madgaon"}));
		comboBox_1.setBounds(233, 86, 95, 20);
		frameBookcab.getContentPane().add(comboBox_1);
		
		
		JLabel lblSelectDateOf = new JLabel("Select Date and Time of Journey");
		lblSelectDateOf.setBounds(10, 117, 219, 20);
		frameBookcab.getContentPane().add(lblSelectDateOf);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1416421800000L), new Date(1416421800000L), new Date(1420050540000L), Calendar.DAY_OF_MONTH));
		spinner.setBounds(233, 117, 148, 20);
		frameBookcab.getContentPane().add(spinner);
		
		
		JLabel lblOneWayOr = new JLabel("One Way or Round Trip");
		lblOneWayOr.setBounds(10, 154, 148, 20);
		frameBookcab.getContentPane().add(lblOneWayOr);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 2, 1));
		spinner_1.setBounds(233, 154, 29, 20);
		frameBookcab.getContentPane().add(spinner_1);
		
		JButton btnCheckFare = new JButton("Check Fare");
		btnCheckFare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String startpt = (String) comboBox.getSelectedItem();
				String endpt = (String) comboBox_1.getSelectedItem();
				
				int duration = Integer.valueOf(spinner_1.getValue().toString());
				SWD swd = new SWD();
				int fare = swd.farecalc(startpt, endpt, null, duration);
				if(fare==0){
					JOptionPane.showMessageDialog(null, "Select different starting and ending points.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog (null, "Your fare is Rs."+fare, "Fare Calculator", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		btnCheckFare.setBounds(200, 185, 163, 23);
		frameBookcab.getContentPane().add(btnCheckFare);
		
		JButton btnNewButton = new JButton("Confirm Booking");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String startpt = (String) comboBox.getSelectedItem();
				String endpt = (String) comboBox_1.getSelectedItem();
				Calendar cal = Calendar.getInstance();
				Calendar cal1 = Calendar.getInstance();
				cal.setTime(new Date()); // sets calendar time/date
		        cal.add(Calendar.HOUR_OF_DAY, 2);
		        String date = spinner.getValue().toString();
		        //System.out.println(date);
		        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				try {
					Date dobook = sdf.parse(date);
					//System.out.println(dobook);
					cal1.setTime(dobook);
					if(cal1.compareTo(cal)<0){ //checks whether cal>cal1
						JOptionPane.showMessageDialog(null, "You  have book cab atleast 2 hours earlier....", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						//JOptionPane.showMessageDialog (null,dobook.getDate()+" "+(dobook.getMonth()+1)+" "+dobook.getYear(), "Fare Calculator", JOptionPane.INFORMATION_MESSAGE);
						Cab ca = new Cab();
						SWD swd = new SWD();
						ca.source=(String) comboBox.getSelectedItem();
						ca.dest = (String) comboBox_1.getSelectedItem();
						ca.day = dobook.getDate();
						ca.mon = dobook.getMonth()+1;
						ca.year = 2014;
						System.out.println(ca.year);
						ca.hr = dobook.getHours();
						ca.min = dobook.getMinutes();
					    ca.duration = Integer.valueOf(spinner_1.getValue().toString());
					    ca.fare = swd.farecalc(startpt, endpt, null, ca.duration);
					    if(ca.fare==0){
					    	JOptionPane.showMessageDialog(null, "Select different starting and ending points.", "Error", JOptionPane.ERROR_MESSAGE);
					    }
					    else{
					    	CabDB cdb = new CabDB();
						    cdb.Book(user, ca);
						    JOptionPane.showMessageDialog (null,"Cab has been booked successfully "+user.name, "Cab Booked", JOptionPane.INFORMATION_MESSAGE);
						    JOptionPane.showMessageDialog (null,"Amount has also been added to your account ", "Charges", JOptionPane.INFORMATION_MESSAGE);
						    EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										frameBookcab.setVisible(false);
										CabChoice window1 = new CabChoice(user);
										window1.frameCabChoice.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
					    }
					    
					}
						
				} catch (ParseException | SQLException e) {
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(200, 219, 163, 23);
		frameBookcab.getContentPane().add(btnNewButton);
		
		JButton btnReturnWithoutBooking = new JButton("Return without booking");
		btnReturnWithoutBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameBookcab.setVisible(false);
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
		btnReturnWithoutBooking.setBounds(24, 219, 166, 23);
		frameBookcab.getContentPane().add(btnReturnWithoutBooking);
		
		
		
		
		
	}
}
