package bomberman;

import java.util.Random;

;

public class Alien extends Sprite {

    private final int B_WIDTH = Board.B_WIDTH;
    private final int B_HEIGHT = Board.B_HEIGHT;
    private final int unitSize = PanelSwap.unitSize;
    private int dx;
    private int dy;

    public Alien() {
        //super(x, y);
        dx = 0;
        dy = 0;
        Random random = new Random();
        do {
            x = (1 + random.nextInt(B_WIDTH - 1)) * unitSize;
            y = (1 + random.nextInt(B_HEIGHT - 1)) * unitSize;
        } while (!Obstacle.freePos(x, y));

        //vx = (int)Math.round((Math.random()-1)*1);
        //vy = (int)Math.round((Math.random()-1)*1);
        initAlien();
    }

    private void initAlien() {

        loadImage("alien.jpg");
    }

    public void move(Myself myself, int cnt, int div) {
        if (cnt % 4 != 0) {
            x += dx;
            y += dy;
            return;
        }
        int top = 0, bottom = 0, left = 0, right = 0;
        if (Obstacle.freePos(x, y + unitSize) && myself.freePos(x, y + unitSize)) top = 1;
        if (Obstacle.freePos(x, y - unitSize) && myself.freePos(x, y - unitSize)) bottom = 1;
        if (Obstacle.freePos(x + unitSize, y) && myself.freePos(x + unitSize, y)) right = 1;
        if (Obstacle.freePos(x - unitSize, y) && myself.freePos(x - unitSize, y)) left = 1;

        if (dy == 1 && top == 1) {
            bottom = 0;
            top = 2;
        } else if (dy == -1 && bottom == 1) {
            bottom = 2;
            top = 0;
        } else if (dx == 1 && right == 1) {
            right = 2;
            left = 0;
        } else if (dx == -1 && left == 1) {
            right = 0;
            left = 2;
        }

        int sum = top + bottom + left + right;
        if (sum == 0) return;
        double rand = Math.random();
        if (rand < (double) top / sum) {
            y++;
            dy = 1;
            dx = 0;
        } else if (rand < (double) (top + bottom) / sum) {
            y--;
            dy = -1;
            dx = 0;
        } else if (rand < (double) (top + bottom + right) / sum) {
            x++;
            dx = 1;
            dy = 0;
        } else {
            x--;
            dx = -1;
            dy = 0;
        }

 /*       if (x<=0) {
        	x=0;
        	vx = -vx;
        }else if(x>=B_WIDTH){
        	x=B_WIDTH;
        	vx = -vx;
        }
        if(y<=0){
        	y=0;
        	vy = -vy;
        }else if(y>=B_HEIGHT){
        	y=B_WIDTH;
        	vy = -vy;
        }
        
        x += vx+(int)((Math.random()-0.5)*2);
        y += vy+(int)((Math.random()-0.5)*2);
      */
    }
}
