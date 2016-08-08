package ru.main;

/**
 * Created by sadmin on 25.12.2015.
 */
public class Player {
    int number;
    boolean isHuman;
    BattleField battleField;
    BattleField enemyBattleField;
    Ship[][] ships;
    Ship[][] enemyShips;

    void initBattleField() {
        battleField = new BattleField();
        battleField.init();
    }

//    void initEnemyBattleField() {   не нужно поскольку игровое поле противника является игровым полем другого игрока и на
//        enemyBattleField = new BattleField();   эту переменную просто делается ссылка
//        enemyBattleField.init();
//    }

    void setShips() {
        // получится 4 корабля по 1 клетке, 3 корабля по 2 клетки, 2 корабля по 3 клетки и 1 корабль по 4 клетки
        ships = new Ship[4][4];
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < (4 - i); j++) {
                ships[i][j] = new Ship();
                ships[i][j].length = i + 1;
                ships[i][j].setType();
                if (isHuman) {
                    do {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Разместите ваш " + (j + 1) + "-й " + ships[i][j].type + ".");
                        ships[i][j].deployHuman();
                        System.out.println("-----------------------------------------------------");
                        System.out.println();
                    } while (!ships[i][j].checkNearbyShips(isHuman, battleField));
                } else {
                    do {
                        ships[i][j].deployComputer(battleField);
                    } while (!ships[i][j].checkNearbyShips(isHuman, battleField));
                }
                //battleField =
                ships[i][j].setShip(battleField);
                //System.out.println(ships[i][j].xPosition + "   " + ships[i][j].yPosition); //вывод координат установки корабля для проверки
                battleField.initialOut();
                System.out.println("-----------------------------------------------");
            }
        }

    }
}
