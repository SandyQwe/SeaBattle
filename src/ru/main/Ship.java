package ru.main;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by sadmin on 25.12.2015.
 */
public class Ship {

    int length;
    boolean isHorizontal;
    Point position = new Point(0, 0);
    String type;

    void setType() {
        switch (length) {
            default:
                type = "ERROR!";
                break;
            case (1):
                type = "Торпедный катер (1 кл.)";
                break;
            case (2):
                type = "Эсминец (2 кл.)";
                break;
            case (3):
                type = "Крейсер (3 кл.)";
                break;
            case (4):
                type = "Авианосец (4 кл.)";
                break;
        }
    }

    void deployHuman() {
        Scanner scanner = new Scanner(System.in);
        boolean rightAnswer = false;
        if (length > 1) {
            String vertical = "";
            System.out.print("Корабль будет стоять вертикально (1) или горизонтально (0)? ");
            int errorCount = 0;
            do {
                String answer = scanner.nextLine();
                if (errorCount > 4) {
                    System.out.println("Что, неужели трудно ввести правильный ответ? Попробуйте, вы же человек!");
                    errorCount = 0;
                }
                if (answer.equals("1")) {
                    isHorizontal = false;
                    rightAnswer = true;
                    vertical = "вертикально";
                } else {
                    if (answer.equals("0")) {
                        isHorizontal = true;
                        rightAnswer = true;
                        vertical = "горизонтально";
                    } else {
                        System.out.println("Вы ввели неправильный вариант! Нужно 1 или 0, попробуйте ещё раз.");
                        errorCount++;
                    }
                }
            } while (!rightAnswer);
            System.out.println("Ваш " + type + " будет размещён " + vertical);
        }
        System.out.println("Введите начальные координаты корабля в формате X Y (от 0 до 9):");
        this.position.x = scanner.nextInt();
        this.position.y = scanner.nextInt();
    }


    void deployComputer(BattleField battleField) {
        Random random = new Random();
        isHorizontal = random.nextBoolean();
        this.position.x = random.nextInt(battleField.xSizeField);
        this.position.y = random.nextInt(battleField.ySizeField);
    }

    boolean checkCoordInField(boolean isHuman, BattleField battleField) {
        if (this.position.x < 0 || this.position.x > (battleField.xSizeField - 1) || this.position.y < 0 ||
                this.position.y > (battleField.ySizeField - 1)) {
            if (isHuman) {
                System.out.println("Неверно введены координаты, попробуйте ещё раз!");
            }
            return false;
        } else {
            return true;
        }
    }

    boolean checkShipInField(boolean isHuman, BattleField playerBattleField) {
        int xBegin = this.position.x;
        int yBegin = this.position.y;
        if (isHorizontal) {
            xBegin = xBegin + length - 1;
        } else {
            yBegin = yBegin + length - 1;
        }
        if (xBegin < 0 || xBegin > (playerBattleField.xSizeField - 1) ||
                yBegin < 0 || yBegin > (playerBattleField.ySizeField - 1)) {
            if (isHuman) {
                System.out.println("Корабль выходит за пределы поля! Разместите корабль правильно!");
            }
            return false;
        } else {
            return true;
        }
    }

    boolean checkNearbyShips(boolean isHuman, BattleField playerBattleField) {
        if (checkCoordInField(isHuman, playerBattleField) && checkShipInField(isHuman, playerBattleField)) {
            if (isHorizontal) {
                for (int i = this.position.x; i < (this.position.x + length); i++) {
                    if (playerBattleField.cell[i][this.position.y].nearShip || playerBattleField.cell[i][this.position.y].hasShip) {
                        if (isHuman) {
                            System.out.println("Вы установили корабль слишком близко к другому, переустановите!");
                        }
                        return false;
                    }
                }
            } else {
                for (int i = this.position.y; i < (this.position.y + length); i++) {
                    if (playerBattleField.cell[this.position.x][i].nearShip || playerBattleField.cell[this.position.x][i].hasShip) {
                        if (isHuman) {
                            System.out.println("Вы установили корабль слишком близко к другому, переустановите!");
                        }
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    void setShip(BattleField battleField) {
        int xLength;
        int yLength;
        // установка непосредственно корабля, а также определение параметров области вокруг корабля
        if (isHorizontal) {
            xLength = length + 2;
            yLength = 3;
            for (int i = this.position.x; i < (this.position.x + length); i++) {
                //System.out.println("x :" + i + ", y :" + yBegin); //вывод координаты установки части корабля
                battleField.cell[i][this.position.y].setShip();
            }
        } else {
            xLength = 3;
            yLength = length + 2;
            for (int i = this.position.y; i < (this.position.y + length); i++) {
                //System.out.println("x :" + xPosition + ", y :" + i); //вывод координаты установки
                battleField.cell[this.position.x][i].setShip();
            }
        }
        // корректировка области вокруг корабля в зависимости от расположения корабля, проверка границ области
        // на нахождение рядом с границами поля
        if (this.position.x > 0) {
            this.position.x = this.position.x - 1;  //если не в начале поля, то граница сдвигается влево
            if (this.position.x + xLength > battleField.xSizeField) {
                xLength = xLength - 1;  //если корабль в конце поля, то уменьшить область
            }
        } else {
            xLength = xLength - 1;  //если корабль в начале поля, то также уменьшить область
        }
        if (this.position.y > 0) {
            this.position.y = this.position.y - 1;  //если не в начале поля, то граница сдвигается вверх
            if (this.position.y + yLength > battleField.ySizeField) {
                yLength = yLength - 1;  //если корабль внизу поля, то уменьшить область
            }
        } else {
            yLength = yLength - 1;  //если корабль вверху поля то также уменьгить область
        }
        //  заполнение значений nearShip
        for (int i = this.position.x; i < this.position.x + xLength; i++) {
            for (int j = this.position.y; j < this.position.y + yLength; j++) {
                if (!battleField.cell[i][j].hasShip) {
                    battleField.cell[i][j].setNearShip();
                }
            }
        }
    //    return playerBattlefield;
    }

}

