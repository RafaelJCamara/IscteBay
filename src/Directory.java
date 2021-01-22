import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Directory {

	private static final int PORT=8070;
	private LinkedList<PrintWriter> outs=new LinkedList<>(); //todos os utilizadores para enviar a mensagem
	private LinkedList<User> current_users=new LinkedList<>();
	
	private void go() {
		ServerSocket s;
		try {
			s=new ServerSocket(PORT);
			try {
				while(true) {
					Socket socket=s.accept();
					new DealWithClient(socket,this).start();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}finally {
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList getcurrentUsers() {
		return current_users;
	}
	
	public synchronized void createUser(String user) {
		String[] finalUser=user.split(" ");
		current_users.add(new User(finalUser[0],finalUser[1],finalUser[2]));
	}
	
	public synchronized void broadcast() {
		for(PrintWriter pw:outs) {
			for(User user:current_users) {
				pw.println(user.toString());
			}
			pw.println("End");
		}
	}
	
	public synchronized void addClent(PrintWriter out) {
		outs.add(out);
	}
	
	public static void main(String[]args) {
		new Directory().go();
	}
}