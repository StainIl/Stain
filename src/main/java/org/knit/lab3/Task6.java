package org.knit.lab3;

import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Ork Warrior", 4, 4);
        Mage mage = new Mage("Mage", 5, 5);
        Priest priest = new Priest("Healer", -5, -5);

        // Отображаем информацию о персонажах
        warrior.displayInfo();
        mage.displayInfo();
        priest.displayInfo();

        // Примеры взаимодействий
        warrior.attack(mage);  // Воин атакует мага
        priest.heal(mage);     // Священник лечит мага
        mage.castSpell(warrior);  // Маг накладывает заклинание на воина

        // Отображаем информацию о персонажах
        warrior.displayInfo();
        mage.displayInfo();
        priest.displayInfo();
    }
}

abstract class Player {
    protected String name;
    protected int currentHealth;
    protected int maxHealth;
    protected boolean isALife;
    protected int damage;
    protected int axis_X;
    protected int axis_Y;
    protected int protection;
    protected int energy;
    protected int maxEnergy;

    public void displayInfo() {
        System.out.print(name + ":");
        System.out.print(" Живой: " + (isALife ? "Да" : "Нет"));
        System.out.print("; Текущее здоровье: " + currentHealth + "/" + maxHealth);
        System.out.print("; Урон: " + damage);
        System.out.print("; Защита: " + protection);
        System.out.print("; Энергия: " + energy + "/" + maxEnergy);
        System.out.println("; Координаты: X = " + axis_X + ", Y = " + axis_Y);
    }

    protected void attack(Player player) {
        int distance = Math.abs(axis_X - player.axis_X) + Math.abs(axis_Y - player.axis_Y);
        if (distance > 5) {
            System.out.println("Цель слишком далеко!");
        } else if (energy < 20) {
            System.out.println("Недостаточно энергии");
        } else {
            System.out.println(this.name + " атакует " + player.name);
            energy -= 20;
            player.decreaseHealth(this.damage);
        }
    }

    protected void addHealth(int healPower) {
        currentHealth = Math.min(this.maxHealth, currentHealth + healPower);
        System.out.println("Текущее здоровье " + this.name + " = " + currentHealth);
    }

    protected void decreaseHealth(int value) {
        this.currentHealth -= Math.max(value - protection, 0);
        System.out.println("Текущее здоровье " + this.name + " = " + currentHealth);
        if (currentHealth <= 0) {
            isALife = false;
            System.out.println(this.name + " мёртв");
        }
    }

    protected void move(int x, int y) {
        if (x < -40 || x > 40 || y < -40 || y > 40) {
            System.out.println("Невозможно переместить " + name + ". Координаты (" + x + ", " + y + ") выходят за границы карты.");
            return;
        }

        int distance = Math.abs(axis_X - x) + Math.abs(axis_Y - y);
        if (distance > energy) {
            System.out.println("Недостаточно энергии!");
        } else {
            axis_X = x;
            axis_Y = y;
            energy -= distance;
            System.out.println(name + " передвигается на: X = " + axis_X + ", Y = " + axis_Y);
        }
    }

    protected void addEnergy() {
        energy = Math.min(maxEnergy, energy + 20);
    }
}

class Warrior extends Player {
    public Warrior(String name, int axis_X, int axis_Y) {
        this.name = name;
        this.currentHealth = 70;
        this.maxHealth = 120;
        this.isALife = true;
        this.damage = 35;
        this.axis_X = axis_X;
        this.axis_Y = axis_Y;
        this.protection = 15;
        this.energy = 50;
        this.maxEnergy = 100;
    }
}

class Mage extends Player {
    public Mage(String name, int axis_X, int axis_Y) {
        this.name = name;
        this.currentHealth = 50;
        this.maxHealth = 70;
        this.isALife = true;
        this.damage = 50;
        this.axis_X = axis_X;
        this.axis_Y = axis_Y;
        this.protection = 5;
        this.energy = 70;
        this.maxEnergy = 100;
    }

    public void castSpell(Player player) {
        int distance = Math.abs(axis_X - player.axis_X) + Math.abs(axis_Y - player.axis_Y);
        if (distance > 15) {
            System.out.println("Цель слишком далеко!");
        } else if (energy < 40) {
            System.out.println("Недостаточно энергии");
        } else {
            System.out.println(this.name + " накладывает заклинание на " + player.name);
            this.energy -= 40;
            player.decreaseHealth(50);
        }
    }
}

class Priest extends Player {
    public Priest(String name, int axis_X, int axis_Y) {
        this.name = name;
        this.currentHealth = 50;
        this.maxHealth = 90;
        this.isALife = true;
        this.damage = 15;
        this.axis_X = axis_X;
        this.axis_Y = axis_Y;
        this.protection = 10;
        this.energy = 80;
        this.maxEnergy = 100;
    }

    public void heal(Player player) {
        int distance = Math.abs(axis_X - player.axis_X) + Math.abs(axis_Y - player.axis_Y);
        if (distance > 20) {
            System.out.println("Цель слишком далеко для исцеления!");
        } else if (this.energy < 40) {
            System.out.println("Недостаточно энергии для лечения");
        } else {
            System.out.println(this.name + " лечит " + player.name);
            this.energy -= 40;
            player.addHealth(30);
        }
    }
}
