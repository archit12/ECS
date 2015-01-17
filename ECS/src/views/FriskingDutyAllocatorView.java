package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import dutyAllocator.FriskingDutyAllocator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriskingDutyAllocatorView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFriskingFacultyCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriskingDutyAllocatorView frame = new FriskingDutyAllocatorView();
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
	public FriskingDutyAllocatorView() {
		setTitle("Frisking Duty Allocation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFriskingFacultyCount = new JLabel("No. of Faculties");
		lblFriskingFacultyCount.setBounds(112, 112, 85, 14);
		contentPane.add(lblFriskingFacultyCount);
		
		textFieldFriskingFacultyCount = new JTextField();
		textFieldFriskingFacultyCount.setBounds(206, 109, 86, 20);
		contentPane.add(textFieldFriskingFacultyCount);
		textFieldFriskingFacultyCount.setColumns(10);
		
		JButton btnAllocate = new JButton("Allocate");
		btnAllocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FriskingDutyAllocator allocator = new FriskingDutyAllocator(Integer.parseInt(textFieldFriskingFacultyCount.getText()));
				if (allocator.allocate()) {
					JOptionPane.showMessageDialog(contentPane, "Frisking Duties Allocated");
				} else {
					JOptionPane.showMessageDialog(contentPane,
						    "An error occured in allocating duties",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAllocate.setBounds(170, 162, 85, 23);
		contentPane.add(btnAllocate);
	}
}
