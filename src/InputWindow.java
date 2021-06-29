import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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
    JPanel result = new JPanel();
    JButton enterButton = new JButton("Enter");
    JButton rightButton = new JButton(">");
    JButton leftButton = new JButton("<");
    JButton randomButton = new JButton("Random");
    JComboBox matrixSize = new JComboBox(sizes);
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel[][] matrixDisplay;
    JLabel[][] splitMatrixDisplay;
    JLabel[][] pointMatrixDisplay;
    JLabel[][] resultMatrixDisplay;
    JLabel[][] test;
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

                nextButton();
                ++navigationCounter;
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

                navigationCounter--;
                nextButton();
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

    public void nextButton() {
        switch(navigationCounter) {
            case 0:
                displaySplitBox("B", "A", "C", "D");
                displaySplitMatrix("A");
                break;

            case 1:
                displaySplitBox("F", "E", "G", "H");
                displaySplitMatrix("B");
                break;

            case 2:
                displaySplitBox("p2 = (a + b)h", "p1 = a(f - h)", "p3 = (c + d)e", "p4 = d(g - e)");
                conquerMatrix("PA1");
                break;
            case 3:
                displaySplitBox("p6 = (b - d)(g + h)", "p5 = (a + d)(e + h)", "p7 = (a - c)(e + f)");
                conquerMatrix("PA2");
                break;
            case 4:
                displaySplitBox("p2 = (a + b)h", "p1 = a(f - h)", "p3 = (c + d)e", "p4 = d(g - e)");
                conquerMatrix("PB1");
                break;
            case 5:
                displaySplitBox("p6 = (b - d)(g + h)", "p5 = (a + d)(e + h)", "p7 = (a - c)(e + f)");
                conquerMatrix("PB2");
                break;
            case 6:
                displaySplitBox("p1 + p2", "p5 + p4 - p2 + p6", "p3 + p4", "p1 + p5 - p3 - p7");
                resultMatrix("C");
                break;
            case 7:
                displaySplitBox("STRASSEN MULTIPLICATION RESULT");
                break;


        }
    }

    public void displaySplitBox(String q1Title, String q2Title, String q3Title, String q4Title) {
        title = BorderFactory.createTitledBorder(blackLine, q1Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, q2Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, q3Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelThree.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, q4Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelFour.setBorder(title);
    }
    public void displaySplitBox(String q1Title, String q2Title, String q3Title) {
        title = BorderFactory.createTitledBorder(blackLine, q1Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, q2Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);
        title = BorderFactory.createTitledBorder(blackLine, q3Title);
        title.setTitleJustification(TitledBorder.CENTER);
        panelThree.setBorder(title);
        panelFour.setBorder(new EmptyBorder(0, 0, 0, 0));
    }
    public void displaySplitBox(String resultTitle) {
        result.setBounds(0, 50, 1000, 700);
        result.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackLine, resultTitle);
        title.setTitleJustification(TitledBorder.CENTER);
        result.setBorder(title);
        frame.add(result);
    }

    public void displaySplitMatrix(String matrixLetter) {
        splitMatrixDisplay = new JLabel[row][column];
        int loopStart = 0;
        int loopEnd = 0;
        int quadrantCounter = 0;

        if (matrixLetter.equals("A")) {
            loopEnd = 4;
        } else {
            loopStart = 4;
            loopEnd = 8;
        }

        for (int processCounter = loopStart; processCounter < loopEnd; ++processCounter) {
            for (i = 0; i < splitMatrixResult[splitMatrixCounter].length; i++) {
                for (j = 0; j < splitMatrixResult[splitMatrixCounter][i].length; j++) {
                    gbc.gridx = j;
                    gbc.gridy = i;

                    switch (quadrantCounter) {
                        case 0 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[processCounter][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);
                        }
                        case 1 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[processCounter][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelOne.add(splitMatrixDisplay[i][j], gbc);
                        }
                        case 2 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[processCounter][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelThree.add(splitMatrixDisplay[i][j], gbc);
                        }
                        case 3 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[processCounter][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelFour.add(splitMatrixDisplay[i][j], gbc);
                        }
                    }

                    splitMatrixDisplay[i][j].setHorizontalAlignment(JLabel.CENTER);
                }
            }
            ++quadrantCounter;
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
    public void conquerMatrix(String matrixLetter) {
        splitMatrixDisplay = new JLabel[row][column];

        int loopStart = 0;
        int loopEnd = 0;
        int quadrantCounter = 0;

        if(matrixLetter.equals("PA1")){
           loopEnd = 4;
        }else if (matrixLetter.equals("PA2")){
            loopStart = 4;
            loopEnd = 7;

        //}else if(matrixLetter.equals("PB1")){

       // }else{

        }
        for (int processCounter = loopStart; processCounter < loopEnd; ++processCounter) {
            for (i = 0; i < splitMatrixResult[splitMatrixCounter].length; i++) {
                for (j = 0; j < splitMatrixResult[splitMatrixCounter][i].length; j++) {
                    gbc.gridx = j;
                    gbc.gridy = i;
                    switch (quadrantCounter) {
                        case 0 -> {
                            splitMatrixDisplay[i][j] = new JLabel("(");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[1][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+2;
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("+");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+3;
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[0][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+4;
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(")");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+5;
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[7][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+6;
                            panelTwo.add(splitMatrixDisplay[i][j], gbc);
                        }
                        case 1 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[1][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelOne.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("(");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+2;
                            panelOne.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[4][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+3;
                            panelOne.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("-");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+4;
                            panelOne.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[7][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+5;
                            panelOne.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(")");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+6;
                            panelOne.add(splitMatrixDisplay[i][j], gbc);
                        }
                        case 2 -> {
                            splitMatrixDisplay[i][j] = new JLabel("(");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[2][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+2;
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("+");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+3;
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[3][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+4;
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(")");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+5;
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[5][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+6;
                            panelThree.add(splitMatrixDisplay[i][j], gbc);

                        }
                        case 3 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[3][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelFour.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("(");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+2;
                            panelFour.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[6][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+3;
                            panelFour.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel("-");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+4;
                            panelFour.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(splitMatrixResult[5][i][j]));
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+5;
                            panelFour.add(splitMatrixDisplay[i][j], gbc);

                            splitMatrixDisplay[i][j] = new JLabel(")");
                            splitMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            gbc.gridx = j+7;
                            panelFour.add(splitMatrixDisplay[i][j], gbc);
                        }
                    }
                    splitMatrixDisplay[i][j].setHorizontalAlignment(JLabel.LEFT);
                }
            }
            ++quadrantCounter;
        }
       /* pointMatrixDisplay = new JLabel[row][column];
        quadrantCounter = 0;

        for (int processCounter = loopStart; processCounter < loopEnd; ++processCounter) {
            for (i = 0; i < resultantMatrixResult[resultantMatrixCounter].length; i++) {
                for (j = 0; j < resultantMatrixResult[resultantMatrixCounter][i].length; j++) {
                    gbc.gridx = j + 2;
                    gbc.gridy = i ;
                    switch (quadrantCounter) {
                        case 0 -> {
                            pointMatrixDisplay[i][j] = new JLabel(Integer.toString(pointsMatrixResult[processCounter][i][j]));
                            pointMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelTwo.add(pointMatrixDisplay[i][j], gbc);
                            splitMatrixDisplay[i][j].setHorizontalAlignment(JLabel.LEFT);
                            pointMatrixDisplay[i][j].setHorizontalAlignment(JLabel.RIGHT);
                        }
                        case 1 -> {
                            pointMatrixDisplay[i][j] = new JLabel(Integer.toString(pointsMatrixResult[processCounter][i][j]));
                            pointMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelOne.add(pointMatrixDisplay[i][j], gbc);
                        }
                        case 2 -> {
                            pointMatrixDisplay[i][j] = new JLabel(Integer.toString(pointsMatrixResult[processCounter][i][j]));
                            pointMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelThree.add(pointMatrixDisplay[i][j], gbc);
                        }
                        case 3 -> {
                            pointMatrixDisplay[i][j] = new JLabel(Integer.toString(pointsMatrixResult[processCounter][i][j]));
                            pointMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelFour.add(pointMatrixDisplay[i][j], gbc);
                        }
                    }

                }
            }
            ++quadrantCounter;
        }
*/
    }

    public void resultMatrix(String matrixLetter) {
        resultMatrixDisplay = new JLabel[row][column];
        int loopStart = 0;
        int loopEnd = 0;
        int quadrantCounter = 0;

        if (matrixLetter.equals("C")) {
            loopEnd = 4;
        } else {
            loopStart = 4;
            loopEnd = 8;
        }

        for (int processCounter = loopStart; processCounter < loopEnd; ++processCounter) {
            for (i = 0; i < resultantMatrixResult[resultantMatrixCounter].length; i++) {
                for (j = 0; j < resultantMatrixResult[resultantMatrixCounter][i].length; j++) {
                    gbc.gridx = j;
                    gbc.gridy = i;

                    switch (quadrantCounter) {
                        case 0 -> {
                            resultMatrixDisplay[i][j] = new JLabel(Integer.toString(resultantMatrixResult[processCounter][i][j]));
                            resultMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelTwo.add(resultMatrixDisplay[i][j], gbc);
                        }
                        case 1 -> {
                            splitMatrixDisplay[i][j] = new JLabel(Integer.toString(resultantMatrixResult[processCounter][i][j]));
                            resultMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelOne.add(resultMatrixDisplay[i][j], gbc);
                        }
                        case 2 -> {
                            resultMatrixDisplay[i][j] = new JLabel(Integer.toString(resultantMatrixResult[processCounter][i][j]));
                            resultMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelThree.add(resultMatrixDisplay[i][j], gbc);
                        }
                        case 3 -> {
                            resultMatrixDisplay[i][j] = new JLabel(Integer.toString(resultantMatrixResult[processCounter][i][j]));
                            resultMatrixDisplay[i][j].setFont(new Font("Arial", Font.BOLD, 25));
                            panelFour.add(resultMatrixDisplay[i][j], gbc);
                        }
                    }

                    resultMatrixDisplay[i][j].setHorizontalAlignment(JLabel.CENTER);
                }
            }
            ++quadrantCounter;
        }
    }

}




