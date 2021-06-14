import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private int[] SnakeXLength = new int[750];
	private int[] SnakeYLength = new int[750];	
	private int moves = 0;
	private int score = 0;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon snakeimage;
	
	private int lengthofSnake = 3;
	//Enemy
	private int [] EnemyXPosition = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] EnemyYPosition = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private ImageIcon enemyimage;
	
	private Random random = new Random();
	
	private int XPos = random.nextInt(34);
	private int YPos = random.nextInt(23);	
	
	private ImageIcon titleImage;
	
	public GamePlay(){
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g){
		
		if(moves == 0){
			
		SnakeXLength[2] = 50;
		SnakeXLength[1] = 75;
		SnakeXLength[0] = 100;
			
		SnakeYLength[2] = 100;
		SnakeYLength[1] = 100;
		SnakeYLength[0] = 100;
		
		}
	
		//draw title image border
		g.setColor(Color.white);;
		g.drawRect(24, 10, 851, 55);
		
		//draw title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: "+score, 780, 30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+lengthofSnake, 780, 50);
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, SnakeXLength[0], SnakeYLength[0]);
		
		for (int a=0; a< lengthofSnake; a++){
			
			if(a==0 && right){
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if(a==0 && left){
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if(a==0 && up){
				upmouth = new ImageIcon("umouth.png");
				upmouth.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if(a==0 && down){
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}
			if (a!=0){
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, SnakeXLength[a], SnakeYLength[a]);
			}	
		}
		
		enemyimage = new ImageIcon("enemy.png");
		if(EnemyXPosition[XPos] == SnakeXLength[0] && EnemyYPosition[YPos] == SnakeYLength[0]){
			score++;
			lengthofSnake++;
			XPos = random.nextInt(34);
			YPos = random.nextInt(23);
		}
		enemyimage.paintIcon(this, g, EnemyXPosition[XPos], EnemyYPosition[YPos]);
		
		for(int b=1; b<lengthofSnake; b++){
			if(SnakeXLength[b] == SnakeXLength[0] && SnakeYLength[b] == SnakeYLength[0]){
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER!", 300, 300);
			
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press SPACE to RESTART!", 350, 340);
			}
			
		}
		
		g.dispose();
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(right){
			for(int r=lengthofSnake-1; r>=0; r--){
				SnakeYLength[r+1] = SnakeYLength[r];
			}
			for(int r=lengthofSnake; r>=0; r--){
				if(r==0){
					SnakeXLength[r] = SnakeXLength[r] + 25;
				}
				else{
					SnakeXLength[r] = SnakeXLength[r-1];
				}
				if(SnakeXLength[r] > 850){
					SnakeXLength[r] = 25;
				}
			}
			
			repaint();
		}
		
		if(left){
			for(int r=lengthofSnake-1; r>=0; r--){
				SnakeYLength[r+1] = SnakeYLength[r];
			}
			for(int r=lengthofSnake; r>=0; r--){
				if(r==0){
					SnakeXLength[r] = SnakeXLength[r] - 25;
				}
				else{
					SnakeXLength[r] = SnakeXLength[r-1];
				}
				if(SnakeXLength[r] < 25){
					SnakeXLength[r] = 850;
				}
			}
			
			repaint();
		}
		if(up){
			for(int r=lengthofSnake-1; r>=0; r--){
				SnakeXLength[r+1] = SnakeXLength[r];
			}
			for(int r=lengthofSnake; r>=0; r--){
				if(r==0){
					SnakeYLength[r] = SnakeYLength[r] - 25;
				}
				else{
					SnakeYLength[r] = SnakeYLength[r-1];
				}
				if(SnakeYLength[r] < 75){
					SnakeYLength[r] = 625;
				}
			}
			
			repaint();
		}
		if(down){
			for(int r=lengthofSnake-1; r>=0; r--){
				SnakeXLength[r+1] = SnakeXLength[r];
			}
			for(int r=lengthofSnake; r>=0; r--){
				if(r==0){
					SnakeYLength[r] = SnakeYLength[r] + 25;
				}
				else{
					SnakeYLength[r] = SnakeYLength[r-1];
				}
				if(SnakeYLength[r] > 625){
					SnakeYLength[r] = 75;
				}
			}
			
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			moves = 0;
			score = 0;
			lengthofSnake = 3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			right = true;
			if(!left){
				right = true;
			}
			else{
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			moves++;
			left = true;
			if(!right){
				left = true;
			}
			else{
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			moves++;
			up = true;
			if(!down){
				up = true;
			}
			else{
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			moves++;
			down = true;
			if(!up){
				down = true;
			}
			else{
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
		
	}