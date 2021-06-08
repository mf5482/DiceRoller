//Michael Fishler

//Import libraries
import java.util.Random;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Shape;

//Main class for program
public class DiceRoller extends JFrame{
	
	private JPanel mainPanel; //Defines first panel
	private JButton rollButton; //Button that enables roll feature
	private JButton clearButton; //Button that clears progress
	private JLabel winsLabel; //Label that prints number of wins
	private JLabel rollsLabel; //Labels that prints number of rolls
	private JPanel scorePanel; //Panel that houses scores
	private JPanel picPanel; //Panel that houses the images of dice
	private JPanel buttonPanel; //Panel that houses the buttons
	private JPanel leftDie; //Panel for the user's die
	private JPanel rightDie; //Panel for the computer's die
	int rolls = 0; //Number of rolls performed
	int wins = 0; //Number of wins had
	int userNum; //Value of user's die
	int compNum; //Value's of computer's die
	Random rand = new Random(); //Random object for die generation
	private GridLayout picLayout; //Custom layout for the die pictures
	
	//Program constructor - builds and returns jpanel object
	public DiceRoller() {
		
		setTitle("Dice Frame"); //Set title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //What should the program do when closed
		buildPanel();
		add(mainPanel);
		pack();
		setResizable(false); //The user should not be able to resize window
		setVisible(true); //Allow user to see window
		
		
		
	}
	
//Function that builds main panel
	private void buildPanel() {
		
		//Define aspects of all panels
		mainPanel = new JPanel(new GridLayout(3, 1));
		mainPanel.setPreferredSize(new Dimension(200, 250));
		scorePanel = new JPanel(new GridLayout(1,2));
		picLayout = new GridLayout(1,2);
		picLayout.setHgap(50);
		picPanel = new JPanel(picLayout);
		buttonPanel = new JPanel(new GridLayout(1,2));
		
		//Score Panel
		
		rollsLabel = new JLabel();
		rollsLabel.setText("Rolls: " + rolls);
		winsLabel = new JLabel();
		winsLabel.setText("         Wins: " + wins);
		scorePanel.add(rollsLabel);
		scorePanel.add(winsLabel);
		
		//Pic Panel
		leftDie = new JPanel() {
			
			//Paints circles on screen to look like the face of a die
			//g - library used to help render these circles
			public void paintComponent(Graphics g) {
				
				//Set up graphics
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLUE);
		        g2.fillRect(0, 0, getWidth(), getHeight());
		        g2.setColor(Color.WHITE);
		        
		        //Placement of circles on die
		        int horMiddle = ((getWidth()/2)-15/2);
		        int verMiddle = ((getHeight()/2)-15/2);
		        int left = ((getWidth()/4)-15/2);
		        int right = ((3*getWidth()/4)-15/2);
		        int top = ((getHeight()/4)-15/2);
		        int bottom = ((3*getHeight()/4)-15/2);
		        
		        //Paint the circles on the die according to user value
		        switch(userNum) {
			        case 1:
			        	paintDot(g2, horMiddle, verMiddle);
			        case 3:
			        	paintDot(g2, right, top);
			        	paintDot(g2, left, bottom);
			        case 2:
			        	paintDot(g2, horMiddle, verMiddle);
			        	break;
			        case 5:
			        	paintDot(g2, horMiddle, verMiddle);
			        case 4:
			        	paintDot(g2,left, top);
			        	paintDot(g2, left, bottom);
			        	paintDot(g2, right, top);
			        	paintDot(g2,right, bottom);
			        	break;
			        case 6:
			        	paintDot(g2,left, top);
			        	paintDot(g2, left, bottom);
			        	paintDot(g2, right, top);
			        	paintDot(g2,right, bottom);
			        	paintDot(g2, right, verMiddle);
			        	paintDot(g2,left, verMiddle);
			        	break;
		        	
		        }
		        
		        //Update
		        repaint();
		        
			} 
			
		};
		
		leftDie.setPreferredSize(new Dimension(10, 10));
		
		
		rightDie = new JPanel() {
			
			//Paints circles on screen to look like the face of a die
			//g - library used to help render these circles
			public void paintComponent(Graphics g) {
				
				//Set up graphics
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.RED);
		        g2.fillRect(0, 0, getWidth(), getHeight());
		        g2.setColor(Color.WHITE);
		        
		        //Placement of circles on die
		        int horMiddle = ((getWidth()/2)-15/2);
		        int verMiddle = ((getHeight()/2)-15/2);
		        int left = ((getWidth()/4)-15/2);
		        int right = ((3*getWidth()/4)-15/2);
		        int top = ((getHeight()/4)-15/2);
		        int bottom = ((3*getHeight()/4)-15/2);
		        
		        //Paint the circles on the die according to computer value
		        switch(compNum) {
			        case 1:
			        	paintDot(g2, horMiddle, verMiddle);
			        case 3:
			        	paintDot(g2, right, top);
			        	paintDot(g2, left, bottom);
			        case 2:
			        	paintDot(g2, horMiddle, verMiddle);
			        	break;
			        case 5:
			        	paintDot(g2, horMiddle, verMiddle);
			        case 4:
			        	paintDot(g2,left, top);
			        	paintDot(g2, left, bottom);
			        	paintDot(g2, right, top);
			        	paintDot(g2,right, bottom);
			        	break;
			        case 6:
			        	paintDot(g2,left, top);
			        	paintDot(g2, left, bottom);
			        	paintDot(g2, right, top);
			        	paintDot(g2,right, bottom);
			        	paintDot(g2, right, verMiddle);
			        	paintDot(g2,left, verMiddle);
			        	break;
		        }
		        
		        //Update
		        repaint();
			}
			
		};
		rightDie.setPreferredSize(new Dimension(15,15));
		picPanel.add(leftDie);
		picPanel.add(rightDie);		
		
		
		//Button Panel
		rollButton = new JButton("Roll");
		clearButton = new JButton("Clear");
		buttonPanel.add(rollButton);
		buttonPanel.add(clearButton);
		
		
		mainPanel.add(scorePanel);
		mainPanel.add(picPanel);
		mainPanel.add(buttonPanel);
		
		//Action listener for roll button
		rollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Generate the random numbers
				userNum = generateNum(rand);
				compNum = generateNum(rand);
				
				//If user's number is greater than computer's
				if(userNum > compNum) {
					wins++;
				}
				rolls++;
				
				
				//Update labels
				rollsLabel.setText("Rolls: " + rolls);
				winsLabel.setText("         Wins: " + wins);
				
			}

		});
		
		//Action listener for clear button
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Set all values back to 0
				wins = 0;
				rolls = 0;
				userNum = 0;
				compNum = 0;
				
				//Update labels
				rollsLabel.setText("Rolls: " + rolls);
				winsLabel.setText("Wins: " + wins);
				
			}

		});
		
	}
	
	//Function to paint dot on screen according to position
	public void paintDot(Graphics2D g2, int x, int y) {
		//Center dot
        g2.fillOval(x, y, 15, 15);
	}
	
	//Generates a random die value between 1 and 6
	//Returns that random die value
	public int generateNum(Random rand) {
		
		return (rand.nextInt(6) + 1);
		
	}
	
	//Main function
	public static void main(String[] args) {
		
		//Creates new instance of object
		DiceRoller cw = new DiceRoller();
		
		
		
	}
	
	

}
