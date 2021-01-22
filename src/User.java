import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class User extends Thread{

	private GUI gui;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private String name; 
	private String address;
	private int user_port;
	private String directory_port;
	private String imagesFolder;
	
	public User(String name, String address, String port) {
		this.name=name;
		this.address=address;
		this.user_port=Integer.parseInt(port);
	}
	
	public String getUserName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
		
	public int getPort() {
		return user_port;
	}
	
	public User(String c1, String c2, String c3, String c4, String c5) {
		this.name=c1;
		this.address=c2;
		this.directory_port=c3;
		this.user_port=Integer.parseInt(c4);
		this.imagesFolder=c5;
	}

	private synchronized void doConnections(String s, String port) throws IOException{ 
		InetAddress endereco= InetAddress.getByName(s);
		socket=new Socket(endereco,Integer.parseInt(port)); 
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public synchronized void sendUserInfo(String user_name, String user_address, String userPort) {
		String user=user_name+" "+user_address+" "+userPort;
		out.println(user);
		gui=new GUI(user_name);
		while(true) {
			try {
				String s = in.readLine();
				if(s!=null) {
					gui.updateList(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.address+" "+this.user_port;
	}
	
	@Override
	public void run() {
		try {
			doConnections(this.address,this.directory_port);
			sendUserInfo(this.name,this.address,Integer.toString(this.user_port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void removeMe() {
		String user=name+" "+address+" "+user_port;
		System.out.println("Vou eliminar-me");
		out.println(user);
	}
	
 	public static void main(String[] args) {
		new User(args[0],args[1],args[2],args[3],args[4]).start();
	}
	
}