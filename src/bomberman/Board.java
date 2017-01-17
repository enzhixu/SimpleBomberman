package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Date;
import javax.swing.*;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private bomberman.Myself myself;
    private bomberman.Block block;
    private bomberman.Obstacle obstacle;
    private ArrayList<bomberman.Alien> aliens;
    private int ingame;
    private int timeCnt=0;
    private int unitSize = bomberman.PanelSwap.unitSize;

 // Screen size
    static Dimension screenSize = bomberman.PanelSwap.screenSize;
    static final int B_WIDTH = bomberman.PanelSwap.B_WIDTH;
    static final int B_HEIGHT = PanelSwap.B_HEIGHT;
    static final int B_STEP = PanelSwap.B_STEP;
    //private final int ICRAFT_X = B_WIDTH/2;
    //private final int ICRAFT_Y = B_HEIGHT/2;
    private final int DELAY = 100;
    private final int ALIEN_NUM = PanelSwap.ALIEN_NUM;
    private boolean INVINCIBLE = true;
    public static int INVINCIBLE_TIME;
    long start;
    JButton swap2;
    
    static int[] initPos(){
    	String where;
    	double toss = Math.random();
    	int semiPerimeter = B_WIDTH+B_HEIGHT;
    	int[] res = new int[2];
    	Random random = new Random();
    	if(toss<(double)B_WIDTH/semiPerimeter){
    		res[0] = (int)(Math.random()*B_WIDTH);
    		res[1] = random.nextInt(2)*B_HEIGHT;	
    	}else{
    		res[0] = random.nextInt(2)*B_WIDTH;	
    		res[1] = (int)(Math.random()*B_HEIGHT);
    	}
    	return res;
    }
    
    public Board(JButton swap2, int INVINCIBLE_TIME) {
    	this.INVINCIBLE_TIME = INVINCIBLE_TIME;
    	this.swap2 = swap2;
    	initBoard();
    }
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                myself.keyPressed(e);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                myself.keyReleased(e);
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
            }
            return false;
        }
    }
    
    private void initBoard() {
    	//addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        
        ingame = 1;   	//正在遊戲狀態     
        setPreferredSize(new Dimension((1+B_WIDTH)*B_STEP, (1+B_HEIGHT)*B_STEP));            
 
        block = new Block();
        obstacle = new Obstacle();
        myself = new Myself();
        initAliens();	//敵人初始化
        start = new Date().getTime();
        new java.util.Timer().schedule(new java.util.TimerTask() {
        			//INVINCIBLE_TIME 過後，本人取消無敵狀態
            @Override
            public void run() {
                INVINCIBLE = false;
            }
        }, INVINCIBLE_TIME);
        
       // new java.util.Timer().schedule(new java.util.TimerTask() {
        
       //     @Override
      //      public void run() {
       //     	aliens.add(new Alien());
       //     }
       // }, 1000*INVINCIBLE_TIME, INVINCIBLE_TIME);  //ADD an enemy periodically
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        for (int i=0;i<ALIEN_NUM;i++) {
        	//int[] p = initPos();
            aliens.add(new Alien());
            System.out.println("Alien left: "+aliens.size());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        	
        if(ingame==1)drawObjects(g);
        else if(ingame==0)drawGameOver(g, "GAME OVER",Color.white,new Font("Vineta BT", Font.BOLD, 100));
        else drawGameOver(g, "  勝利！",Color.red, new Font("MINGLIU",  Font.BOLD, 150));
        Toolkit.getDefaultToolkit().sync();   //？？？？？？不知道什麼意思
    }

    private void drawObjects(Graphics g) {
    	
    	for(int i=0;i<=B_WIDTH;i++){   //draw blocks
    		for(int j=0;j<=B_HEIGHT;j++){
    			if((i%2==0&&j%2==0)||i==0||j==0||i==B_WIDTH||j==B_HEIGHT){
    				//block.getImage();
    				g.drawImage(block.getImage(), i*B_STEP, j*B_STEP, B_STEP, B_STEP, this);
    			}
    		}
    	}

    	for(int[] p : obstacle.pos){
    		g.drawImage(obstacle.getImage(), p[0]/unitSize*B_STEP, p[1]/unitSize*B_STEP, B_STEP, B_STEP, this);
    	}

        if (myself.isVisible()) {
            g.drawImage(myself.getImage(), myself.getX()*B_STEP/unitSize, myself.getY()*B_STEP/unitSize, B_STEP, B_STEP,
                    this);
        }

        ArrayList<Bomb> bombs = myself.getBombs();

        for (Bomb bomb : bombs) {
            if (bomb.isVisible()) {
                g.drawImage(bomb.getImage(), bomb.getX()*B_STEP/unitSize, bomb.getY()*B_STEP/unitSize, B_STEP, B_STEP, this);
            }
            if(bomb.flameVisible){
            	Shape oldClip = g.getClip ();
            	g.setClip(bomb.flameBound);
            	g.drawImage(bomb.flame.getImage(), bomb.flame.getX()*B_STEP/unitSize-bomb.FLAME_LEG*B_STEP, bomb.flame.getY()*B_STEP/unitSize-bomb.FLAME_LEG*B_STEP,(Bomb.BOMB_SIZE+2*Bomb.FLAME_LEG)*B_STEP,(Bomb.BOMB_SIZE+2*Bomb.FLAME_LEG)*B_STEP, this);
            	g.setClip(oldClip);
            }
        }

        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX()*B_STEP/unitSize, a.getY()*B_STEP/unitSize, B_STEP, B_STEP, this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g, String msg, Color color, Font font) {

        FontMetrics fm = getFontMetrics(font);

        g.setColor(color);
        g.setFont(font);
        g.drawString(msg, (B_WIDTH*B_STEP - fm.stringWidth(msg)) / 2,
                B_STEP*B_HEIGHT / 2);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        timeCnt++;
        System.out.println(timeCnt);
        System.out.println("Invincible: "+INVINCIBLE);
        updateCraft();
        updateMissiles();
        updateAliens();
        checkCollisions();
        repaint();
        
        if(ingame==0||ingame==2){		//失敗||勝利
        	long end = new Date().getTime();
        	System.out.println("The last round took "+(end-start)/1000+" s.");
        	removeAll();
        	revalidate();
        	add(swap2);
        	timer.stop();
        }
    }

    private void inGame() {
        
        if (ingame!=1) {
            timer.stop();
        }
    }

    private void updateCraft() {

        if (myself.isVisible()) {
            myself.move();
        }
    }

    private void updateMissiles() {

        ArrayList<Bomb> bombs = myself.getBombs();

        for (int i = 0; i < bombs.size(); i++) {

            Bomb bomb = bombs.get(i);

            if (bomb.isVisible()) {
                bomb.move();
            } else {
                bombs.remove(i);
            }
           }
    }

    private void updateAliens() {

        if (aliens.isEmpty()) {

            ingame = 2;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move(myself, timeCnt, unitSize);
            } else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = myself.getBounds();
        //r3.grow(r3.width, r3.height);
        for (Alien alien : aliens) {
            Rectangle r2 = alien.getBounds();
            if (INVINCIBLE==false&&r3.intersects(r2)) {           	
                //if(r2.width==r3.width+1||Math.random()<0.3){
                	myself.setVisible(false);
                	alien.setVisible(false);
                	System.out.println("Game over. Died from alien. Alien number: "+aliens.size());
                    ingame = 0;
                //}
            }
        }
        
        ArrayList<Bomb> bombs = myself.getBombs();
        for (Bomb bomb : bombs) {
            if(bomb.flameVisible){
            	//Rectangle r1 = bomb.flame.getBounds();
            	if (INVINCIBLE==false&&bomb.flame.reach(myself.x, myself.y)) {     	
                    myself.setVisible(false);
                    System.out.println("Game over. Died from bomb. Alien number: "+aliens.size());
                    ingame = 0;
                }
            	for (Iterator<Alien> i = aliens.iterator(); i.hasNext();) { 
            		Alien alien = i.next();
                    if (bomb.flame.reach(alien.x, alien.y)) {
                        alien.setVisible(false);
                        i.remove();
                    }
                }
            	/*for(int[]p : obstacle.pos){
            		if(bomb.reach(p[0], p[1])){
            			obstacle.pos.remove(p);
            		}
            	}*/
            	
            	for (Iterator<int[]> i = obstacle.pos.iterator(); i.hasNext();) {
            	    int[] p = i.next();
            	    if (bomb.flame.reach(p[0], p[1])) {
            	        i.remove();
            	    }
            	}
            }
        }
    }    
}