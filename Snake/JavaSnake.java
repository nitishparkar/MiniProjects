import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

// @author: Nitish Parkar

public class JavaSnake extends Canvas implements KeyListener
{
	
	
	private int speed=100;  //delay in ms
	private int snakeLength=2;
	private int snakex[]=new int[100];
	private int snakey[]=new int[100];
	private int direction=39; //39:right, 38:up, 40:down, 37:left
	private boolean directionInUse=false;
	private int score=10;
	private int snakeAreaX=10;
	private int snakeAreaY=10;
	private int snakeAreaWidth=600;
	private int snakeAreaHeight=400;
	private int baitX=0;
	private int baitY=0;
	private int result=0; //0-lost 1-won
	
	
	
    public static void main(String[] args) {
		// Create a CanvasTry class instance (which is a type of canvas)
		JavaSnake b = new JavaSnake();
		
		//System.out.print("hello");
		// Create a JFrame to put our canvas on
		JFrame f = new JFrame();
		f.setTitle("Snake in Java");
		f.setSize(b.snakeAreaWidth+40,b.snakeAreaHeight+60);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addKeyListener(b);
		
		// Add the canvas and show the JFrame
		f.add(b);
		 
		f.setVisible(true);
		b.requestFocus();
		
		b.generateBait();

		b.move();
    }

    public JavaSnake() {
    	
    	snakex[0]=snakey[0]=snakey[1]=110;
    	snakex[1]=100;
    	
    	direction=39;
    	
    }
    
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
      
    }
    
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
    		
    		
    		int sum=direction+e.getKeyCode();
    		if(sum!=76&&sum!=78&&directionInUse==false)
    		{
    			direction=e.getKeyCode();
    			directionInUse=true;
    			//System.out.print(e.getKeyCode());
    		}
    }
    
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
    }
    
	
    
    public void paint(Graphics g) {
		// Set color first before we draw
		g.setColor(Color.red);
		g.drawRect(snakeAreaX, snakeAreaY, snakeAreaWidth, snakeAreaHeight); //draw boundary
		
		g.setColor(Color.blue);
		
		for(int i=0;i<snakeLength;i++)
		{
			g.fillRect(snakex[i],snakey[i] , 10, 10);
			//g.fillOval(snakex[i],snakey[i] , 10, 10);
		}
		//System.out.println(snakex[0]+":"+snakey[0]);
		//System.out.println("_____");
		g.setColor(Color.BLACK);
		g.fillOval(baitX,baitY , 10, 10);
		
		
    }
    
    private void move()
    {
    	while(true)
    	{
    		if(snakeLength>=95)
    		{
    			result=1;
    			break;
    		}
    		try{
    		Thread.sleep(speed);
    		}
    		catch(Exception e)
    		{
    			javax.swing.JOptionPane.showMessageDialog(getParent(), "Unexpected Error!!\nPlease Try Again Later");
    			System.exit(0);
    		}
    		
    		if(collisionDetection())
    			break;
    		
    		score++;
    		if(baitCaptureDetection())
    		{
    			snakeLength++;
    			score+=20;
    			baitX=baitY=0;
    			generateBait();
    		}
    		for(int j=snakeLength-2;j>=0;j--)
    		{
    
    			snakex[j+1]=snakex[j];
    			snakey[j+1]=snakey[j];
    		}
    		
    		if(direction==39)
    		{
    			right();
    		}
    		if(direction==38)
    		{
    			up();
    		}
    		if(direction==40)
    		{
    			down();
    		}
    		if(direction==37)
    		{
    			left();
    		}
    		directionInUse=false;
    		
    		
    	
    		repaint();
    	}
    	if(result==1)
    		javax.swing.JOptionPane.showMessageDialog(getParent(), "Congratulation!!\nNothing Left to conquer!!\nThanks for playing.\nScore:"+score);
    	else
    		javax.swing.JOptionPane.showMessageDialog(getParent(), "Game Over!!\nScore:"+score);
    	//System.out.println(snakeAreaX+snakeAreaWidth);
    }
    
    private void up()
    {
    	
    		snakey[0]-=10;
    	
    }
    
    private void down()
    {
    	
    		snakey[0]+=10;
    	
    	
    }
    
    private void left()
    {
    	
    		snakex[0]-=10;
    
    }
    
    private void right()
    {
    	
    		snakex[0]+=10;
    		//System.out.println("hh"+snakex[0]);
    }
    
    private boolean baitCaptureDetection()
    {
    	boolean res=false;
    	if(snakex[0]==baitX&&snakey[0]==baitY)
    		res=true;
    	//if(res)
    		//System.out.print("hhh");
    	return res;
    }
    
    private boolean collisionDetection()
    {
    	boolean res=false;
    	//if(snakex[0]>=(snakeAreaX+snakeAreaWidth)||snakex[0]<=snakeAreaX||snakey[0]>=(snakeAreaY+snakeAreaHeight)||snakey[0]<=snakeAreaY)
    	//	res=true;
    	if(snakex[0]>=(snakeAreaX+snakeAreaWidth))
    	{
    		snakex[0]=snakeAreaX;
    		return res;
    	}	
    			
    	if(snakex[0]<snakeAreaX)
    	{
    		snakex[0]=snakeAreaX+snakeAreaWidth-10;
    		return res;
    	}
    	
       	if(snakey[0]>=(snakeAreaY+snakeAreaHeight))
       	{
       		snakey[0]=snakeAreaY;
       		return res;
       	}		
    	if(snakey[0]<snakeAreaY)
    	{
    		snakey[0]=snakeAreaY+snakeAreaHeight-10;	
    		return res;
    	}
    		
    	for(int i=1;i<snakeLength;i++)
    	{
    		if((snakex[0]==snakex[i])&&(snakey[0]==snakey[i]))
    			res=true;
    	}
    	
    	return res;
    }
    
    private void generateBait()
    {
    	java.util.Random r=new java.util.Random();
    	int x=0,y=0;
    	
    	while(x==0)
    	{
    		x=r.nextInt(snakeAreaX+snakeAreaWidth-30);
    		
    		if(x<=snakeAreaX+30)
    			x=0;
    		
    		for(int i=0;i<snakeLength;i++)
        	{
        		if(x==snakex[i])
        			x=0;
        	}
    		
    		
    	}
    	//System.out.print("\njj"+x);
    	while(y==0)
    	{
    		y=r.nextInt(snakeAreaY+snakeAreaHeight-30);
    		if(y<=snakeAreaY+30)
    			y=0;
    		
    		for(int i=0;i<snakeLength;i++)
        	{
        		if(y==snakey[i])
        			y=0;
        	}
    	}
    	int tmp;
    	
    	tmp=x%10;
    	tmp=10-tmp;
    	baitX=x+tmp;
    	tmp=y%10;
    	tmp=10-tmp;
    	baitY=y+tmp;
    	
    }
    

}


