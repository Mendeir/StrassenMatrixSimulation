public class Main {

    // Main method of the program
    public static void main(String[] args) {

        // Calling the InputWindow for graphical user interface
        InputWindow window = new InputWindow();
        int[][] matrixA = {{1, 0, 2, 1},
                {4, 1, 1, 0},
                {0, 1, 3, 0},
                {5, 0, 2, 1}};

        int[][] matrixB = {{0, 1, 0, 1},
                {2, 1, 0, 4},
                {2, 0, 1, 1},
                {1, 3, 5, 0}};

        Logic test = new Logic();
        int[][] resultMatrix = test.multiplyMatrix(matrixA, matrixB);
        test.displayMatrix(resultMatrix);
    }
}
