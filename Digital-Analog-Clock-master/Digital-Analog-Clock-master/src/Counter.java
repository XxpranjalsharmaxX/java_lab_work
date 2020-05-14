import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Counter {
	public Timer myTimer;
	private final int delay = 1000; //1 second = 1000 milliseconds
	
	public Counter(ActionListener counterListener){
		myTimer = new Timer(delay, counterListener);
	}
	public void start(){
		myTimer.start();
	}
	public void stop(){
		myTimer.stop();
	}
	public boolean isRunning(){
		return myTimer.isRunning();
	}
	public void addActionListener(ActionListener a){
		myTimer.addActionListener(a);
	}
	
}
