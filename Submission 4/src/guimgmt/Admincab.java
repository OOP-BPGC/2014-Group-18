package guimgmt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;

import CabB.ConnectCab;
import CabB.SWD;
import Login.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admincab {

	JFrame frameAdmincab;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Admincab(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final User user) {
		frameAdmincab = new JFrame();
		frameAdmincab.setBounds(100, 100, 450, 300);
		frameAdmincab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAdmincab.getContentPane().setLayout(null);
		
		JLabel lblWhichRoutesFare = new JLabel("Which route's fare do you want to change ?");
		lblWhichRoutesFare.setBounds(10, 11, 281, 28);
		frameAdmincab.getContentPane().add(lblWhichRoutesFare);
		
		JLabel lblStartingPoint = new JLabel("Starting point");
		lblStartingPoint.setBounds(20, 50, 110, 14);
		frameAdmincab.getContentPane().add(lblStartingPoint);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bits", "Vasco", "Panjim", "Airport", "Madgaon"}));
		comboBox.setBounds(172, 50, 119, 20);
		frameAdmincab.getContentPane().add(comboBox);
		
		JLabel lblEndingPoint = new JLabel("Ending Point");
		lblEndingPoint.setBounds(20, 86, 101, 14);
		frameAdmincab.getContentPane().add(lblEndingPoint);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Bits", "Vasco", "Panjim", "Airport", "Madgaon"}));
		comboBox_1.setBounds(172, 81, 119, 20);
		frameAdmincab.getContentPane().add(comboBox_1);
		
		JButton btnCheckPresentFare = new JButton("Check Present Fare for one way");
		btnCheckPresentFare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					String startpt = (String) comboBox.getSelectedItem();
					String endpt = (String) comboBox_1.getSelectedItem();
					
					int duration = 1;
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
		btnCheckPresentFare.setBounds(172, 124, 187, 23);
		frameAdmincab.getContentPane().add(btnCheckPresentFare);
		
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("0");
		formattedTextField.setBounds(172, 172, 85, 20);
		frameAdmincab.getContentPane().add(formattedTextField);
		
		JButton btnConfirmChange = new JButton("Confirm Change");
		btnConfirmChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String startpt = (String) comboBox.getSelectedItem();
				String endpt = (String) comboBox_1.getSelectedItem();
				int newfare = Integer.parseInt(formattedTextField.getText());
				ConnectCab cc = new ConnectCab();
				String sql = "UPDATE `oop`.`cabroutes` SET `"+endpt+"` = '"+newfare+"' WHERE `From` = '"+startpt+"'";
				cc.cabRoute(sql, endpt, 3);
				JOptionPane.showMessageDialog (null,"Fare from "+startpt+" to "+endpt+" has been successfully changed to Rs."+newfare, "Fare changed", JOptionPane.INFORMATION_MESSAGE);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try { frameAdmincab.setVisible(false);
							Admin window = new Admin(user);
							window.frameAdmin.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnConfirmChange.setBounds(168, 208, 154, 23);
		frameAdmincab.getContentPane().add(btnConfirmChange);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try { frameAdmincab.setVisible(false);
							Admin window = new Admin(user);
							window.frameAdmin.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnReturn.setBounds(47, 208, 89, 23);
		frameAdmincab.getContentPane().add(btnReturn);
		
		JLabel lblEnterNewFare = new JLabel("Enter new fare");
		lblEnterNewFare.setBounds(20, 175, 110, 14);
		frameAdmincab.getContentPane().add(lblEnterNewFare);
	}
}
