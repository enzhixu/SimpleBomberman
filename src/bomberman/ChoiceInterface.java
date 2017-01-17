package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChoiceInterface extends JPanel{
    
    public ChoiceInterface(int width, int height){
        //Container conPane=getContentPane();
        //conPane.setBackground(Color.BLUE);
        //conPane.setLayout(new FlowLayout());//采用FlowLayout布局
    	setFocusable(true);
        setBackground(Color.BLACK);
        
    	System.out.println(width+"+ "+height);
    	setPreferredSize(new Dimension(width, height));
    	setLayout(new FlowLayout());
    }
}
