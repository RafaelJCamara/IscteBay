import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchImageGUI {

	private JFrame mainWindow;
	private JTextArea resultArea;
	
	public SearchImageGUI() {
		mainWindow=new JFrame("Searching Images");
		mainWindow.setLayout(new BorderLayout());
		addFrameContent();
		mainWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainWindow.setSize(500, 500);
		mainWindow.setVisible(true);
	}
	
	public void addFrameContent() {
		northBorder();
		centerBorder();
	}
	
	public void northBorder() {
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new GridLayout(1,3));
		JLabel textLabel=new JLabel("Texto a procurar: ");
		JTextField textField=new JTextField("");
		JButton searchButton=new JButton("Procurar");
		southPanel.add(textLabel);
		southPanel.add(textField);
		southPanel.add(searchButton);
		mainWindow.add(southPanel,BorderLayout.NORTH);
	}
	
	public void centerBorder() {
		resultArea=new JTextArea();
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new GridLayout(2,1));
		JButton descarregar=new JButton("Descarregar");
		JProgressBar download=new JProgressBar();
		download.setStringPainted(true);
		download.setMinimum(0);
		download.setMaximum(100);
		
		descarregar.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FillingProgressBar(mainWindow,download).start();
			}
			
		});
		
		rightPanel.add(descarregar);
		rightPanel.add(download);		
		centerPanel.add(resultArea);
		centerPanel.add(rightPanel);
		mainWindow.add(centerPanel,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new SearchImageGUI();
	}
	
}
