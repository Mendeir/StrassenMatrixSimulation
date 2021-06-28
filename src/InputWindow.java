import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class InputWindow extends JFrame implements ActionListener {


    // Instant variables declaration
    String [] sizes = {"2x2", "4x4", "8x8"};
    char [] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    String [][] matrixA;
    String [][] matrixB;

    //declaration for matrixSize
    int row;
    int column;

    //instantiation for loops
    int i = 0;
    int j = 0;

    //Instantiation of objects for GUI
    JFrame frame = new JFrame("Strassen's Matrix Algorithm");
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelThree = new JPanel();
    JPanel panelFour = new JPanel();
    JPanel dropDownPanel = new JPanel();
    JPanel panelMenu = new JPanel();
    JButton enterButton = new JButton("Enter");
    JButton rightButton = new JButton(">");
    JButton leftButton = new JButton("<");
    JButton randomButton = new JButton("Random");
    JComboBox matrixSize = new JComboBox(sizes);
    GridBagConstraints gbc = new GridBagConstraints();
    JTextField[][] inputOne;
    JTextField[][] inputTwo;
    TitledBorder title;
    Border blackline = BorderFactory.createLineBorder(Color.BLACK);

    // Constructor
    InputWindow(){

        // Frame size and design
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,0,1000,700);

        //format for display output
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel size and format
        dropDownPanel.setBounds(0,0,1000,50);
        dropDownPanel.setBackground(Color.ORANGE);
        panelOne.setBounds(0,50,500,300);
        panelOne.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackline, "Matrix A");
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);
        panelOne.setBackground(Color.BLUE);
        panelTwo.setBounds(500,50,500,300);
        panelTwo.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder( blackline,"Matrix B");
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
        panelTwo.setBackground(Color.GREEN);
        panelThree.setBounds(0,350,500,300);
        panelThree.setBackground(Color.DARK_GRAY);
        panelFour.setBounds(500,350,500,300);
        panelFour.setBackground(Color.PINK);
        panelMenu.setBounds(0,650,800,100);
        panelMenu.setBackground(Color.BLACK);

        // Button sizes and formats
        dropDownPanel.add(leftButton);
        dropDownPanel.add(rightButton);
        rightButton.addActionListener(this);
        leftButton.addActionListener(this);
        randomButton.setBounds(0,20,100,30);
        randomButton.addActionListener(this);
        dropDownPanel.add(randomButton);
        enterButton.setBounds(300,20,100,25);
        enterButton.addActionListener(this);
        dropDownPanel.add(enterButton);

        // Combobox design and simulations
        matrixSize.setBounds(200, 20, 100, 25);
        matrixSize.addActionListener(this);
        dropDownPanel.add(matrixSize);

        // Adds and visibility of the frame
        frame.add(panelOne);
        frame.add(panelTwo);
        frame.add(panelThree);
        frame.add(panelFour);
        frame.add(dropDownPanel);
        frame.add(panelMenu);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // box size action
        if (e.getSource() == matrixSize) {

            // Get the user's wanted size form the comboBox
            String size = (String) matrixSize.getSelectedItem();

            String[] s = size.split("x");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);
            gridLayoutOne(row, column);
            gridLayoutTwo(row, column);
        }

        // Random Button action
        if(e.getSource() == randomButton){
            for(i=0;i<row;i++){
                for(j=0;j<column;j++){
                    inputOne[i][j].setText(String.format("%d", (int)(Math.random() * 5)));
                    inputTwo[i][j].setText(String.format("%d", (int)(Math.random() * 5)));
                }
            }
        }

        // Enter button action
        if(e.getSource() == enterButton){
            matrixA = new String[row][column];
            matrixB = new String[row][column];

            // Store all the values input by the user on Matrix A
            for(i=0;i<row;i++){
                for(j=0;j<column;j++){
                    matrixA[i][j] = inputOne[i][j].getText();
                }
            }

            // Store all the values input by the user on Matrix B
            for(i=0;i<row;i++){
                for(j=0;j<column;j++){
                    matrixB[i][j] = inputTwo[i][j].getText();
                }
            }
        }

        // Next Button Action
        if(e.getSource() == rightButton){

        }

        // Previous Button Action
        if(e.getSource() == leftButton){

        }
    }
    // layout for combo box options
    public void gridLayoutOne(int row, int column) {
        inputOne = new JTextField[row][column];
        panelOne.removeAll();
        panelOne.revalidate();
        frame.repaint();

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                    inputOne[i][j] = new JTextField(3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    inputOne[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                    panelOne.add(inputOne[i][j], gbc);
                    inputOne[i][j].setHorizontalAlignment(JTextField.CENTER);
                }
            }
        }

    public void gridLayoutTwo(int row, int column) {
        inputTwo = new JTextField[row][column];
        panelTwo.removeAll();
        panelTwo.revalidate();
        frame.repaint();

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                inputTwo[i][j] = new JTextField(3);
                gbc.gridx = i;
                gbc.gridy = j;
                inputTwo[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                panelTwo.add(inputTwo[i][j], gbc);
                inputTwo[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }

    // Dividing the two matrix
    public void divideMatrix(){

    }

    // Conquer the divided sub-problems
    public void conquerMatrix(){

    }


    public void resultMatrix(){

    }

    public void panelOneDisplay(){

    }
    public void panelTwoDisplay(){

    }
    public void panelThreeDisplay(){

    }
    public void panelFourDisplay(){

    }

}
