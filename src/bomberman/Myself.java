package bomberman;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.*;

public class Myself extends Sprite {

    private int dx;
    private int dy;
    private int unitSize = PanelSwap.unitSize;
    private ArrayList<Bomb> bombs;

    public Myself() {
    	Random random = new Random();
        do{
        	x = (1+random.nextInt(PanelSwap.B_WIDTH-1))*unitSize;
        	y = (1+random.nextInt(PanelSwap.B_HEIGHT-1))*unitSize;
        }while(!Obstacle.freePos(x, y));

        initCraft();
    }

    private void initCraft() {
        
        bombs = new ArrayList<>();
        loadImage("craft.jpg");		//myselfˆDÆ¬Ãû×Ö
    }

    public void move() {

      /*  if(Obstacle.freePos(x+dx, y+dy)){
        	x += dx;
            y += dy;
        }*/
        if(dy==0&&dx!=0){
        	if(Obstacle.freePos(x+dx, y)||Obstacle.freePos(x+dx, y+1)||Obstacle.freePos(x+dx, y-1)){
	        	x += dx;
	        	y = (int)Math.round((double)y/unitSize)*unitSize;
        	}
        }else if(dx==0&&dy!=0){
	        	if(Obstacle.freePos(x, y+dy)||Obstacle.freePos(x+1, y+dy)||Obstacle.freePos(x-1, y+dy)){
	        	x = (int)Math.round((double)x/unitSize)*unitSize;
	        	y += dy;
        	}
        }
        //if (x < 0) x = 0;
        //else if(x>Board.B_WIDTH) x=Board.B_WIDTH;

        //if (y < 0) y = 0;
        //else if(y>Board.B_HEIGHT) y=Board.B_HEIGHT;
    }

    public ArrayList getBombs() {
        return bombs;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A){
        	explode();
        }
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
        bombs.add(new Bomb((int)Math.round((double)x/unitSize)*unitSize, (int)Math.round((double)y/unitSize)*unitSize));
    }
    
    public void explode() {
        if(bombs.size()!=0)bombs.get(0).count = PanelSwap.explodeTime;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    boolean freePos(int x, int y){
    	//if(!Obstacle.freePos(x, y))return false;
    	for(Bomb bomb : bombs){
			//if(x/unitSize==p[0]/unitSize&&y/unitSize==p[1]/unitSize)return false;
			Rectangle bombRec = new Rectangle(bomb.x,bomb.y,unitSize,unitSize);
			Rectangle target = new Rectangle(x,y,unitSize,unitSize);
			if(bombRec.intersects(target))return false;

		}
    	
    	return true;
    }
    
}
