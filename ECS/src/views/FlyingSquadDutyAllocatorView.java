package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dutyAllocator.FlyingSquadDutyAllocator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlyingSquadDutyAllocatorView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNoOfFloors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlyingSquadDutyAllocatorView frame = new FlyingSquadDutyAllocatorView();
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
	public FlyingSquadDutyAllocatorView() {
		setTitle("Flying Squad Duty Allocation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNoOfFloors = new JLabel("No. of Floors");
		lblNoOfFloors.setBounds(131, 115, 70, 14);
		contentPane.add(lblNoOfFloors);
		
		JButton btnAllocate = new JButton("Allocate");
		btnAllocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlyingSquadDutyAllocator allocator = new FlyingSquadDutyAllocator(Integer.parseInt(textFieldNoOfFloors.getText()));
				if (allocator.allocate()) {
					JOptionPane.showMessageDialog(contentPane, "Flying Squad Duties Allocated");
				} else {
					JOptionPane.showMessageDialog(contentPane,
						    "An error occured in allocating duties",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAllocate.setBounds(167, 161, 89, 23);
		contentPane.add(btnAllocate);
		
		textFieldNoOfFloors = new JTextField();
		textFieldNoOfFloors.setBounds(211, 112, 86, 20);
		contentPane.add(textFieldNoOfFloors);
		textFieldNoOfFloors.setColumns(10);
	}

}
