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

    public static int px;
    public static int py;

    public static int SIZE;
    public static char[][] gameField;

    public static Random rand = new Random();

    //voids

    static void playTicTacToe() {
        initGameField();
        while (true) {
            movePlayer();
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

    static void movePlayer() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Введите координату X: ");
            px = scan.nextInt() - 1;
            System.out.println("Введите координату Y: ");
            py = scan.nextInt() - 1;
        } while (!isCellValid(px, py));
        gameField[py][px] = signX;
        makeGameField();
        checkMove(signX, "МОЛОДЕЦ!");

    }

    static void moveAsus() {
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        } while (!isCellValidAsus(px, py));
        gameField[py][px] = signO;
        makeGameField();
        checkMove(signO, "ASUS WIN!");
    }

    static void moveMac() {
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        } while (!isCellValidAsus(px, py));
        gameField[py][px] = signStar;
        makeGameField();
        checkMove(signStar, "Mac WIN!");
    }

    static void moveAcer() {
        do {
            px = rand.nextInt(SIZE);
            py = rand.nextInt(SIZE);
        } while (!isCellValidAsus(px, py));
        gameField[py][px] = signPLUS;
        makeGameField();
        checkMove(signPLUS, "Acer WIN!");
    }

    public static boolean isCellValid(int px, int py) {
        if (px < 0 || px >= SIZE || py < 0 || py >= SIZE) {
            System.out.println("Введены не верные координаты. Введите другие координаты");
            return false;
        }
        if (gameField[py][px] == emptyField) {
            return true;
        }
        System.out.println("Занято. Введите другие координаты");
        return false;
    }

    public static boolean isCellValidAsus(int px, int py) {
        if (px < 0 || px >= SIZE || py < 0 || py >= SIZE) {
            return false;
        }
        if (gameField[py][px] == emptyField) {
            return true;
        }
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


