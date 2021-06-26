import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame implements ActionListener {


    // Instant variables declaration
    String [] sizes = {"2x2", "4x4", "8x8"};

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
    JComboBox matrixSize = new JComboBox(sizes);
    GridBagConstraints gbc = new GridBagConstraints();
    JTextField[][] input;
    TitledBorder title;
    Border blackline = BorderFactory.createLineBorder(Color.BLACK);

    // Constructor
    InputWindow(){

        // Frame size and design
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,50,820,600);

        //format for display output
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel size and format
        panelOne.setBounds(0,30,400,200);
        panelOne.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder(blackline, "Matrix A");
        title.setTitleJustification(TitledBorder.CENTER);
        panelOne.setBorder(title);

        //panelOne.setBackground(Color.BLUE);
        panelTwo.setBounds(400,30,400,200);
        panelTwo.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder( blackline,"Matrix B");
        title.setTitleJustification(TitledBorder.CENTER);
        panelTwo.setBorder(title);
        //panelTwo.setBackground(Color.GREEN);
        panelThree.setBounds(0,180,400,250);
        //panelThree.setBackground(Color.DARK_GRAY);
        panelFour.setBounds(400,180,400,250);
        //panelFour.setBackground(Color.PINK);
        panelMenu.setBounds(0,430,800,100);
        //panelMenu.setBackground(Color.BLACK);
        dropDownPanel.setBounds(0,0,800,100);

        // Button sizes and formats
        // Combobox design and simulations
        dropDownPanel.add(matrixSize);
        matrixSize.setBounds(350, 20, 100, 25);
        matrixSize.addActionListener(this);


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

        if(e.getSource() == enterButton){

        }

        if(e.getSource() == rightButton){

        }

        if(e.getSource() == leftButton){

        }
    }
    // layout for combo box options
    public void gridLayoutOne(int row, int column) {
        input = new JTextField[row][column];
        panelOne.removeAll();
        panelOne.revalidate();
        frame.repaint();

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                    input[i][j] = new JTextField("-", 3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    input[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                    panelOne.add(input[i][j], gbc);
                    input[i][j].setHorizontalAlignment(JTextField.CENTER);
                }
            }
        }

    public void gridLayoutTwo(int row, int column) {
        input = new JTextField[row][column];
        panelTwo.removeAll();
        panelTwo.revalidate();
        frame.repaint();

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                input[i][j] = new JTextField("-", 3);
                gbc.gridx = i;
                gbc.gridy = j;
                input[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                panelTwo.add(input[i][j], gbc);
                input[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }
}
