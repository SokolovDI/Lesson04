import java.util.Random;
import java.util.Scanner;

/**
 * 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку.
 * 2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например,
 * с использованием циклов.
 * 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
 * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
 * 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */

public class TicTacToe {
    public static void main(String[] args) {
        playTicTacToe();
    }

    //variables
    public static char emptyField = '-';
    public static char signX = 'X';
    public static char signO = 'O';
    public static char signStar = '*';
    public static char signPLUS = '+';


    public static int SIZE;
    public static char[][] gameField;
    public static Random rand = new Random();

    //voids

    static void playTicTacToe() {
        initGameField();
        while (true) {
            movesignX();
            moveAsus();
//            moveMac();
//            moveAcer();
        }
    }

    static void initGameField() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите размер игрового поля (от 3 до 5): ");
        SIZE = scan.nextInt();
        if (3 > SIZE || SIZE > 5) {
            System.out.println("Введен не верный размер игрового поля");
            initGameField();
        } else {
            gameField = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    gameField[i][j] = emptyField;
                }
            }
        }
        makeGameField();
    }

    static void makeGameField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void movesignX() {
        int px;
        int py;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Введите координату X: ");
            px = scan.nextInt() - 1;
            System.out.println("Введите координату Y: ");
            py = scan.nextInt() - 1;
        } while (!isCellValid(px, py, "Введены не верные координаты\n", "\nЗанято! Введите другие координаты\n"));
        gameField[py][px] = signX;
        makeGameField();
        checkMove(signX, "МОЛОДЕЦ!");

    }

    static void moveAsus() {
        int px;
        int py;
        int horizontal;
        int hEmp;
        int vertical;
        int vEmp;
        int diagonal13 = 0;
        int diagonal13Emp = 0;
        int diagonal31 = 0;
        int diagonal31Emp = 0;

        for (int i = 0; i < SIZE; i++) {
            horizontal = 0;
            hEmp = 0;
            vertical = 0;
            vEmp = 0;
            for (int j = 0; j < SIZE; j++) {

                if (gameField[j][i] == signX) vertical++;
                else if (gameField[j][i] == emptyField) vEmp++;
                if ((vertical == SIZE - 1) && (vEmp == 1)) {
                    for (int k = 0; k < SIZE; k++) {
                        if (gameField[k][i] == emptyField) {
                            gameField[k][i] = signO;
                            makeGameField();
                            checkMove(signO, "ASUS WIN!");
                            return;
                        }
                    }
                }

                if (gameField[i][j] == signX) horizontal++;
                else if (gameField[i][j] == emptyField) hEmp++;
                if ((horizontal == SIZE - 1) && (hEmp == 1)) {
                    for (int k = 0; k < SIZE; k++) {
                        if (gameField[i][k] == emptyField) {
                            gameField[i][k] = signO;
                            makeGameField();
                            checkMove(signO, "ASUS WIN!");
                            return;
                        }
                    }
                }

            }

            if (gameField[i][i] == signX) diagonal13++;
            else if (gameField[i][i] == emptyField) diagonal13Emp++;
            if ((diagonal13 == SIZE - 1) && (diagonal13Emp == 1)) {
                for (int j = 0; j < SIZE; j++) {
                    if (gameField[j][j] == emptyField) {
                        gameField[j][j] = signO;
                        makeGameField();
                        checkMove(signO, "ASUS WIN!");
                        return;
                    }
                }
            }

            if (gameField[i][SIZE - 1 - i] == signX)
                diagonal31++;
            else if (gameField[i][SIZE - 1 - i] == emptyField)
                diagonal31Emp++;
            if ((diagonal31 == SIZE - 1) && (diagonal31Emp == 1)) {
                for (int j = 0; j < SIZE; j++) {
                    if (gameField[j][SIZE - 1 - j] == emptyField) {
                        px = j;
                        py = (SIZE - 1 - j);
                        gameField[px][py] = signO;
                        makeGameField();
                        checkMove(signO, "ASUS WIN!");
                        return;
                    }
                }
            }
        }
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        }
        while (!isCellValid(px, py, "", ""));
        gameField[px][py] = signO;
        makeGameField();
        checkMove(signO, "ASUS WIN!");
    }

    static void moveMac() {
        int px;
        int py;
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        } while (!isCellValid(px, py, "", ""));
        gameField[py][px] = signStar;
        makeGameField();
        checkMove(signStar, "Mac WIN!");
    }

    static void moveAcer() {
        int px;
        int py;
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        } while (!isCellValid(px, py, "", ""));
        gameField[py][px] = signPLUS;
        makeGameField();
        checkMove(signPLUS, "Acer WIN!");
    }

    public static boolean isCellValid(int px, int py, String wrongCoord, String occupied) {
        if (px < 0 || px >= SIZE || py < 0 || py >= SIZE) {
            System.out.print(wrongCoord);
            return false;
        }
        if (gameField[py][px] == emptyField) {
            return true;
        }
        System.out.print(occupied);
        return false;
    }

    public static void checkMove(char sign, String winMessage) {
        if (isWin(sign)) {
            System.out.println(winMessage);
            System.exit(0);
        }
        if (isDraw()) {
            System.out.println("Победила дружба!");
            System.exit(0);
        }
    }

    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameField[i][j] == emptyField) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isWin(char sign) {
        boolean vertical;
        boolean horizontal;
        int diagonal13 = 0;
        int diagonal31 = 0;
        for (int i = 0; i < SIZE; i++) {
            vertical = true;
            horizontal = true;
            if (gameField[i][i] == sign)
                diagonal13++;
            if (gameField[i][SIZE - 1 - i] == sign)
                diagonal31++;
            for (int j = 0; j < SIZE; j++) {
                horizontal &= (gameField[i][j] == sign);
                vertical &= (gameField[j][i] == sign);
            }
            if (diagonal13 == SIZE || diagonal31 == SIZE || vertical || horizontal)
                return true;
        }
        return false;
    }
}

        

