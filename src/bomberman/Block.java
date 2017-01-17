package bomberman;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.net.*;


public class Block {
    static final int B_WIDTH = PanelSwap.B_WIDTH;
    static final int B_HEIGHT = PanelSwap.B_HEIGHT;
    static final int unitSize = PanelSwap.unitSize;
	protected Image image;
	
	public Block(){
		loadImage("block.png");
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
    	int x1=x, y1=y;
    	if((x1/unitSize%2==0&&y1/unitSize%2==0)||x1<unitSize||y1<unitSize||x1>(B_WIDTH-1)*unitSize||y1>(B_HEIGHT-1)*unitSize){
    		return false;
    	}
    	if(!(x%unitSize==0&&x/unitSize%2==1)){
    		if(y%unitSize!=0)return false;
    	}
    	if(!(y%unitSize==0&&y/unitSize%2==1)){
    		if(x%unitSize!=0)return false;
    	}
    	return true;
    }
}
