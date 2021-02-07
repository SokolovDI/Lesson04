import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        initGameField();

        while (true) {
            movePlayer();
        }
    }
    /**
     * 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку.
     * 2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например,
     * с использованием циклов.
     * 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
     * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
     * 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
     */
    //variables
    public static char emptyField = '-';
    public static char moveX = 'X';

    public static int px;
    public static int py;

    public static int SIZE;

    public static char[][] gameField;
    public static Scanner scan = new Scanner(System.in);

    //voids
    static void initGameField() {
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
        do {
            System.out.println("Введите координату X: ");
            px = scan.nextInt() - 1;
            System.out.println("Введите координату Y: ");
            py = scan.nextInt() - 1;
        } while (!isCellValid(px, py));
        gameField[py][px] = moveX;
        makeGameField();
    }

    static void moveAsus() {

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
}
