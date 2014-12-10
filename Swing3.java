


import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Swing3 extends JFrame{
	JTextField t1, t2,t3, t4;
	JLabel l1, l2, l3;
	JButton b1;

	Swing3(){
//		Angaben zum Fenster		
		setTitle("Zinssatz");  
		setSize(300,485);
		setLocation(200,150);
		Container c = getContentPane();
		c.setBackground(new Color(0,255,255));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
// 		Label erzeugen
		add(l1 = new JLabel ("Betrag = "));
		l1.setBounds (30,30, 100, 20);
		add(l2 = new JLabel ("Zinssatz = "));
		l2.setBounds (30,70, 100, 20);

		add(l3 = new JLabel ("Laufzeit = "));
		l3.setBounds (30,110, 100, 20);
		
// 		Textfeld erzeugen
		add(t1 = new JTextField(""));
		t1.setBounds (130,30, 100, 20);
		add(t2 = new JTextField(""));
		t2.setBounds (130,70, 60, 20);
		
		add(t3 = new JTextField(""));
		t3.setBounds (130,110, 60, 20);
		
		add(t4 = new JTextField(""));
		t4.setBounds (130,190, 100, 20);
		
		
		
// 		Button erzeugen
		add (b1 = new JButton ("rechne"));
		b1.setBounds (130,140, 90,20);
// 		Button mit Listener versorgen
		b1.addActionListener (new Action ());
		setVisible(true); 
	}
//	innere Klasse zur Behandlung der Button-Ereignisse 
	class Action implements ActionListener {

//	Behandlungsmethode fuer Aktionen
	  public void actionPerformed(ActionEvent e){
		  String s;
		  double betrag,zinssatz,zeit;
		  double zinsen;
		  s = t1.getText(); 
		  betrag = Double.parseDouble(s);
		  s = t2.getText(); 
		  zinssatz = Double.parseDouble(s);
		  s = t3.getText(); 
		  zeit = Double.parseDouble(s);
		  
		  zinsen = betrag*(1+ ((zinssatz/100)*zeit));
		  
		  //z=(int)Math.pow(z,ex);
		  t4.setText(zinsen +"");	
	 }
}

	public static void main (String args[]) {
		new Swing3();
	}
}
