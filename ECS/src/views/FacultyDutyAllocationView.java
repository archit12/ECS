package views;
import dutyAllocator.DutyAllocator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacultyDutyAllocationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_roomCount;
	private JLabel lblDateOfDuty;
	private JLabel lblDdmmyyyy;
	private JTextField textField_date;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyDutyAllocationView frame = new FacultyDutyAllocationView();
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
	public FacultyDutyAllocationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_roomCount = new JLabel("No. of Rooms");
		lbl_roomCount.setBounds(92, 134, 73, 14);
		contentPane.add(lbl_roomCount);
		
		textField_roomCount = new JTextField();
		textField_roomCount.setBounds(175, 131, 86, 20);
		contentPane.add(textField_roomCount);
		textField_roomCount.setColumns(10);
		
		JButton btn_allocate = new JButton("Allocate");
		btn_allocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date_string = null;
				int room_count = 0;
				try {
					date_string = textField_date.getText();
					room_count = Integer.parseInt(textField_roomCount.getText());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (NullPointerException e2) {
					e2.printStackTrace();
				}
				DutyAllocator allocator = new DutyAllocator(date_string, room_count);
				if (allocator.allocate()) {
					JOptionPane.showMessageDialog(contentPane, "All Duties Allocated");
				} else {
					JOptionPane.showMessageDialog(contentPane,
						    "An error occured in allocating duties",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_allocate.setBounds(175, 174, 89, 23);
		contentPane.add(btn_allocate);
		
		lblDateOfDuty = new JLabel("Date Of Duty");
		lblDateOfDuty.setBounds(92, 90, 73, 14);
		contentPane.add(lblDateOfDuty);
		
		lblDdmmyyyy = new JLabel("(dd-mm-yyyy)");
		lblDdmmyyyy.setBounds(269, 90, 80, 14);
		contentPane.add(lblDdmmyyyy);
		
		textField_date = new JTextField();
		textField_date.setBounds(175, 87, 86, 20);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
	}
}
