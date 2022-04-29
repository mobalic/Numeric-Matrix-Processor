package processor;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. InverseMatrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    addMatrices();
                    break;
                }
                case 2: {
                    addMultiplyMatrixByAConstant();
                    break;
                }
                case 3: {
                    multiplyMatrices();
                    break;
                }
                case 4: {
                    transposeMatrix();
                    break;
                }
                case 5: {
                    calculateADeterminant();
                    break;
                }
                case 6: {
                    inverseMatrix();
                    break;

                }
                case 0: {
                    System.exit(0);
                    break;
                }
            }
        }

    }

    private static void inverseMatrix() {
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        double[][] matrix = new double[row][col];
        System.out.println("Enter matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        Determinant det = new Determinant(row, matrix);

        if (row == col) {
            if (det.getDeterminant() != 0) {
                double[][] adj = new double[row][row];
                var adjMatrix = det.adj(matrix, adj);


                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        System.out.print(adjMatrix[i][j] * (1 / det.getDeterminant()) + " ");
                    }
                    System.out.println();
                }


            } else {
                System.out.println("This matrix doesn't have an inverse.");
            }


        } else {
            System.out.println("The operation cannot be performed.");
        }
        System.out.println();

    }

    private static void calculateADeterminant() {
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        double[][] matrix = new double[row][col];
        System.out.println("Enter matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        if (row == col) {
            Determinant det = new Determinant(row, matrix);
            det.display();
        } else {
            System.out.println("The operation cannot be performed.");
            System.out.println();
        }


    }

    private static void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        System.out.print("Enter matrix size: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        double[][] matrix = new double[row][col];
        System.out.println("Enter matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        switch (choice) {
            case 1: {
                print(mainDiagonal(matrix));
                break;
            }
            case 2: {
                print(sideDiagonal(matrix));
                break;
            }
            case 3: {
                print(verticalLine(matrix));
                break;
            }
            case 4: {
                print(horizontalLine(matrix));
                break;
            }
        }
    }

    private static double[][] verticalLine(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
        return matrix;
    }

    private static double[][] horizontalLine(double[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = temp;
            }
        }
        return matrix;
    }

    private static double[][] sideDiagonal(double[][] matrix) {
        return mainDiagonal(verticalLine(horizontalLine(matrix)));
    }

    private static double[][] mainDiagonal(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] newMatrix = new double[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static void print(double[][] matrix) {
        System.out.println("The result is: ");
        for (double[] doubles : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(doubles[j] + " ");

            }
            System.out.println();
        }
    }


    private static void multiplyMatrices() {
        System.out.print("Enter size of first matrix: ");
        int row1 = scanner.nextInt();
        int col1 = scanner.nextInt();
        double[][] firstMatrix = new double[row1][col1];
        System.out.println("Enter first matrix: ");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                firstMatrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.print("Enter size of second matrix: ");
        int row2 = scanner.nextInt();
        int col2 = scanner.nextInt();
        double[][] secondMatrix = new double[row2][col2];
        System.out.println("Enter second matrix: ");
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                secondMatrix[i][j] = scanner.nextDouble();
            }
        }

        double[][] result = new double[row1][col2];
        double sum = 0;

        if (col1 == row2) {
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    for (int k = 0; k < row2; k++) {
                        sum += firstMatrix[i][k] * secondMatrix[k][j];
                    }
                    result[i][j] = sum;
                    sum = 0;
                }
            }

            System.out.println("The result is: ");
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

        } else {
            System.out.println("The operation cannot be performed.");

        }


    }

    private static void addMultiplyMatrixByAConstant() {
        System.out.print("Enter size of matrix: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        double[][] matrix = new double[row][col];
        System.out.println("Enter matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();
        System.out.println("The result is: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] * constant + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void addMatrices() {

        System.out.print("Enter size of first matrix: ");
        int row1 = scanner.nextInt();
        int col1 = scanner.nextInt();
        double[][] firstMatrix = new double[row1][col1];
        System.out.println("Enter first matrix: ");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                firstMatrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.print("Enter size of second matrix: ");
        int row2 = scanner.nextInt();
        int col2 = scanner.nextInt();
        double[][] secondMatrix = new double[row2][col2];
        System.out.println("Enter second matrix: ");
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                secondMatrix[i][j] = scanner.nextDouble();
            }
        }

        if (row1 == row2 && col1 == col2) {
            System.out.println("The result is:");
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col1; j++) {
                    System.out.print(firstMatrix[i][j] + secondMatrix[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            System.out.println("The operation cannot be performed.");
        }
        System.out.println();
    }
}
