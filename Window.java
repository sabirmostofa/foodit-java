
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.*;
import java.awt.font.TextAttribute;

/**
 * Class that generates the gui window for the game
 * @author cacophonix
 *
 */
public class Window extends JFrame implements ActionListener {
	
	
	public int numBoxes, numColors, gameType, difficulty;
	public SpielBrett single;
	public Spiel2 two;
	public SpielComputer com;
	
   JLabel l1,l2,l3,l4,l5;
	
	
	
	Main choose;
	Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta, Color.cyan};
	Character[] chars = {'A', 'B', 'C', 'D', 'E', 'F'}; 
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
		 
		 
		 // generate colorMap // a hashmap with key value pairs
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
		
		 
		 
    //initializ labels
		 l1 = new JLabel();
		 l2 = new JLabel();
		 l3 = new JLabel();
		 l4 = new JLabel();
		 l5 = new JLabel();
		 
		
	}
	
	public int getMaxSteps(){
		
		switch(numColors){
		case 3:
			switch(numBoxes){
			case 10:
				return 7;
			case 15:
				return 12;
			case 20:
				return 15;
			case 25:
				return 18;
			
			}
			
		case 4:
			switch(numBoxes){
			case 10:
				return 10;
			case 15:
				return 15;
			case 20:
				return 18;
			case 25:
				return 20;
			
			}
		case 5:
			switch(numBoxes){
			case 10:
				return 15;
			case 15:
				return 20;
			case 20:
				return 27;
			case 25:
				return 35;
			
			}
		case 6:
			switch(numBoxes){
			case 10:
				return 18;
			case 15:
				return 25;
			case 20:
				return 35;
			case 25:
				return 45;
			
			}
				
		
		}
		
		return 0;
		
	}
	
	public void addScore(){		
		// scores 
		l1.setText("Score");
		add(l1);
		Font font = new Font(l1.getName(), Font.BOLD, 20);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		l1.setFont(font.deriveFont(attributes));
		
		l1.setBounds(50,300, 130, 25);
		
		
		String s, s2;
		s = "";
		s2 = "";
		
		switch(gameType){
		// single game
		case 1:
			
			s += single.steps + "/" + getMaxSteps();
			l2.setText(s);
			l2.setFont(new Font(l2.getName(), Font.BOLD, 18));
			add(l2);
			l2.setBounds(50,330, 200, 40);
		// 1 vs 1	
		case 2:
			s += "Player 1: " + two.owned.size() ;
			s2 += "Player 2: " + two.owned2.size() ;
			
			l2.setText(s);
			l2.setFont(new Font(l2.getName(), Font.BOLD, 18));
			add(l2);
			l2.setBounds(50,330, 200, 40);
			
			l3.setText(s2);
			l3.setFont(new Font(l3.getName(), Font.BOLD, 18));
			add(l3);
			l3.setBounds(50,355, 200, 40);
			
		case 3:
			
			s += "Player 1: " + com.owned.size() ;
			s2 += "Computer: " + com.owned2.size() ;
			
			l2.setText(s);
			l2.setFont(new Font(l2.getName(), Font.BOLD, 18));
			add(l2);
			l2.setBounds(50,330, 200, 40);
			
			l3.setText(s2);
			l3.setFont(new Font(l3.getName(), Font.BOLD, 18));
			add(l3);
			l3.setBounds(50,355, 200, 40);
			
			
		
		}
		
		
		
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
	            button.setBounds(300+i*80, 10, 80, 60);
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
		
		
		int maxWidth = 600;
		int maxHeight = 600;
		int width= maxWidth / numBoxes;
		int height= width;
		int paddx = 300 ;
		int paddy = 130 ;
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
		
	
		addScore();
		
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
			if(actionVar < 6){
				
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
		Main choose = new Main();
		
		
	    //win.setVisible(false);
	}

}
