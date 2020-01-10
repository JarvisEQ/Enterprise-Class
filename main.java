import java.util.*;
import javax.swing.*;

public class main{
	
	public static void main(String args[]){
		
		main Main = new main();
		System.out.println("Exiting Program");
	}
	
	public void main(){
		
		//initise UI
		userInterface();
	}
	
	public void userInterface(){
		JFrame frame = new JFrame("Store");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}
