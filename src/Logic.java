public class Logic {
    private int originalSize;
    private boolean firstPass = true;
    private int[][][] splitMatrixResult;
    private int[][][] pointsMatrixResult;
    private int[][][] resultantMatrixResult;

    //Multiply the matrix recursively by dividing into 4 quadrants
    public int[][] multiplyMatrix(int[][] matrixA, int[][] matrixB) {
        //Store matrix length and create a matrix to store results
        int matrixSize = matrixA.length;
        int[][] resultMatrix = new int[matrixSize][matrixSize];

        //Base Case
        if (matrixSize == 1) {
            resultMatrix[0][0] = matrixA[0][0] * matrixB[0][0];
        } else {
            //Matrices for matrix A when divided into 4
            int[][] matrixAQ1 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixAQ2 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixAQ3 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixAQ4 = new int[matrixSize / 2][matrixSize / 2];

            //Matrices for matrix B when divided into 4
            int[][] matrixBQ1 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixBQ2 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixBQ3 = new int[matrixSize / 2][matrixSize / 2];
            int[][] matrixBQ4 = new int[matrixSize / 2][matrixSize / 2];

            //Start dividing the matrix into 4
            //Matrix A
            //A
            splitMatrix(matrixA, matrixAQ2, 0, 0);
            //B
            splitMatrix(matrixA, matrixAQ1, 0, matrixSize/2);
            //C
            splitMatrix(matrixA, matrixAQ3, matrixSize/2, 0);
            //D
            splitMatrix(matrixA, matrixAQ4, matrixSize/2, matrixSize/2);

            //Matrix B
            //E
            splitMatrix(matrixB, matrixBQ2, 0, 0);
            //F
            splitMatrix(matrixB, matrixBQ1, 0, matrixSize/2);
            //G
            splitMatrix(matrixB, matrixBQ3, matrixSize/2, 0);
            //H
            splitMatrix(matrixB, matrixBQ4, matrixSize/2, matrixSize/2);

            //Store the first matrix to be split in the 3d matrix
            if (firstPass) {
                //Get the original size for other conditions
                originalSize = matrixA.length;
                //Split the 2 matrices into 8 parts
                int numberOfSplits = 8;

                splitMatrixResult = new int[numberOfSplits][matrixSize/2][matrixSize/2];

                //Store the split matrices into the 3d matrix for display later on
                for (int splitProcessCounter = 0; splitProcessCounter < numberOfSplits; ++splitProcessCounter ) {
                    for (int rowCounter = 0; rowCounter < splitMatrixResult[splitProcessCounter].length; ++rowCounter) {
                        for (int colCounter = 0; colCounter < splitMatrixResult[splitProcessCounter][rowCounter].length; ++colCounter) {
                            switch (splitProcessCounter) {
                                case 0 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixAQ1[rowCounter][colCounter];
                                case 1 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixAQ2[rowCounter][colCounter];
                                case 2 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixAQ3[rowCounter][colCounter];
                                case 3 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixAQ4[rowCounter][colCounter];
                                case 4 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixBQ1[rowCounter][colCounter];
                                case 5 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixBQ2[rowCounter][colCounter];
                                case 6 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixBQ3[rowCounter][colCounter];
                                case 7 -> splitMatrixResult[splitProcessCounter][rowCounter][colCounter] = matrixBQ4[rowCounter][colCounter];
                                default -> System.out.println("Error");
                            }
                        }
                    }
                }

                firstPass = false;
            }

            //Calculate the 7 points
            /*
             * Formulas
             * P1 = A * (F - H)
             * P2 = (A + B) * H
             * p3 = (C + D) * E
             * P4 = D * (G - E)
             * P5 = (A + D) * (E + H)
             * P6 = (B - D) * (G + H)
             * P7 = (A - C) * (E + F)
             *  A = matrixAQ2
             *  B = matrixAQ1
             *  C = matrixAQ3
             *  D = matrixAQ4
             *  E = matrixBQ2
             *  F = matrixBQ1
             *  G = matrixBQ3
             *  H = matrixBQ4
             * */

            int[][] P1 = multiplyMatrix(matrixAQ2, subtractMatrix(matrixBQ1, matrixBQ4));
            int[][] P2 = multiplyMatrix(addMatrix(matrixAQ2, matrixAQ1), matrixBQ4);
            int[][] P3 = multiplyMatrix(addMatrix(matrixAQ3, matrixAQ4), matrixBQ2);
            int[][] P4 = multiplyMatrix(matrixAQ4, subtractMatrix(matrixBQ3, matrixBQ2));
            int[][] P5 = multiplyMatrix(addMatrix(matrixAQ2, matrixAQ4), addMatrix(matrixBQ2, matrixBQ4));
            int[][] P6 = multiplyMatrix(subtractMatrix(matrixAQ1, matrixAQ4), addMatrix(matrixBQ3, matrixBQ4));
            int[][] P7 = multiplyMatrix(subtractMatrix(matrixAQ2,matrixAQ3), addMatrix(matrixBQ2,matrixBQ1));

            if (P1.length == originalSize / 2) {
                int numberOfPoints = 7;

                pointsMatrixResult = new int[numberOfPoints][originalSize / 2][originalSize / 2];

                //Store the split matrices into the 3d matrix for display later on
                for (int pointsProcessCounter = 0; pointsProcessCounter < numberOfPoints; ++pointsProcessCounter ) {
                    for (int rowCounter = 0; rowCounter < pointsMatrixResult[pointsProcessCounter].length; ++rowCounter) {
                        for (int colCounter = 0; colCounter < pointsMatrixResult[pointsProcessCounter][rowCounter].length; ++colCounter) {
                            switch (pointsProcessCounter) {
                                case 0 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P1[rowCounter][colCounter];
                                case 1 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P2[rowCounter][colCounter];
                                case 2 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P3[rowCounter][colCounter];
                                case 3 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P4[rowCounter][colCounter];
                                case 4 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P5[rowCounter][colCounter];
                                case 5 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P6[rowCounter][colCounter];
                                case 6 -> pointsMatrixResult[pointsProcessCounter][rowCounter][colCounter] = P7[rowCounter][colCounter];
                                default -> System.out.println("Error");
                            }
                        }
                    }
                }
            }

//            displayMatrix(P1);
//            System.out.println();
//            displayMatrix(P2);
//            System.out.println();
//            displayMatrix(P3);
//            System.out.println();
//            displayMatrix(P4);
//            System.out.println();
//            displayMatrix(P5);
//            System.out.println();
//            displayMatrix(P6);
//            System.out.println();
//            displayMatrix(P7);
//            System.out.println();

            //Calculate the resultant matrix
            /*
             * Formulas
             * Q1 = P1 + P2
             * Q2 = P5 + P4 - P2 + P6
             * Q3 = P3 + P4
             * Q4 = P1 + P5 - P3 - P7
             * */

            int[][] matrixResultQ1 = addMatrix(P1, P2);
            int[][] matrixResultQ2 = addMatrix(subtractMatrix(addMatrix(P5, P4), P2), P6);
            int[][] matrixResultQ3 = addMatrix(P3, P4);
            int[][] matrixResultQ4 = subtractMatrix(subtractMatrix(addMatrix(P1, P5), P3), P7);

            if (matrixResultQ1.length == originalSize / 2) {
                int numberOfQuadrants = 4;

                resultantMatrixResult = new int[numberOfQuadrants][originalSize / 2][originalSize / 2];

                //Store the split matrices into the 3d matrix for display later on
                for (int resultantProcessCounter = 0; resultantProcessCounter < numberOfQuadrants; ++resultantProcessCounter ) {
                    for (int rowCounter = 0; rowCounter < resultantMatrixResult[resultantProcessCounter].length; ++rowCounter) {
                        for (int colCounter = 0; colCounter < resultantMatrixResult[resultantProcessCounter][rowCounter].length; ++colCounter) {
                            switch (resultantProcessCounter) {
                                case 0 -> resultantMatrixResult[resultantProcessCounter][rowCounter][colCounter] = matrixResultQ1[rowCounter][colCounter];
                                case 1 -> resultantMatrixResult[resultantProcessCounter][rowCounter][colCounter] = matrixResultQ2[rowCounter][colCounter];
                                case 2 -> resultantMatrixResult[resultantProcessCounter][rowCounter][colCounter] = matrixResultQ3[rowCounter][colCounter];
                                case 3 -> resultantMatrixResult[resultantProcessCounter][rowCounter][colCounter] = matrixResultQ4[rowCounter][colCounter];
                                default -> System.out.println("Error");
                            }
                        }
                    }
                }
            }

            //Start combining matrices from four quadrants into 1 big matrix
            joinMatrix(resultMatrix, matrixResultQ2, 0, 0);
            joinMatrix(resultMatrix, matrixResultQ1, 0, matrixSize/2);
            joinMatrix(resultMatrix, matrixResultQ3, matrixSize/2, 0);
            joinMatrix(resultMatrix, matrixResultQ4, matrixSize/2, matrixSize/2);
        }
        System.out.println("Test Multiply method");
        return resultMatrix;
    }

    //Divides the matrix into 4 quadrants
    public void splitMatrix(int[][] divideMatrix, int[][] storeResultMatrix, int rowStart, int colStart) {
        for (int rowCounterStart = 0, rowCounterEnd = rowStart; rowCounterStart < storeResultMatrix.length; ++rowCounterStart, ++rowCounterEnd) {
            for (int colCounterStart = 0, colCounterEnd = colStart; colCounterStart < storeResultMatrix.length; ++colCounterStart, ++colCounterEnd) {
                storeResultMatrix[rowCounterStart][colCounterStart] = divideMatrix[rowCounterEnd][colCounterEnd];
            }
        }
    }

    //Joins the 4 quadrants into 1 matrix
    public void joinMatrix(int[][] divideMatrix, int[][] storeResultMatrix, int rowStart, int colStart) {
        for (int rowCounterStart = 0, rowCounterEnd = rowStart; rowCounterStart < storeResultMatrix.length; ++rowCounterStart, ++rowCounterEnd) {
            for (int colCounterStart = 0, colCounterEnd = colStart; colCounterStart < storeResultMatrix.length; ++colCounterStart, ++colCounterEnd) {
                divideMatrix[rowCounterEnd][colCounterEnd] = storeResultMatrix[rowCounterStart][colCounterStart];
            }
        }
    }

    //Adds 2 matrix
    public int[][] addMatrix(int[][] matrixA, int[][] matrixB) {
        //Initialize a matrix to store the results with the size of matrix A
        int[][] resultMatrix = new int[matrixA.length][matrixA.length];

        //Traverse through the matrix and add them together
        for (int rowCounter = 0; rowCounter < matrixA.length; ++rowCounter) {
            for (int colCounter = 0; colCounter < matrixA[rowCounter].length; ++colCounter) {
                resultMatrix[rowCounter][colCounter] = matrixA[rowCounter][colCounter] + matrixB[rowCounter][colCounter];
            }
        }

        return  resultMatrix;
    }

    //Subtracts 2 matrix
    public int[][] subtractMatrix(int[][] matrixA, int[][] matrixB) {
        //Initialize a matrix to store the results with the size of matrix A
        int[][] resultMatrix = new int[matrixA.length][matrixA.length];

        //Traverse through the matrix and subtract them together
        for (int rowCounter = 0; rowCounter < matrixA.length; ++rowCounter) {
            for (int colCounter = 0; colCounter < matrixA[rowCounter].length; ++colCounter) {
                resultMatrix[rowCounter][colCounter] = matrixA[rowCounter][colCounter] - matrixB[rowCounter][colCounter];
            }
        }

        return resultMatrix;
    }

    //Display 2D Matrix
    public void displayMatrix(int[][] outGivenMatrix) {
        for (int[] rowValues : outGivenMatrix) {
            for (int colValues :  rowValues) {
                System.out.print(colValues + " ");
            }
            System.out.println();
        }
    }

    //Display 3d Matrix
    public void display3DMatrix() {
        for (int[][] array2D: resultantMatrixResult) {
            for (int[] array1D: array2D) {
                for(int arrayValues: array1D) {
                    System.out.print(arrayValues + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int[][][] getSplitMatrixResult() {
        return splitMatrixResult;
    }

    public int[][][] getPointsMatrixResult() {
        return pointsMatrixResult;
    }

    public int[][][] getResultantMatrixResult() {
        return resultantMatrixResult;
    }
}
