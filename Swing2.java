

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Swing2 extends JFrame{
	JTextField t1;
	JLabel l1;
	JButton b1;

	int p=2;

	Swing2(){
//		Angaben zum Fenster		
		setTitle("Potenzen von 2");  
		setSize(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
// 		Label erzeugen
		add(l1 = new JLabel ("Potenzen von 2 "));
// 		Textfeld erzeugen
		add(t1 = new JTextField(""));
		t1.setText(p +"        ");	
// 		Button erzeugen
		add (b1 = new JButton ("rechne"));
// 		Button mit Listener versorgen
		b1.addActionListener (new Action ());
		setVisible(true); 

	}
//	innere Klasse zur Behandlung der Button-Ereignisse 
	class Action implements ActionListener {

//	Behandlungsmethode fuer Aktionen
	  public void actionPerformed(ActionEvent e){
		  p=p*2;
		  t1.setText(p +"");	
	 }
}
	public static void main (String args[]) {
		new Swing2();
	}
}
