import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {

	private JFrame mainWindow;
	private JPanel userListPanel;
	DefaultListModel<String> dlm = new DefaultListModel<>();
	private JList<String> userList=new JList<>(dlm);
	
	public GUI(String user) {
		mainWindow=new JFrame("THEISCTEBAY - "+user+"'s window");
		mainWindow.setLayout(new BorderLayout());
		addFrameContent();
		mainWindow.setSize(500,500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	public void addFrameContent() {
		userListPanel=new JPanel();
		mainWindow.add(userListPanel,BorderLayout.CENTER);
		addButtons();
	}
	

	public void addButtons() {
		JButton showUsersButton=new JButton("Show current users");
		showUsersButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setVisible(true);
				userListPanel.add(userList);
				mainWindow.pack();
				mainWindow.setSize(500, 500);
				mainWindow.setVisible(true);
			}
			
		});
		JButton resetUsersButton=new JButton("Reset list");
		resetUsersButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mainWindow.setVisible(true);
				userListPanel.remove(userList);
				mainWindow.pack();
				mainWindow.setSize(500, 500);
				mainWindow.setVisible(true);
			}
			
		});		
		JButton searchImages=new JButton("Search Images");
		searchImages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new SearchImageGUI();
				
			}
			
		});

		JPanel aux=new JPanel();
		aux.setLayout(new FlowLayout());
		mainWindow.add(aux,BorderLayout.SOUTH);
		aux.add(showUsersButton);
		aux.add(resetUsersButton);
		aux.add(searchImages);
	}
	
	public void showListElements() {
		userListPanel.add(userList);
	}
	
	public void updateList(String info) {
		if(info.equals("End")) {
			dlm.removeElement("End");
			dlm.addElement("End");
		}
		if(!dlm.contains(info)) {
			dlm.addElement(info); /*adicionar um utilizador à lista de utilizador da GUI*/
		}
		mainWindow.setVisible(true);
	}
	
}