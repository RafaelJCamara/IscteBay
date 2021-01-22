import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class FillingProgressBar extends Thread{

	private JProgressBar progress;
	private JFrame mainWindow;
	
	public FillingProgressBar(JFrame window,JProgressBar j) {
		this.mainWindow=window;
		this.progress=j;
	}
	
	@Override
	public void run() {
		for(int i=0;i<=progress.getMaximum();i++) {
			progress.setValue(i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==progress.getMaximum()) {
				JOptionPane.showMessageDialog(mainWindow, "Download Completo");
			}
		}
	}
	
}
