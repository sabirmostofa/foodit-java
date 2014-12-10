
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*; 

public class PSKWmain extends JFrame{
	JTextField t1, t2;
	JLabel l1,l2;
	JButton b1,b2;
	int wertPS, wertKW;

	PSKWmain(){
//		Angaben zum Fenster		
		setTitle("PS  <->  KW");  
		setLocation(200,150);
		setSize(300,200);
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container c = getContentPane();
	
		
//		setLayout(null);
		setLayout(new FlowLayout());		
// 		Textfeld erzeugen
		add(t1 = new JTextField(""));
		t1.setBounds (140,30, 60, 20);
		add(t2 = new JTextField(""));
		t2.setBounds (140,110, 60, 20);
// 		Label erzeugen
		add(l1 = new JLabel ("PS-Zahl = "));
		l1.setBounds (70,30, 70, 20);
		add(l2 = new JLabel ("KW-Zahl = "));
		l2.setBounds (70,110, 70, 20);
// 		Button erzeugen
		add (b1 = new JButton ("PS -> KW"));
		b1.setBounds (40,70, 90,20);
		add (b2 = new JButton ("KW -> PS"));
		b2.setBounds (140,70, 90,20);
// 		Button mit Listener versorgen
		b1.addActionListener (new Action (1));
		b2.addActionListener (new Action (2));
	}

//		innere Klasse zur Behandlung der Button-Ereignisse 
		class Action implements ActionListener {
			int typ; // 1=PS -> KW,  2=KW -> PS
			Action (int i) {
				typ = i;
			}
// 	Behandlungsmethode fuer Aktionen 1 und 2
		  public void actionPerformed(ActionEvent e){
				String s;
				switch (typ) {
			case 1:
				s = t1.getText(); 
				wertPS = Integer.parseInt(s);
				wertKW = (int) (wertPS * 0.735499+0.49); 
				t2.setText(wertKW +"");	
				break;
			case 2:  
				s = t2.getText(); 
				wertKW = Integer.parseInt(s);
				wertPS = (int)( wertKW * 1.359622+0.1);
				t1.setText(wertPS +"");	
			}
		 }
	}

	public static void main (String args[]) {
		new PSKWmain();
	}
}
