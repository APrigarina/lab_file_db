
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;


import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class OpenDB {

	protected JFrame frame;
	private JTextField searchField;
	private String dataBaseName;
	protected static boolean fileExist = true;
	private JTextField searchValue;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public OpenDB(String dataBaseName) {
		this.dataBaseName = dataBaseName;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private Object[][] data;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 827, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u041A\u0438\u043D\u043E\u043F\u043E\u0438\u0441\u043A");
		label.setBounds(0, 0, 832, 62);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(label);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		
		Object[] columnsHeader;
		try {
			columnsHeader = FileWork.getHeader(dataBaseName);
			tableModel.setColumnIdentifiers(columnsHeader);
		} catch (FileNotFoundException e) {
			fileExist = false;
			String text = "Файл не существует!";
			JDialog dialog = new Dialog(text);
			dialog.setFont(new Font("Tahoma", Font.PLAIN, 16));
			//dialog.setBounds(400, 200, 340, 318);
			dialog.setLocation(400,200); 
			dialog.setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        
		try {
			data = FileWork.getField(dataBaseName);
			for (int i = 1; i < data.length; i++)
		            tableModel.addRow(data[i]);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}catch (IOException e) {
			e.printStackTrace();
		}  
        
                // Создание таблицы на основании модели данных
                JTable dataBase = new JTable(tableModel);
                dataBase.setFont(new Font("Tahoma", Font.PLAIN, 17));
                dataBase.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
                dataBase.setBackground(new Color(255, 255, 255));
                dataBase.setRowHeight(50);
                JScrollPane scrollPane = new JScrollPane(dataBase);
                scrollPane.setBounds(32, 162, 743, 178);
                frame.getContentPane().add(scrollPane);
                
        Box contents = new Box(BoxLayout.Y_AXIS);
        scrollPane.setRowHeaderView(contents);
        
        JButton back = new JButton("\u041D\u0430\u0437\u0430\u0434");
        back.setBounds(32, 505, 225, 35);
        back.setFont(new Font("Tahoma", Font.PLAIN, 17));
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Beginning menu = new Beginning();
				menu.frame.setVisible(true);
				frame.dispose();
        	}
        });
        frame.getContentPane().add(back);
        
        JButton addField = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u0435");
        addField.setBounds(550, 385, 225, 35);
        addField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		AddField addWindow = new AddField(dataBaseName);
				 addWindow.frame.setVisible(true);
				 frame.dispose();
        	}
        });
        addField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        frame.getContentPane().add(addField);
        
        JButton clearDataBase = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C \u0431\u0430\u0437\u0443 \u0434\u0430\u043D\u043D\u044B\u0445");
        clearDataBase.setBounds(550, 445, 225, 35);
        clearDataBase.setFont(new Font("Tahoma", Font.PLAIN, 17));
        clearDataBase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					FileWork.clearFile(dataBaseName);
					// обновить таблицу
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        frame.getContentPane().add(clearDataBase);
        
        JButton deleteDataBase = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0431\u0430\u0437\u0443 \u0434\u0430\u043D\u043D\u044B\u0445");
        deleteDataBase.setBounds(550, 505, 225, 35);
        deleteDataBase.setFont(new Font("Tahoma", Font.PLAIN, 17));
        deleteDataBase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					if (FileWork.deleteFile(dataBaseName)==true) {
						String text = "База данных удалена";
						JDialog dialog = new Dialog(text);
						dialog.setFont(new Font("Tahoma", Font.PLAIN, 16));
						//dialog.setBounds(400, 200, 340, 318);
						dialog.setLocation(400,200); 
						dialog.setVisible(true);
					}
				}catch (FileNotFoundException e) {
					System.out.println("file not found" + e);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        frame.getContentPane().add(deleteDataBase);
        
        JLabel searchFieldLabel = new JLabel("\u041F\u043E\u0438\u0441\u043A  \u043F\u043E \u043F\u043E\u043B\u044E");
        searchFieldLabel.setBounds(30, 88, 135, 37);
        searchFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchFieldLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        frame.getContentPane().add(searchFieldLabel);
        
        searchField = new JTextField();
        searchField.setBounds(198, 89, 135, 37);
        frame.getContentPane().add(searchField);
        searchField.setColumns(10);
        
        JLabel searchValueLabel = new JLabel("\u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435");
        searchValueLabel.setBounds(333, 89, 135, 37);
        searchValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        frame.getContentPane().add(searchValueLabel);
        
        searchValue = new JTextField();
        searchValue.setBounds(476, 90, 135, 37);
        searchValue.setColumns(10);
        frame.getContentPane().add(searchValue);
        
        JButton searchButton = new JButton("\u0418\u0441\u043A\u0430\u0442\u044C");
        searchButton.setBounds(670, 86, 105, 41);
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			int foundedValue = FileWork.search(dataBaseName, searchField.getText(), searchValue.getText());
        			int count = tableModel.getRowCount();
        			int i=0;
        			while (count>0) {
        				tableModel.removeRow(i);
        				count--;
        			}
        			tableModel.addRow(data[foundedValue]);
				}catch (FileNotFoundException e) {
					System.out.println("file not found" + e);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        frame.getContentPane().add(searchButton);
        
        // Создание кнопки удаления строки таблицы
//        JButton remove = new JButton("Удалить");
//        remove.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Номер выделенной строки
//                int idx = table1.getSelectedRow();
//                // Удаление выделенной строки
//                tableModel.removeRow(idx);
//            }
//        });

	}
}
