import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 

import java.util.Random;


public class WindowGame extends JFrame implements ActionListener {
	
	public static int n = 0;
	public static int colors = 0;
	public static int gametype = 0;
	public static int difficulty = 0;
	public static Container con;
	public static JButton cButton1,cButton2, cButton3;
	public static JLabel j1;
	
	
	
	public WindowGame(){
		 setTitle("Choose a Game");
		 setLocation(100,200);
		 setSize(1000,800);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(null);
		 setVisible(true);
		 
		 Container con = getContentPane();
		 
           //	  Label erzeugen
		 
		 j1= new JLabel ("How many Colors?");
		 add(j1);	 
		 j1.setBounds(200,70, 200, 20);
		//setbounds(x,y, width, height)
		 
		 String[] clabels = { "3", "4" , "5" };
		 
		 JButton button;
		 int i = 0;
		 for (String label : clabels) {
			   
	            button = new JButton(label);
	            button.addActionListener(this);
	            add(button);  //same as self.add(button)
	            button.setBounds(200+i*80, 100, 60, 30);
	            i += 1;
	        }
		 
		 //How Big?
		 
		 j1= new JLabel ("Size of the Board");
		 add(j1);	 
		 j1.setBounds(200, 150, 200, 20);
		 
		 String[] slabels = { "10x10", "15x15" , "20x20", "25x25"};
		 
		 i = 0;
		 for (String label : slabels) {
			   
	            button = new JButton(label);
	            button.addActionListener(this);
	            add(button);  //same as self.add(button)
	            button.setBounds(200+i*100, 170, 100, 30);
	            i += 1;
	        }
		 
		 
		 //Gametype
		 

		 j1= new JLabel ("Type of the Game");
		 add(j1);	 
		 j1.setBounds(200, 220, 200, 20);
		 
		 String[] tlabels = { "Single", "1 vs 1" , "vs Computer"};
		 
		 i = 0;
		 for (String label : tlabels) {
			   
	            button = new JButton(label);
	            button.addActionListener(this);
	            add(button);  //same as self.add(button)
	            button.setBounds(200+i*100, 250, 100, 30);
	            i += 1;
	        }
		 
		 // Difficulty
		 
		 j1= new JLabel ("Difficulty");
		 add(j1);	 
		 j1.setBounds(200, 290, 200, 20);
		 j1.setVisible(false);
		 
		
		 
		 

			   
	            cButton1 = new JButton("Easy");
	            cButton1. setVisible(false);
	            cButton1.addActionListener(this);
	            add(cButton1);  //same as self.add(button)
	            cButton1.setBounds(200, 320, 100, 30);
	            
	            cButton2 = new JButton("Medium");
	            cButton2.setVisible(false);
	            cButton2.addActionListener(this);
	            add(cButton2);  //same as self.add(button)
	            cButton2.setBounds(300, 320, 100, 30);
	            
	            cButton3 = new JButton("Hard");
	            cButton3.setVisible(false);
	            cButton3.addActionListener(this);
	            add(cButton3);  //same as self.add(button)
	            cButton3.setBounds(400, 320, 100, 30);
	  

		// start button	
			JButton b1;
			b1 = new JButton ("Start");
			
			b1.addActionListener(this);
			add (b1);
			b1.setBounds(200,700, 100, 20);
			
			
			
			
		 
		 Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta};
		

		
	}
	
	public static void hideComButtons(){
		 j1.setVisible(false);
		 cButton1.setVisible(false);
		 cButton2.setVisible(false);
		 cButton3.setVisible(false);
	}
	
	public static void showComButtons(){
		
		 j1.setVisible(true);
		 cButton1.setVisible(true);
		 cButton2.setVisible(true);
		 cButton3.setVisible(true);
		
	} 
	
	//Action listener
	
	public void actionPerformed(ActionEvent e){
		
		// show that not selected
		if( n == 0 || colors == 0 || gametype == 0)
			;
		
		// set the variables n, colors, gametype
		
		 JButton button = (JButton) e.getSource();	
	
		 
	    String s = button.getText();
	    
	    System.out.println(s);
	    System.out.println(colors);
	    System.out.println(gametype);
	    //perfom action based on the buttons clicked
	    switch(s){
	    	case "Start":
	    		this.setVisible(false);
	    		Window win = new Window();
	    		break;
	    	case "3":
	    		colors = 3;
	    		break;
	    	case "4":
	    		colors = 4;
	    		break;
	    		
	    	case "5":
	    		colors = 5;
	    		break;
	    		
	    	case "10x10":
	    		 n= 10;
	    		 break;
	    	     
	    	case "15x15":
	    		n = 15;
	    		break;    
	    	     
	    	case "20x20":
	    		 n = 20;
	    		 break;
	    	     
	    	case "25x25":
	 	        n = 25;
	 	       break;
		    	     
	    	case "Single":
	    		gametype = 1;
	    		hideComButtons();
	    		break;   
	    	     
	    	case "vs Computer":
	    		gametype = 3;
	    		showComButtons();
	    		break;
	    	     
	    	case "1 vs 1":
	    		gametype = 2;
	    		hideComButtons();
	    		break;
	    		
	    	case "Easy":
	    		difficulty = 1;
	    		break;
	    		
	    	case "Medium":
	    		difficulty = 2;
	    		break;
	    		
	    	case "Hard":
	    		difficulty = 3;
	    		break;
		    	     
		    	     
	    	 
	    	
	    }
		
	}
	
	
	
	
	

}
