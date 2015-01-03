package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.io.*;

public class FacultyUpload extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyUpload frame = new FacultyUpload();
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
	public FacultyUpload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lbl_department = new JLabel("Enter Faculty Department");
		lbl_department.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_department.setBounds(132, 55, 156, 30);
		contentPane.add(lbl_department);
		
		JButton btn_upload = new JButton("Upload File");
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose Your File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile.getName());
				}
			}
		});
		btn_upload.setBounds(166, 151, 89, 23);
		contentPane.add(btn_upload);
		
		JLabel lblNewLabel = new JLabel(".xls or .xlsx only");
		lblNewLabel.setBounds(265, 155, 79, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CSE", "ECE", "EN", "ME", "IT", "EI", "CE", "AS", "MBA", "MCA", "TIFAC"}));
		comboBox.setBounds(132, 96, 156, 20);
		contentPane.add(comboBox);
	}
}
