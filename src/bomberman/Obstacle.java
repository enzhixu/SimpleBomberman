package bomberman;

import java.awt.Image;
import java.net.URL;
import java.util.*;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Obstacle {

	static final int B_STEP = PanelSwap.B_STEP;
    static final int B_WIDTH = PanelSwap.B_WIDTH;
    static final int B_HEIGHT = PanelSwap.B_HEIGHT;
    static final int unitSize = PanelSwap.unitSize;
    static int OBSTACLE_NUM = PanelSwap.OBSTACLE_NUM;
    static Set<int[]> pos;
	protected Image image;
	
	public Obstacle(){
		pos = new HashSet<int[]>();
		loadImage("obstacle.png");
		Random random = new Random();
		for(int i=0;i<OBSTACLE_NUM;i++){
			int[] p = new int[2];
			int cnt=0;
			while(cnt==0){
				p[0] = (1+random.nextInt(B_WIDTH-1))*unitSize;
				p[1] = (1+random.nextInt(B_HEIGHT-1))*unitSize;
				if(Block.freePos(p[0], p[1])&&(!pos.contains(p))){
					pos.add(p);
					cnt++;
				}
			}
		}
	}
	
    protected void loadImage(String imageName) {
    	URL url = getClass().getResource("/images/"+imageName);
        ImageIcon ii = new ImageIcon(url);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }
    
    static boolean freePos(int x, int y){
    	if(!Block.freePos(x, y))return false;
    	for(int[]p : pos){
			if(x/unitSize==p[0]/unitSize&&y/unitSize==p[1]/unitSize)return false;
			Rectangle obs = new Rectangle(p[0],p[1],unitSize,unitSize);
			Rectangle target = new Rectangle(x,y,unitSize,unitSize);
			if(obs.intersects(target))return false;

		}
    	
    	return true;
    }
}

