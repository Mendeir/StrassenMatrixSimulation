import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame implements ActionListener {


    // Instant variables declaration
    String [] sizes = {"2x2", "4x4", "8x8"};


    //Instantiation of objects for GUI
    JFrame frame = new JFrame("Strassen's Matrix Algorithm");
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelThree = new JPanel();
    JPanel panelFour = new JPanel();
    JPanel panelMenu = new JPanel();
    JButton enterButton = new JButton("Enter");
    JButton rightButton = new JButton(">");
    JButton leftButton = new JButton("<");
    JComboBox matrixSize = new JComboBox(sizes);


    // Constructor
    InputWindow(){

        // Frame size and design
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,50,800,600);


        // Panel size and format
        panelOne.setBounds(0,0,400,200);
       // panelOne.setBackground(Color.BLUE);
        panelTwo.setBounds(400,0,400,200);
        //panelTwo.setBackground(Color.GREEN);
        panelThree.setBounds(0,150,400,250);
       // panelThree.setBackground(Color.DARK_GRAY);
        panelFour.setBounds(400,150,400,250);
       // panelFour.setBackground(Color.PINK);
        panelMenu.setBounds(0,400,800,100);
        //panelMenu.setBackground(Color.BLACK);

        // Button sizes and formats


        // Adds and visibility of the frame
        frame.add(panelOne);
        frame.add(panelTwo);
        frame.add(panelThree);
        frame.add(panelFour);
        frame.add(panelMenu);
        frame.setVisible(true);




    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
