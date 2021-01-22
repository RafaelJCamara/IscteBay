import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class DealWithClient extends Thread{

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Directory directory;
	
	public DealWithClient (Socket socket, Directory directory) {
		this.socket=socket;
		this.directory=directory;
	}  
	
	@Override   
	public void run() {
		try {
			doConnections();
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
	}

	private void serve(){
		try {
			while(true) {
				String msg=in.readLine();
				directory.createUser(msg);
				directory.broadcast();
			}
		}catch (IOException e) {
			System.out.println("Alguém saiu do nosso servidor"); 
		}
	}
	
	public void updateUsers() {
		LinkedList<User> list=directory.getcurrentUsers();
		for(User s:list) {
			String[] v=new String[3];
			v[0]=s.getUserName();
			v[1]=s.getAddress();
			v[2]=Integer.toString(s.getPort());
			String aux="";
			for(int i=0;i!=v.length;i++) {
				aux.concat(v[i]+" ");
			}
			out.print(aux);
		}
	}

	private void doConnections() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out =new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		directory.addClent(out); // adiciona um utilizador à lista de utilizadores quando a ligação ao servidor é criada
	}
}
