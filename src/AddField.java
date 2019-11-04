import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.SwingConstants;


public class AddField {

	protected JFrame frame;
	private JTextField inputName;
	private JTextField inputGenre;
	private JTextField inputDate;
	private JTextField inputCountry;
	private String dataBaseName;
	private JTextField inputID;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AddField(String dataBaseName) {
		this.dataBaseName = dataBaseName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 540, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_4 = new JLabel("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u0435");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(0, 0, 520, 33);
		frame.getContentPane().add(label_4);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(23, 45, 110, 39);
		frame.getContentPane().add(lblId);

		JLabel label = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(23, 108, 110, 39);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u0416\u0430\u043D\u0440");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(23, 172, 69, 36);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u0414\u0430\u0442\u0430 \u0432\u044B\u0445\u043E\u0434\u0430");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(23, 240, 108, 39);
		frame.getContentPane().add(label_2);
		
		JLabel label_5 = new JLabel("\u0421\u0442\u0440\u0430\u043D\u0430");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(23, 307, 108, 39);
		frame.getContentPane().add(label_5);
		
		inputID = new JTextField();
		inputID.setColumns(10);
		inputID.setBounds(143, 46, 343, 39);
		frame.getContentPane().add(inputID);

		inputName = new JTextField();
		inputName.setBounds(143, 109, 343, 39);
		frame.getContentPane().add(inputName);
		inputName.setColumns(10);
		
		inputGenre = new JTextField();
		inputGenre.setBounds(143, 172, 345, 39);
		frame.getContentPane().add(inputGenre);
		inputGenre.setColumns(10);

		inputDate = new JTextField();
		inputDate.setBounds(143, 241, 343, 39);
		frame.getContentPane().add(inputDate);
		inputDate.setColumns(10);
		
		inputCountry = new JTextField();
		inputCountry.setColumns(10);
		inputCountry.setBounds(143, 308, 345, 39);
		frame.getContentPane().add(inputCountry);

		JButton button = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(23, 396, 147, 46);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = inputID.getText();
					if (FileWork.alreadyExist(dataBaseName, id)) {
						String text = "Объект с данным ID уже существует!";
						JDialog dialog = new Dialog(text);
						dialog.setFont(new Font("Tahoma", Font.PLAIN, 16));
						//dialog.setBounds(400, 200, 340, 318);
						dialog.setLocation(400,200); 
						dialog.setVisible(true);
					}
					else {
					FileWork.writeIntoFile(dataBaseName, id, 0);
					inputID.setText("");
					String name = inputName.getText();
					FileWork.writeIntoFile(dataBaseName, name, 1);
					inputName.setText("");
					String genre = inputGenre.getText();
					FileWork.writeIntoFile(dataBaseName, genre, 2);
					inputGenre.setText("");
					String date = inputDate.getText();
					FileWork.writeIntoFile(dataBaseName, date, 3);
					inputDate.setText("");
					String country = inputCountry.getText();
					FileWork.writeIntoFile(dataBaseName, country, 4);
					inputCountry.setText("");
					}
				} catch (FileNotFoundException ex) {

				} catch (IOException ex2) {
				}
			}
		});
		


		frame.getContentPane().add(button);
		
		JButton save = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		save.setFont(new Font("Tahoma", Font.PLAIN, 16));
		save.setBounds(341, 396, 147, 46);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				 OpenDB openedWindow = new OpenDB(dataBaseName);
				 openedWindow.frame.setVisible(true);
				 frame.dispose();
			}
		});
		frame.getContentPane().add(save);

	}
}



