
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Random;

public class Window extends JFrame {
	
	Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta};
	
	/**
	 * Constructor
	 */
	public Window(){
		 setTitle("Farbwechsel");
		 setLocation(200,300);
		 setSize(1000,800);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(null);
		 setVisible(true);
		 
		 Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.magenta};
		

		
	}
	
	public void paint(Graphics g){
		
		int width=25;
		int height=25;
		int paddx = 100 ;
		int paddy = 100 ;
		for(int x=0;x<25;x++)
		{
		    for(int y=0;y<25;y++)
		    {
		    	int idx = new Random().nextInt(this.colors.length);

		    	g.setColor(colors[idx]);
		        g.fillRect(x*width + paddx ,y*height+ paddy,width,height);
		    	g.setColor(Color.black);
		    	g.drawRect(x*width + paddx ,y*height+ paddy,width,height);
		      
		    }
		}
	}
	
	
	public static void main(String args[]){
		new Window();
	}

}
