package ru.main;

import java.util.Scanner;

/**
 * Created by sadmin on 25.12.2015.
 */
public class Game {

    boolean finished = false;

    public void init() {
        Player[] player;
        player = new Player[2];
        for (int i = 0; i < 2; i++) {
            player[i] = new Player();
            player[i].number = i + 1;
            System.out.print("Игрок " + player[i].number + " это человек (введите yes, y, 1) или компьютер (введите no, n, 0)? :");
            Scanner scanner = new Scanner(System.in);
            boolean rightAnswer = false;
            int errorCount = 0;
            do {
                String isHuman = scanner.nextLine();
                isHuman = isHuman.toLowerCase();
                if (isHuman.equals("yes") || isHuman.equals("y") || isHuman.equals("1")) {
                    player[i].isHuman = true;
                    rightAnswer = true;
                } else {
                    if (isHuman.equals("no") || isHuman.equals("n") || isHuman.equals("0")) {
                        player[i].isHuman = false;
                        rightAnswer = true;
                    } else {
                        if (errorCount < 5) {
                            System.out.println("Неверно введено значение! Попробуйте ещё раз.");
                            errorCount++;
                        } else {
                            System.out.println("Сударь, вы олень! За игрока " + player[i].number + " будет играть компьютер");
                            player[i].isHuman = false;
                            rightAnswer = true;
                        }
                    }
                }
            } while (!rightAnswer);
            player[i].initBattleField();
//            player[i].initEnemyBattleField(); не нужно поскольку эотому объекту потом присваивается ссылка на существующиее игровое поле
            player[i].setShips();
            // TODO: 26.12.2015 запилить итоговый вывод игрового поля каждого игрока
        }
        for (int i = 0; i < 2; i++) {
            player[i].enemyBattleField = player[1 - i].battleField;
            player[i].enemyShips = player[1-i].ships;
        }
    }

    public void play() {
        System.out.println("Let's play!");
        finished = true;
        // TODO: 26.12.2015 запилить игровой процесс
    }
}
