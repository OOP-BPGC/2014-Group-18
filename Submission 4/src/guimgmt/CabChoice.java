package guimgmt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import CabB.CabDB;
import Login.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CabChoice {

	JFrame frameCabChoice;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public CabChoice(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final User user) {
		frameCabChoice = new JFrame();
		frameCabChoice.setBounds(100, 100, 450, 300);
		frameCabChoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCabChoice.getContentPane().setLayout(null);
		
		JButton btnMakeNewBooking = new JButton("Make New Booking");
		btnMakeNewBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameCabChoice.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Bookcab windowbc = new Bookcab(user);
							windowbc.frameBookcab.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnMakeNewBooking.setBounds(130, 47, 218, 38);
		frameCabChoice.getContentPane().add(btnMakeNewBooking);
		
		JButton btnCancelEarlierBooking = new JButton("Cancel Earlier Booking");
		btnCancelEarlierBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {frameCabChoice.setVisible(false);
							Cancelcab window = new Cancelcab(user);
							window.frameCancelcab.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnCancelEarlierBooking.setBounds(130, 107, 218, 38);
		frameCabChoice.getContentPane().add(btnCancelEarlierBooking);
		
		JButton btnReturnToPrevious = new JButton("Return to previous window");
		btnReturnToPrevious.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {frameCabChoice.setVisible(false);
							Choice window = new Choice(user);
							window.frameChoice.setVisible(true);
							
						} catch (Exception e) {
							System.out.println(user.name);
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnReturnToPrevious.setBounds(130, 166, 218, 38);
		frameCabChoice.getContentPane().add(btnReturnToPrevious);
	}

}
