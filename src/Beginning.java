import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Beginning {

	protected JFrame frame;
	private JTextField dataBaseName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Beginning window = new Beginning();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Beginning() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 512, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0414\u043E\u0431\u0440\u043E \u043F\u043E\u0436\u0430\u043B\u043E\u0432\u0430\u0442\u044C");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 458, 50);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0431\u0430\u0437\u044B \u0434\u0430\u043D\u043D\u044B\u0445, \u043A\u043E\u0442\u043E\u0440\u0443\u044E \u0445\u043E\u0442\u0438\u0442\u0435 \u043E\u0442\u043A\u0440\u044B\u0442\u044C");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(29, 50, 429, 42);
		frame.getContentPane().add(lblNewLabel);
		
		dataBaseName = new JTextField();
		dataBaseName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dataBaseName.setBounds(29, 105, 202, 42);
		frame.getContentPane().add(dataBaseName);
		dataBaseName.setColumns(10);
		
		JButton openButton = new JButton("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenDB openedWindow = new OpenDB(dataBaseName.getText());
				if(OpenDB.fileExist==true) {
					openedWindow.frame.setVisible(true);
					frame.dispose();
				}
				else {
					openedWindow.frame.setVisible(false);
				}
			}
		});
		openButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		openButton.setBounds(256, 105, 202, 42);
		frame.getContentPane().add(openButton);
		
		JButton createButton = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043D\u043E\u0432\u0443\u044E!");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		createButton.setBounds(141, 219, 195, 67);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateDB createWindow = new CreateDB();
				createWindow.frame.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(createButton);
		
		JLabel label_1 = new JLabel("\u0418\u041B\u0418");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(141, 160, 195, 46);
		frame.getContentPane().add(label_1);
		
	}
	
}


