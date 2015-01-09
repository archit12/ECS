package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.util.List;

import data.Department;
import helpers.ExcelToMysqlHelper;

public class FacultyExcelUpload extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private String path = "";
	private Department faculty_dept = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyExcelUpload frame = new FacultyExcelUpload();
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
	public FacultyExcelUpload() {
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
		
		JButton btn_select_file = new JButton("Select File");
		btn_select_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose Your File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					path = selectedFile.getAbsolutePath();
				}
			}
		});
		btn_select_file.setBounds(166, 151, 89, 23);
		contentPane.add(btn_select_file);
		
		JLabel lblNewLabel = new JLabel(".xls or .xlsx only");
		lblNewLabel.setBounds(265, 155, 79, 14);
		contentPane.add(lblNewLabel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		Department dept = new Department();
		List<Department> departments = dept.getAll();
		Department[] departments_array = new Department[departments.size()];
		departments_array = departments.toArray(departments_array);
		comboBox.setModel(new DefaultComboBoxModel(departments_array));
		comboBox.setBounds(132, 96, 156, 20);
		contentPane.add(comboBox);
		
		JButton btn_upload = new JButton("Upload");
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				faculty_dept = (Department) comboBox.getSelectedItem();
				ExcelToMysqlHelper exporter = new ExcelToMysqlHelper();
				if(exporter.export(path, faculty_dept)) {
					JOptionPane.showMessageDialog(contentPane, "All Details Exported");
				}
				else {
					JOptionPane.showMessageDialog(contentPane,
						    "An error occured in exporting Details",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_upload.setBounds(166, 197, 89, 23);
		contentPane.add(btn_upload);
	}
}
