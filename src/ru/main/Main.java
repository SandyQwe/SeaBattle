package ru.main;

/**
 * Created by sadmin on 25.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        System.out.println("Начинаем игру!" + '\n');

        do {
            game.play();
        } while (!game.finished);
    }
}
