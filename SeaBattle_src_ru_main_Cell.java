package ru.main;

/**
 * Created by Александр on 10.12.2015.
 */
public class Cell {
//     переменные
    byte xCoord;    //координата Х, по горизонтали
    byte yCoord;    //координата Y, по вертикали
    boolean isShoot;    //стреляли по полю или нет
    boolean hasShip;    //есть ли в ячейке корабль
    boolean nearShip;   //есть ли корабль в соседней ячейке

//    методы
    void shoot() {
        isShoot = true;
        System.out.print("Вы выстрелили по клетке " + xCoord + "." + yCoord + " и ");
        if (hasShip) {
            System.out.println("попали во вражеский корабль!");
        }
        else {
            System.out.println("промахнулись!");
        }
    }

    void setShip() {
        if (hasShip) {
            System.out.println("В ячейке уже есть корабль");
        }
        else {
            hasShip = true;
            System.out.println("Корабль установлен!");
        }
    }
}
