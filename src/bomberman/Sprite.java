package bomberman;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.net.*;

public class Sprite {

    protected int x;
    protected int y;
    protected int width=PanelSwap.unitSize;
    protected int height=PanelSwap.unitSize;
    protected boolean vis;
    protected Image image;
    
    public Sprite(){vis = true;}
    
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) {
    	URL url = getClass().getResource("/images/"+imageName);
        ImageIcon ii = new ImageIcon(url);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
