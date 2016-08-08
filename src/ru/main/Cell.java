package ru.main;

/**
 * Created by Александр on 10.12.2015.
 */
public class Cell {
    //     переменные
    Point coord = new Point(0, 0); //координаты X, Y
    boolean isShoot;    //стреляли по полю или нет
    boolean hasShip;    //есть ли в ячейке корабль
    boolean nearShip;   //есть ли корабль в соседней ячейке
    char symbol;        //символ вывода ячейки в зависимости от её состояния (есть корабль, стрелили в ячейку, подбили корабль)

    //    методы
    void shoot() {
        isShoot = true;
        System.out.print("Вы выстрелили по клетке " + coord.x + "." + coord.y + " и ");
        if (hasShip) {
            System.out.println("попали во вражеский корабль!");
            symbol = '\u2739';
        } else {
            System.out.println("промахнулись!");
            symbol = '\u2609';
        }
    }

    void setShip() {
        hasShip = true;
        symbol = '\u2B1C';
    }

    void setNearShip() {
        nearShip = true;
        symbol = '\u1424';
    }

    void fill(int x, int y) {
        this.coord.x = x;
        this.coord.y = y;
        isShoot = false;
        hasShip = false;
        nearShip = false;
        symbol = '\u0387';
    }
}