import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class dbIsCreated {

	protected JFrame frame;
	private String dataBaseName;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public dbIsCreated(String dataBaseName) {
		this.dataBaseName = dataBaseName;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0411\u0430\u0437\u0430 \u0434\u0430\u043D\u043D\u044B\u0445 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0441\u043E\u0437\u0434\u0430\u043D\u0430!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(0, 35, 432, 50);
		frame.getContentPane().add(label);
		
		JButton openDataBase = new JButton("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		openDataBase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		openDataBase.setBounds(233, 173, 163, 41);
		openDataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				 OpenDB openedWindow = new OpenDB(dataBaseName);
				 openedWindow.frame.setVisible(true);
				 frame.dispose();


			}
		});
		frame.getContentPane().add(openDataBase);
		
		JButton addNewField = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u0435");
		addNewField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addNewField.setBounds(42, 173, 163, 41);
		addNewField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 AddField  addField = new AddField(dataBaseName);
				 addField.frame.setVisible(true);
				 frame.dispose();
			}
		});
		frame.getContentPane().add(addNewField);
	}
}
