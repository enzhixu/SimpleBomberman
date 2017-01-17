package bomberman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelSwap extends JPanel implements ActionListener {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int unitSize=4;  //how many steps a block has
    static final int B_WIDTH = 20;
    static final int B_HEIGHT = 12;
    static final int B_STEP = (int)screenSize.getHeight()*17/20/B_HEIGHT;
    static final int OBSTACLE_NUM = 40;
    static final int ALIEN_NUM = 15;
    //static final int INVINCIBLE_TIME = 5000;
    static final int EASY_INVINCIBLE_TIME = 20000;
    static final int MIDIUM_INVINCIBLE_TIME = 10000;
    static final int HARD_INVINCIBLE_TIME = 5000;
    static final int explodeTime = 40;


    
    JButton swap1 = new JButton("����"); 
    JButton swap2 = new JButton("�е�");
    JButton swap3 = new JButton("���y");
    JButton swapFinish = new JButton("Main menu");  
   
    JPanel firstPanel = new bomberman.ChoiceInterface((1+B_WIDTH)*B_STEP, (1+B_HEIGHT)*B_STEP);
    JPanel secondPanel;
    JTextArea note, note0;

    public PanelSwap() {
        //super(new BorderLayout());
    	firstPanel.setLayout(new BorderLayout());
        firstPanel.setBackground(Color.red);
        swap1.addActionListener(this);
        swap1.setBackground(Color.RED);
        swap1.setFont(new Font("MingLiU", Font.PLAIN, 160));
        
        swap2.addActionListener(this);
        swap2.setBackground(Color.RED);
        swap2.setFont(new Font("MingLiU", Font.PLAIN, 160));
        
        swap3.addActionListener(this);
        swap3.setBackground(Color.RED);
        swap3.setFont(new Font("MingLiU", Font.PLAIN, 160));
        
      //swap2.addActionListener(this);
        //secondPanel.setBackground(Color.YELLOW);
        note0 = new JTextArea("Ո�x���[���y���_ʼ�[��");
        note0.setFont(new Font("MingLiU", Font.PLAIN, 60));
        note0.setLineWrap(true);
        note0.setBackground(Color.ORANGE);
        
        note = new JTextArea("�f���������������I�Ƅӣ����ո��I����ը������A�I����ը������tը�����4������Ԅӱ�ը������ȫ��ը���t���Y������\n�o����B�r�g�����Σ�"+EASY_INVINCIBLE_TIME/1000+"�룻�еȣ�"+MIDIUM_INVINCIBLE_TIME/1000+"�룻���y��"+HARD_INVINCIBLE_TIME/1000+"�롣");
        note.setFont(new Font("MingLiU", Font.PLAIN, 60));
        note.setLineWrap(true);
        note.setBackground(Color.ORANGE);
        
        firstPanel.add(note0, BorderLayout.NORTH);
        firstPanel.add(swap1, BorderLayout.WEST);
        firstPanel.add(swap2, BorderLayout.CENTER);
        firstPanel.add(swap3, BorderLayout.EAST);
        firstPanel.add(note, BorderLayout.SOUTH);
        //firstPanel.add(new JButton(" "), BorderLayout.NORTH);
        //secondPanel.add(swap2);
        add(firstPanel);
    }

    /** Listens to the buttons and perfomr the swap. */
    public void actionPerformed(ActionEvent e) {

    	if(e.getSource()==swap1){
    		remove(firstPanel);
            swapFinish.addActionListener(this);            
            swapFinish.setBackground(Color.gray);
            swapFinish.setFont(new Font("Times New Roman", Font.PLAIN, 80));           
            secondPanel = new Board(swapFinish, EASY_INVINCIBLE_TIME);
            add(secondPanel);
    	}else if(e.getSource()==swap2){
    		remove(firstPanel);
            swapFinish.addActionListener(this);           
            swapFinish.setBackground(Color.gray);
            swapFinish.setFont(new Font("Times New Roman", Font.PLAIN, 80));         
            secondPanel = new Board(swapFinish, MIDIUM_INVINCIBLE_TIME);
            add(secondPanel);
    	}else if(e.getSource()==swap3){
    		remove(firstPanel);
            swapFinish.addActionListener(this);           
            swapFinish.setBackground(Color.gray);
            swapFinish.setFont(new Font("Times New Roman", Font.PLAIN, 80));         
            secondPanel = new Board(swapFinish, HARD_INVINCIBLE_TIME);
            add(secondPanel);
    	}else if(e.getSource()==swapFinish){
    		remove(secondPanel);
            add(firstPanel);
    	}
        repaint();
        revalidate();
    }
}
