
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Super-awesome Flood-It:
 * 
 * 4 sizes: 		10x10; 15x15; 24x24; 30x30.
 * 6 colors:		blue, green, magenta, orange, red, cyan.
 * Turn-Counter:	Counts every time a color-button is pressed.
 * 					(needs improvements)
 * 
 * -note:			single and computer buttons not yet implemented.
 * 
 * 
 * @author carlos
 * @version 1.2
 *
 */
class SpielfeldB extends JFrame {
	
	// Instanzvariablen:
	public int[][] matrix;
	public int[][] paintMatrix;
	public int[][] copyMatrix;
	public JButton butt1, butt2, butt3, butt4;
	public JButton bCol1, bCol2, bCol3, bCol4, bCol5, bCol6;
	public JButton bSingle, bComputer;
	public int size = 0;
	public Random rand = new Random();
	public int colNumber = 0;
	public boolean random = false;
	public boolean ingame = false;
	public int dim = 0;	// Dimension
	public int prevColor = 0;
	public int count = 0;
	public int turns = 0;

	
	// Kontruktor:
	public SpielfeldB() {
		setTitle("Random Spielfeld");
		setBounds(0,0,1000,800);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(butt1 = new JButton("S"));
		butt1.setBounds(50,180,60,60);
		butt1.addActionListener(new Action(1));
		add(butt2 = new JButton("M"));
		butt2.setBounds(110,180,60,60);
		butt2.addActionListener(new Action(2));
		add(butt3 = new JButton("B"));
		butt3.setBounds(50,240,60,60);
		butt3.addActionListener(new Action(3));
		add(butt4 = new JButton("XL"));
		butt4.setBounds(110,240,60,60);
		butt4.addActionListener(new Action(4));
		
		
		add(bCol1 = new JButton(""));
		bCol1.setBounds(50,360, 60, 60);
		bCol1.setBackground(Color.blue);
		bCol1.addActionListener(new Action(5));
		add(bCol2 = new JButton(""));
		bCol2.setBounds(50,420, 60, 60);
		bCol2.setBackground(Color.green);
		bCol2.addActionListener(new Action(6));
		add(bCol3 = new JButton(""));
		bCol3.setBounds(50,480, 60, 60);
		bCol3.setBackground(Color.magenta);
		bCol3.addActionListener(new Action(7));
		add(bCol4 = new JButton(""));
		bCol4.setBounds(110,360, 60, 60);
		bCol4.setBackground(Color.orange);
		bCol4.addActionListener(new Action(8));
		add(bCol5 = new JButton(""));
		bCol5.setBounds(110,420, 60, 60);
		bCol5.setBackground(Color.red);
		bCol5.addActionListener(new Action(9));
		add(bCol6 = new JButton(""));
		bCol6.setBounds(110,480, 60, 60);
		bCol6.setBackground(Color.cyan);
		bCol6.addActionListener(new Action(10));
		
		add(bSingle = new JButton("Single"));
		bSingle.setBounds(50,60,120,60);
		bSingle.addActionListener(new Action(11));
		add(bComputer = new JButton("Computer"));
		bComputer.setBounds(50,120,120,60);
		bComputer.addActionListener(new Action(12));
		
	}
	
	// Paint:
	public void paint(Graphics g) {
		super.paintComponents(g);
		paintMatrix = new int[size][size];
		
		// "paintMatrix" gets Values.
		paintMatrix = copyMatrix;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				
				g.fillRect(300 + j * dim, dim + i * dim, dim, dim);
				g.setColor(Color.black);
				g.drawRect(300 + j * dim, dim + i * dim, dim, dim);
				if (random == true) {
					if (i == 0 && j == 0) {
						paintMatrix[0][0] += 10;	// !!!!!!!!!!!!! Fields the belong to me are > 10.
					}
					// If RIGHT is same color as 0|0, then add 10 to RIGHT.
					if (j > 0 && paintMatrix[i][j] == paintMatrix[i][j-1] - 10) {
						paintMatrix[i][j] = paintMatrix[0][0];
						// If UP is same color as 0|0, then add 10 to UP.
						if (i > 0 && paintMatrix[i][j] == paintMatrix[i-1][j] + 10) {
							paintMatrix[i-1][j] = paintMatrix[0][0];
						}
						// If DOWN is same color as 0|0, then add 10 to DOWN.
						if (i < size - 1 && paintMatrix[i][j] == paintMatrix[i+1][j] + 10) {
							paintMatrix[i+1][j] = paintMatrix[0][0];
						}
					}
					// If DOWN is same color as 0|0, then add 10 to DOWN.
					if (i > 0 && paintMatrix[i][j] == paintMatrix[i-1][j] - 10) {
						paintMatrix[i][j] = paintMatrix[0][0];
						// If LEFT is same color as 0|0, then add 10 to LEFT.
						if (j > 0 && paintMatrix[i][j] == paintMatrix[i][-j] + 10) {
							paintMatrix[i][j-1] = paintMatrix[0][0];
						}
						// If RIGHT is same color as 0|0, then add 10 to RIGHT.
						if (j < size - 1 && paintMatrix[i][j] == paintMatrix[i][+j] + 10) {
							paintMatrix[i][j+1] = paintMatrix[0][0];
						}
					}		
				}	
			}
		}
		
		if (ingame == true) {
			// Turn-Counter!
			turns = count;
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
				
					// Check only Fields with value > 10.
					if (paintMatrix[i][j] > 10) {
							
						// If RIGHT is "colNumber", then change right to color "colX" and value to "10+colNumber".
						if (j < size - 1 && paintMatrix[i][j+1] == colNumber) {
							paintMatrix[i][j+1] = 10 + colNumber;
							prevColor = paintMatrix[i][j];
							// If UP or DOWN is "colNumber"...
							if (i > 0 && paintMatrix[i-1][j] == colNumber) {
								paintMatrix[i-1][j] = 10 + colNumber;
							}
							if (i < size - 1 && paintMatrix[i+1][j] == colNumber) {
								paintMatrix[i+1][j] = 10 + colNumber;
							}
							// Change all previous Color to new Color.
							for(i = 0; i < size; i++) {
								for(j = 0; j < size; j++) {
									if (paintMatrix[i][j] == prevColor) {
										paintMatrix[i][j] = 10 + colNumber;
										repaint();
									}
								}
							}
						}
								
						// if DOWN is "colNumber", then change down to color "colX" and value to "10+colNumber".
						if (i < size - 1 && paintMatrix[i+1][j] == colNumber) {
							paintMatrix[i+1][j] = 10 + colNumber;
							prevColor = paintMatrix[i][j];
							// If LEFT or RIGHT is "colNumber"...
							if (j > 0 && paintMatrix[i][j-1] == colNumber) {
								paintMatrix[i][j-1] = 10 + colNumber;
							}
							if (j < size - 1 && paintMatrix[i][j+1] == colNumber) {
								paintMatrix[i][j+1] = 10 + colNumber;
							}
							// Change all prev Color to new Color.
							for(i = 0; i < size; i++) {
								for(j = 0; j < size; j++) {
									if (paintMatrix[i][j] == prevColor) {
										paintMatrix[i][j] = 10 + colNumber;
										repaint();
									}
								}
							}
						}
						
						// If LEFT 	is "colNumber", then change down to color "colX" and value to "10+colNumber".
						if (j > 0 && paintMatrix[i][j-1] == colNumber) {
							paintMatrix[i][j-1] = 10 + colNumber;
							prevColor = paintMatrix[i][j];
							// If UP or DOWN is "colNumber"...
							if (i > 0 && paintMatrix[i-1][j] == colNumber) {
								paintMatrix[i-1][j] = 10 + colNumber;
							}
							if (i < size - 1 && paintMatrix[i+1][j] == colNumber) {
								paintMatrix[i+1][j] = 10 + colNumber;
							}
							// Change all prev Color to new Color.
							for(i = 0; i < size; i++) {
								for(j = 0; j < size; j++) {
									if (paintMatrix[i][j] == prevColor) {
										paintMatrix[i][j] = 10 + colNumber;
										repaint();
									}
								}
							}
						}
								
						//	If UP is "colNumber", then change down to color "colX" and value to "10+colNumber".
						if (i > 0 && paintMatrix[i-1][j] == colNumber) {
							paintMatrix[i-1][j] = 10 + colNumber;
							prevColor = paintMatrix[i][j];
							// If LEFT or RIGHT is "colNumber"...
							if (j > 0 && paintMatrix[i][j-1] == colNumber) {
								paintMatrix[i][j-1] = 10 + colNumber;
							}
							if (j < size - 1 && paintMatrix[i][j+1] == colNumber) {
								paintMatrix[i][j+1] = 10 + colNumber;
							}
							// Change all prev Color to new Color.
							for(i = 0; i < size; i++) {
								for(j = 0; j < size; j++) {
									if (paintMatrix[i][j] == prevColor) {
										paintMatrix[i][j] = 10 + colNumber;
										repaint();
									}
								}
							}
						}		
					}
				}
			}
			// Needs Improvements: Count turns only if paintMatrix has changed.
			turnCounter(turns);
			g.setColor(Color.white);
			g.fillRect(50, 570, 120, 80);
			g.setColor(Color.black);
			g.drawRect(50, 570, 120, 80);
			g.setFont(new Font("Serif",Font.BOLD, 20));
			g.drawString("Turns: ", 60, 600);
			g.drawString("" + turns, 60, 630);
		}
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				switch(paintMatrix[i][j]) {
				case 1:
				case 11:
					g.setColor(Color.blue);
					break;
				case 2:
				case 12:
					g.setColor(Color.green);
					break;
				case 3:
				case 13:
					g.setColor(Color.magenta);
					break;
				case 4:
				case 14:
					g.setColor(Color.orange);
					break;
				case 5:
				case 15:
					g.setColor(Color.red);
					break;
				case 6:
				case 16:
					g.setColor(Color.cyan);
					break;
				}
				
				g.fillRect(300 + j * dim, dim + i * dim, dim, dim);
				g.setColor(Color.black);
				g.drawRect(300 + j * dim, dim + i * dim, dim, dim);
				
				// Optional: Checks value of all fields.
			//	g.setColor(Color.black);
			//	g.setFont(new Font("Serif",Font.BOLD, 10));
			//	g.drawString("" + paintMatrix[i][j], 300 + j * dim, dim + i * dim);
			}
		}
		
		saveMatrix(paintMatrix);
	}
		
		
	
	// Method_1:
	public int[][] randomMatrix() {
		matrix = new int[size][size];
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					matrix[i][j] = rand.nextInt(6) + 1;
				}
			}
		saveMatrix(matrix);
		return matrix;
	}
	
	// Method_2:
	public int[][] saveMatrix(int[][] m) {
		int[][] sMatrix = m;
		return sMatrix;
	}
	
	// Method 3:
	public int turnCounter(int count) {
		int counter = count;
		counter++;
		return counter;
	}
	
	
	//  Button-Listeners:
	class Action implements ActionListener {
		int typ;
		Action(int i) {
			typ = i;
		}
		
		public void actionPerformed(ActionEvent evt) {
			switch(typ) {
			case 1:
				size = 10;
				dim = 60;
				random = true;
				copyMatrix = randomMatrix();
				repaint();
				break;
			case 2:
				size = 15;
				dim = 40;
				random = true;
				copyMatrix = randomMatrix();
				repaint();
				break;
			case 3:
				size = 24;
				dim = 25;
				random = true;
				copyMatrix = randomMatrix();
				repaint();
				break;
			case 4:
				size = 30;
				dim = 20;
				random = true;
				copyMatrix = randomMatrix();
				repaint();
				break;
			case 5:
				colNumber = 1;	// blue
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			case 6:
				colNumber = 2;	// green
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			case 7:
				colNumber = 3;	// magenta
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			case 8:
				colNumber = 4;	// orange
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			case 9:
				colNumber = 5;	// red
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			case 10:
				colNumber = 6;	// cyan
				ingame = true;
				random = false;
				copyMatrix = saveMatrix(paintMatrix);
				count = turnCounter(turns);
				repaint();
				break;
			}
			
		}
	}
	
}

// Main:
public class Spielfeld2 {
	public static void main(String[] args) {
		SpielfeldB Spiel1 = new SpielfeldB();
		Spiel1.setVisible(true);
		
	}
}
