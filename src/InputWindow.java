import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;

public class InputWindow extends JFrame implements ActionListener {

    // Calling Logic window
    Logic strassenLogic = new Logic();


    // Instant variables declaration
    String[] sizes = {"2x2", "4x4", "8x8"};
    char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    int[][] matrixA;
    int[][] matrixB;

    //Stored values of the process

    int[][][] splitMatrixResult;
    int[][][] pointsMatrixResult;
    int[][][] resultantMatrixResult;

    //Matrix Navigation Counter
    static int navigationCounter = 0;
    static int splitMatrixCounter = 0;
    static int pointsMatrixCounter = 0;
    static int resultantMatrixCounter = 0;

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
    JLabel[][] matrixDisplay;
    JLabel[][] splitMatrixDisplay;
    JTextField[][] inputOne;
    JTextField[][] inputTwo;
    TitledBorder title;
    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

    // Constructor
    InputWindow() {

        // Frame size and design
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 0, 1000, 700);

        //format for display output
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel size and format
        dropDownPanel.setBounds(0, 0, 1000, 50);
        //dropDownPanel.setBackground(Color.ORANGE);
        panelOne.setBounds(0, 50, 500, 300);
        panelOne.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackLine, "Matrix A");
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);
        //panelOne.setBackground(Color.BLUE);
        panelTwo.setBounds(500, 50, 500, 300);
        panelTwo.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackLine, "Matrix B");
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
       //panelTwo.setBackground(Color.GREEN);
        panelThree.setBounds(0, 350, 500, 300);
        panelThree.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackLine, "Given Matrix A");
        title.setTitleJustification(TitledBorder.CENTER);
        panelThree.setBorder(title);
        //panelThree.setBackground(Color.DARK_GRAY);
        panelFour.setBounds(500, 350, 500, 300);
        panelFour.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackLine, "Given Matrix B");
        title.setTitleJustification(TitledBorder.CENTER);
        panelFour.setBorder(title);
        //panelFour.setBackground(Color.PINK);
        panelMenu.setBounds(0, 650, 800, 100);
        //panelMenu.setBackground(Color.BLACK);

        // Button sizes and formats
        dropDownPanel.add(leftButton);
        dropDownPanel.add(rightButton);
        rightButton.addActionListener(this);
        leftButton.addActionListener(this);
        randomButton.setBounds(0, 20, 100, 30);
        randomButton.addActionListener(this);
        dropDownPanel.add(randomButton);
        enterButton.setBounds(300, 20, 100, 25);
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
        if (e.getSource() == randomButton) {
            for (i = 0; i < row; i++) {
                for (j = 0; j < column; j++) {
                    inputOne[i][j].setText(String.format("%d", (int) (Math.random() * 5)));
                    inputTwo[i][j].setText(String.format("%d", (int) (Math.random() * 5)));
                }
            }
        }

            // Enter button action
            if (e.getSource() == enterButton) {

                // Initialize matrixA and matrixB sizes
                matrixA = new int[row][column];
                matrixB = new int[row][column];

                // Store all the values input by the user on Matrix A and Matrix B
                for (i = 0; i < row; i++) {
                    for (j = 0; j < column; j++) {
                        matrixA[i][j] = Integer.parseInt(inputOne[i][j].getText());
                        matrixB[i][j] = Integer.parseInt(inputTwo[i][j].getText());
                    }
                }
                displayGivenMatrix(matrixA,matrixB);
                strassenLogic.multiplyMatrix(matrixA, matrixB);
                splitMatrixResult = strassenLogic.getSplitMatrixResult();
                pointsMatrixResult = strassenLogic.getPointsMatrixResult();
                resultantMatrixResult = strassenLogic.getResultantMatrixResult();
            }

            // Next Button Action
            if (e.getSource() == rightButton) {
                panelOne.removeAll();
                panelOne.revalidate();
                panelTwo.removeAll();
                panelTwo.revalidate();
                panelThree.removeAll();
                panelThree.revalidate();
                panelFour.removeAll();
                panelFour.revalidate();
                frame.repaint();

                if(navigationCounter < 1) {
                    nextButton(splitMatrixResult);
                    splitMatrixCounter++;
                    navigationCounter++;
                }
            }

            // Previous Button Action
            if (e.getSource() == leftButton) {
                panelOne.removeAll();
                panelOne.revalidate();
                panelTwo.removeAll();
                panelTwo.revalidate();
                panelThree.removeAll();
                panelThree.revalidate();
                panelFour.removeAll();
                panelFour.revalidate();
                frame.repaint();


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
                gbc.gridx = j;
                gbc.gridy = i;
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
                gbc.gridx = j;
                gbc.gridy = i;
                inputTwo[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                panelTwo.add(inputTwo[i][j], gbc);
                inputTwo[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }
    public void nextButton(int[][][] splitMatrixResult) {

        title = BorderFactory.createTitledBorder(blackLine, "B");
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, "A");
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, "C");
        title.setTitleJustification(TitledBorder.CENTER);
        panelThree.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, "D");
        title.setTitleJustification(TitledBorder.CENTER);
        panelFour.setBorder(title);
    }

    public void displaySplitMatrix(String matrixLetter) {
            splitMatrixDisplay = new JLabel[row][column];

        for (i = 0; i < splitMatrixResult[splitMatrixCounter].length; i++) {
            for (j = 0; j < splitMatrixResult[splitMatrixCounter][i].length; j++) {
                splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[0][i][j]));
                gbc.gridx = j;
                gbc.gridy = i;
                splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                panelTwo.add(splitMatrixDisplay[i][j], gbc);
                splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[1][i][j]));
                splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                panelOne.add(splitMatrixDisplay[i][j], gbc);
                splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[2][i][j]));
                splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                panelThree.add(splitMatrixDisplay[i][j], gbc);
                splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[3][i][j]));
                splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                panelFour.add(splitMatrixDisplay[i][j], gbc);
                splitMatrixDisplay[i][j].setHorizontalAlignment(JLabel.CENTER);
            }
        }
    }

        public void displayGivenMatrix(int[][] matrixA, int[][] matrixB){
            matrixDisplay = new JLabel[row][column];

            panelThree.removeAll();
            panelThree.revalidate();

            for (i = 0; i < row ; i++) {
                for (j = 0; j < column ; j++) {
                        matrixDisplay[i][j] = new JLabel(Integer.toString(matrixA[i][j]));;
                        gbc.gridx = j;
                        gbc.gridy = i;
                        matrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                        panelThree.add(matrixDisplay[i][j], gbc);
                        matrixDisplay[i][j].setHorizontalAlignment(JLabel.CENTER);
                }
            }

            panelFour.removeAll();
            panelFour.revalidate();
            frame.repaint();

            for (i = 0; i < row ; i++) {
                for (j = 0; j < column ; j++) {
                    matrixDisplay[i][j] = new JLabel(Integer.toString(matrixB[i][j]));;
                    gbc.gridx = j;
                    gbc.gridy = i;
                    matrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                    panelFour.add(matrixDisplay[i][j], gbc);
                    matrixDisplay[i][j].setHorizontalAlignment(JLabel.CENTER);
                }
            }
        }

    // Conquer the divided sub-problems
    public void conquerMatrix() {

    }

    public void resultMatrix() {

    }

    public void panelOneDisplay() {

    }

    public void panelTwoDisplay() {

    }

    public void panelThreeDisplay() {

    }

    public void panelFourDisplay() {

    }
}




