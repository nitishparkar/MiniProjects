import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*; 
import javax.imageio.ImageIO;

/*
* Title: Bubble Burster in Java
* Author: Nitish Parkar 09-187 
*/
public class BubbleBurster1 extends JFrame{

	static int width=800;
	static int height=600;
	
	BubbleBurster1(){
		super("Bubble Burster");
		BubbleCanvas bc=new BubbleCanvas();
		add(bc);
		setSize(width,height);
		setVisible(true);
		bc.requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bc.runGame();
	}
	
	public static void main(String[] args) {
		new BubbleBurster1();
	}

}

class BubbleCanvas extends Canvas implements KeyListener{

        private Image dbImage;
	private Graphics dbg;
	int missedBubblesCount=0;
	int score=0;
	static LinkedList<Bubble> bubbles=new LinkedList<Bubble>();
	static boolean gameLoop=true;
	Image img;

	BubbleCanvas() {
        try {
            img = ImageIO.read(new File("underwater.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(BubbleCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
            addKeyListener(this);
            setBackground(Color.WHITE);
            Thread bubbleGenerator=new Thread(new BubbleAdder());
            bubbleGenerator.start();
            //generateBubble();
            //generateBubble();
            //generateBubble();
	}

        void killBubble(char c){
            //System.out.println("kill:"+c);
            Iterator<Bubble> it=bubbles.iterator();
            while(it.hasNext()){
                Bubble b=it.next();
                if(b.letter==c){
                    switch(b.color){
                        case 0:
                            score+=10;
                            break;
                        case 1:
                            score+=20;
                            break;
                        case 2:
                            score-=50;
                            missedBubblesCount++;
                            break;
                    }
                    it.remove();
                }
            }

        }

        void collisionDetection(){
            Iterator<Bubble> it=bubbles.iterator();
            while(it.hasNext()){
                Bubble b=it.next();
                if(b.y<=0){
                    if(b.color<2){
                        missedBubblesCount++;
                    }
                    it.remove();
                    //score-=50;
                    
                }
            }
        }
	
	void runGame(){
		while(gameLoop){
			for(Bubble x:bubbles){
				x.y = x.y - x.speed;
			}
			repaint();
                        //update(this.getGraphics());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                        collisionDetection();
                        if(missedBubblesCount>=8){
                            gameLoop=false;
                            repaint();
                            
                        }
		}
	}
	
	public void paint(Graphics g){
                g.drawImage(img, 0, 0, null);
                if(!gameLoop){
                    g.setColor(Color.WHITE);
                    int boxX=BubbleBurster1.width/3;
                    int boxY=BubbleBurster1.height/3;
                    g.fillRect(boxX, boxY,BubbleBurster1.width/3 , BubbleBurster1.height/3);
                    g.setColor(Color.RED);
                    g.setFont(new Font("Times New Roman",Font.BOLD,20));
                    g.drawString("Game Over!", boxX+30, boxY+30);
                    g.drawString("Final Score:"+score, boxX+30, boxY+70);
                    return;
                }
		for(Bubble x:bubbles){
                        switch(x.color){
                            case 0:
                                g.setColor(Color.BLUE);
                                break;
                            case 1:
                                g.setColor(Color.GREEN);
                                break;
                            case 2:
                                g.setColor(Color.RED);
                                break;
                        }
			g.fillOval(x.x, x.y, x.radius, x.radius);
                        g.setColor(Color.WHITE);
			g.drawString(Character.toString(x.letter), x.x+(x.radius/2), x.y+(x.radius/2));			
		}
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times New Roman",Font.BOLD,30));
		g.drawString("Score:"+score, BubbleBurster1.width-130, 50 );
		g.drawString("Faults:"+missedBubblesCount, BubbleBurster1.width-130, 100 );
	}

        public void update(Graphics g){
            if (dbImage == null)
            {
			dbImage = createImage (BubbleBurster1.width, BubbleBurster1.height);
			dbg = dbImage.getGraphics ();
            }
            paint(dbg);
            g.drawImage(dbImage, 0, 0, this);
        }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		char chr=Character.toUpperCase(arg0.getKeyChar());
                killBubble(chr);
        }
	
	static void generateBubble(){
            if(bubbles.size()>8){
                return;
            }
            boolean generated=false;
            while(!generated){
		Bubble b = new Bubble();
                boolean exists = false;

                for(Bubble x:bubbles){
                    if(b.letter==x.letter){
                        exists=true;
                    }
                }
                if(!exists){
                    bubbles.add(b);
                    generated=true;
                }
            }
	}
}

class BubbleAdder implements Runnable{

    public void run() {
        while(BubbleCanvas.gameLoop){
            BubbleCanvas.generateBubble();
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException ex) {
                Logger.getLogger(BubbleAdder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class Bubble{
	public int x,y,speed,color,radius;
	char letter;
	Random r;
	Bubble(){
		r=new Random();
		radius=75;
		x=r.nextInt(BubbleBurster1.width-400);
                x+=100;
		y=BubbleBurster1.height;
		//y+=radius+10;
		speed = r.nextInt(10);
		color=r.nextInt(3);
		letter= (char)(r.nextInt(26)+65);
	}
}
