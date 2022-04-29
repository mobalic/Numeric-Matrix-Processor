package processor;

public class Determinant {
   private static int N;
    double[][] matrix;

    public Determinant(int n, double[][] matrix) {
        N = n;
        this.matrix = matrix;
    }


    public static void getCofactor(double[][] mat, double[][] temp, double p, double q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }

        }
    }

    public static double determinantOfMatrix(double[][] mat, int n) {
        double D = 0; // Initialize result

        if (n == 1) {
            return mat[0][0];
        }
        double[][] temp = new double[N][N];
        double sign = 1;
        for (int f = 0; f < n; f++) {
            getCofactor(mat, temp, 0, f, n);

            D += sign * mat[0][f] * determinantOfMatrix(temp, n - 1);
            sign = -sign;
        }


        return D;
    }

    public double[][] adj(double[][] A, double[][] adj) {
        if (N == 1) {
            adj[0][0] = 1;
        } else {
            double sign;
            double[][] temp = new double[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    getCofactor(A, temp, i, j, N);

                    sign = ((i + j) % 2 == 0) ? 1 : -1;

                    adj[j][i] = (sign) * (determinantOfMatrix(temp, N - 1));
                }
            }
        }
        return adj;
    }


    public double getDeterminant() {
        return determinantOfMatrix(matrix, N);
    }

    public void display() {
        System.out.println("The result is: ");
        System.out.println(determinantOfMatrix(matrix, N));
    }

}

