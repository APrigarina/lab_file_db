import javax.swing.JFrame;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.JButton;

public class CreateDB{

	protected JFrame frame;
	private JTextField baseName;
	private JTextField tableName;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDB window = new CreateDB();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public CreateDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(765, 286);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0421\u043E\u0437\u0434\u0430\u043D\u0438\u0435 \u0431\u0430\u0437\u044B \u0434\u0430\u043D\u043D\u044B\u0445");
		label.setBounds(0, 0, 758, 38);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(22, 52, 111, 39);
		label_1.setBackground(new Color(240, 240, 240));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(label_1);
		
		JLabel label_4 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0442\u0430\u0431\u043B\u0438\u0446\u044B");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(22, 109, 166, 38);
		frame.getContentPane().add(label_4);
		
		baseName = new JTextField();
		baseName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		baseName.setColumns(10);
		baseName.setBounds(185, 52, 543, 39);
		frame.getContentPane().add(baseName);
		
		tableName = new JTextField();
		tableName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableName.setColumns(10);
		tableName.setBounds(185, 109, 543, 38);
		frame.getContentPane().add(tableName);
		
		
		JButton createDataBase = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0431\u0430\u0437\u0443 \u0434\u0430\u043D\u043D\u044B\u0445");
		createDataBase.setFont(new Font("Tahoma", Font.BOLD, 18));
		createDataBase.setBounds(238, 183, 303, 38);
		createDataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileWork.createFile(baseName.getText(), tableName.getText());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				 dbIsCreated createdWindow = new dbIsCreated(baseName.getText());
				 createdWindow.frame.setVisible(true);
				 frame.dispose();
			}
		});
		
		frame.getContentPane().add(createDataBase);
		
		
	}
}
