package bomberman;

import java.awt.Rectangle;

public class Bomb extends Sprite {

	public static int BOMB_SIZE=1;
	public static int FLAME_LEG=2;
    private final int BOARD_WIDTH = 390;
    private final int EXPLODE_RANGE = 10;
    public final int unitSize = PanelSwap.unitSize;
    static final int B_STEP = PanelSwap.B_STEP;
    Rectangle flameBound;
    Rectangle flameX;
    Rectangle flameY;
    int count = 0;
    int explodeTime=40;
    int explodeDuration = 5;
    boolean flameVisible = false;
    Flame flame;
    
    public class Flame extends Sprite{
    	int top = FLAME_LEG*unitSize;
    	int bottom = FLAME_LEG*unitSize;
    	int right = FLAME_LEG*unitSize;
    	int left = FLAME_LEG*unitSize;
    	public Flame(int x, int y){
    		super(x, y);
    		initFlame();
    		for(int i=x/unitSize-1;i>=0&&i>=x/unitSize-FLAME_LEG;i--){
    			if(!Block.freePos(i*unitSize, y)&&!Block.freePos(i*unitSize, y+1)&&!Block.freePos(i*unitSize-1, y-1)){
    				left= x-(i+1)*unitSize;
    				break;
    			}
    		}
    		for(int i=x/unitSize+1;i<=PanelSwap.B_WIDTH&&i<=x/unitSize+FLAME_LEG;i++){
    			if(!Block.freePos(i*unitSize, y)&&!Block.freePos(i*unitSize, y+1)&&!Block.freePos(i*unitSize-1, y-1)){
    				right= (i-1)*unitSize-x;
    				break;
    			}
    		}
    		for(int i=y/unitSize-1;i>=0&&i>=y/unitSize-FLAME_LEG;i--){
    			if(!Block.freePos(x, i*unitSize)&&!Block.freePos(x+1, i*unitSize)&&!Block.freePos(x-1, i*unitSize)){
    				bottom= y-(i+1)*unitSize;
    				break;
    			}
    		}
    		for(int i=y/unitSize+1;i<=PanelSwap.B_HEIGHT&&i<=y/unitSize+FLAME_LEG;i++){
    			if(!Block.freePos(x, i*unitSize)&&!Block.freePos(x+1, i*unitSize)&&!Block.freePos(x-1, i*unitSize)){
    				top= (i-1)*unitSize-y;
    				break;
    			}
    		}
    		
    		flameBound = new Rectangle((x-left)*B_STEP/unitSize, (y-bottom)*B_STEP/unitSize, (left+right+unitSize)*B_STEP/unitSize, (bottom+top+unitSize)*B_STEP/unitSize);
    		flameX = new Rectangle((x-left)*B_STEP/unitSize, y*B_STEP/unitSize, (left+right+unitSize)*B_STEP/unitSize, B_STEP);
    		flameY = new Rectangle(x*B_STEP/unitSize, (y-bottom)*B_STEP/unitSize, B_STEP, (bottom+top+unitSize)*B_STEP/unitSize);
    	}
    	public void initFlame(){
    		loadImage("flame.png");    
    	}
        //public Rectangle getBounds() {
         //   return new Rectangle(x, y, width, height);
        //}
        
        public boolean reach(int x, int y){
        	Rectangle target = new Rectangle(x*B_STEP/unitSize, y*B_STEP/unitSize, B_STEP, B_STEP);
        	
        	if(target.intersects(flameX)||target.intersects(flameY))return true;
        	return false;
        }
        
    }
    
    //private final i
    public Bomb(int x, int y) {
        super(x, y);
        initBomb();
    }
    
    private void initBomb() {
        
        loadImage("bomb.jpg");        
    }
    public void move(){
    	count+=1;
    	if(count>explodeTime){
    		flameVisible=true;
    		flame = new Flame(x, y);
    		//setVisible(false); 
    	}
    	if(count>explodeTime+explodeDuration){
    		flameVisible=false;
    		setVisible(false);
    	}
    }
    
}
