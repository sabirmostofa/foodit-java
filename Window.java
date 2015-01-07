
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.*;

public class Window extends JFrame implements ActionListener {
	
	
	public int numBoxes, numColors, gameType, difficulty;
	public SpielBrett single;
	public Spiel2 two;
	public SpielComputer com;
	
	
	
	WindowGame choose;
	Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta};
	Character[] chars = {'A', 'B', 'C', 'D', 'E'}; 
	HashMap<Character, Color> colorMap= new HashMap<Character, Color>();
	
	/**
	 * Constructor
	 */
	public Window(){
		
		 setTitle("Game on!");
		 setLocation(100,200);
		 setSize(1000,800);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(null);
		 setVisible(true);
		 
		 
		 

		 
		 
		 numBoxes = choose.n ;
		 numColors = choose.colors;
		 gameType = choose.gametype;
		 difficulty = choose.difficulty;
		 
		 colors = Arrays.copyOfRange(colors, 0, numColors);
		 
		 
		 // generate colorMap // a hashmap with key valu pairs
		 for(int i=0; i < colors.length ; i++){
			 colorMap.put(chars[i],colors[i]);
		 }
		 
		 switch(gameType){
		 
		 case 1:
			single = new SpielBrett(numBoxes, numColors);
			System.out.println("SpielBrett initiated");
			
			break;
			 
		 case 2:
			 two = new Spiel2(numBoxes, numColors);
	
			 break;
			 
		 case 3:
			 com = new SpielComputer(numBoxes, numColors);
			 break;
		 
		 
		 }
		
		 
		 
		 //Adding buttons for color;
		
		 /*
		 int i = 0;
		 for(Color color: colors){			 
			    JButton button = new JButton();
			    button.setBackground(color);
	            button.addActionListener(this);
	            add(button);  //same as self.add(button)
	            button.setBounds(100+i*100, 80, 100, 40);
			    i+=1;
		 }
		 
		 */

		 
		 Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta};
		
   // repaint();
		
	}
	
	

	
	public void paint(Graphics g){
		System.out.println("in paint");
		super.paintComponents(g);
		
		if(single == null && com == null && two == null )
			return;
		
		
		 int i = 0;
		 for(Color color: colors){			 
			    JButton button = new JButton();
			    button.setBackground(color);
	            button.addActionListener(new Action(i));
	            add(button);  //same as self.add(button)
	            button.setBounds(20+i*80, 10, 80, 60);
			    i+=1;
		 }
		 
		 
		 char[][] board = new char[numBoxes][numBoxes];
		 
		 
		 switch(gameType){		 
		 case 1:
			 
			 Test.arCopy(board, single.brett);
			
			break;
			 
		 case 2:
			 
			 Test.arCopy(board, two.brett);
	
			 break;
			 
		 case 3:
			 Test.arCopy(board, com.brett);
			 break;
		 
		 }
		
		
		int maxWidth = 625;
		int maxHeight = 625;
		int width= maxWidth / numBoxes;
		int height= width;
		int paddx = 300 ;
		int paddy = 90 ;
		for(int x=0;x<numBoxes;x++)
		{
		    for(int y=0;y<numBoxes;y++)
		    {
		    	Character a = board[x][y];

		    	g.setColor( colors[Arrays.asList(chars).indexOf(a)] );
		        g.fillRect(x*width + paddx ,y*height+ paddy,width,height);
		    	g.setColor(Color.black);
		    	g.drawRect(x*width + paddx ,y*height+ paddy,width,height);
		      
		    }
		}
		
		
	}
	
	
	// all button click  listener
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
	
	// Action Listener
	public class Action implements ActionListener{
		public int actionVar;
		Action(int i){
			actionVar = i;
			
		}
		
		public void actionPerformed(ActionEvent e){
			
			//pressed on any colors
			if(actionVar < 5){
				
				Character selected = chars[actionVar];
				
				 switch(gameType){
				 
				 case 1:
					single.feldErneuen(selected);
					 
					
					break;
					 
				 case 2:
					 
			        if(two.isValid(selected)){
			        	two.startGame2(selected);
			        }
			        //if not valid
			        else{
			        	
			        	
			        }
					 break;
					 
				 case 3:
					 com.startGame(selected);
					 
					 break;
				 
				 }//endswitch
				
				
			}//endif for colors
			
			//action listener for other buttons
			
			switch(actionVar){
			case 0:
				break;

			
			}
			repaint();
		}
	}
	
	public static void main(String args[]){
		WindowGame choose = new WindowGame();
		
		
	    //win.setVisible(false);
	}

}
