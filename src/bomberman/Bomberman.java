
package bomberman;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Bomberman extends JFrame {

    public Bomberman() {
        initUI();
    }

    private void initUI() {


        //ChoiceInterface choiceInterface = new ChoiceInterface(1000,800);
        //add(choiceInterface);
        //getContentPane().removeAll();
        //pack();
        add(new bomberman.PanelSwap());
        //add(new Board(new JButton("SwapToRed")));


        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        setResizable(true);
        pack();

        setTitle("Bomberman");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Bomberman ex = new Bomberman();
                ex.setVisible(true);
            }
        });
    }
}