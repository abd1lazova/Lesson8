package com.company.games;

import com.company.players.*;

public class RPG_Game {
    public static void start() {
        System.out.println("Game started!");
        Boss boss = new Boss(5000, 50, "Босс");
        Warrior warrior = new Warrior(250, 20, "Воин");
        Magic magic = new Magic(240, 15, "Магический");
        Witcher witcher = new Witcher(300,0,"Ведьмак");
        Thor thor = new Thor(250,20,"Тор");
        Tank tank = new Tank(300,5,"Танк");
        Golem golem = new Golem(400,100,"голем");
        Hero[] heroes = {warrior,magic,witcher,thor,tank,golem};

        printStatistics(boss, heroes);

        while (!isGameFinished(boss, heroes)) {
            round(boss, heroes);
        }
    }

    private static void round(Boss boss, Hero[] heroes) {
        heroesApplySuperPower(boss, heroes);
        heroesHit(boss, heroes);
        if (boss.getHealth() > 0) {
            bossHits(boss, heroes);
        }

        printStatistics(boss, heroes);
    }
    private static void bossHits(Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                heroes[i].setHealth(heroes[i].getHealth() - boss.getDamage());
            }
        }
    }

    private static void heroesHit(Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0 && boss.getHealth() > 0) {
                boss.setHealth(boss.getHealth() - heroes[i].getDamage());
            }

        }
    }

    private static void heroesApplySuperPower(Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0 && boss.getHealth() > 0) {
                heroes[i].applySuperPower(boss, heroes);
            }
        }
    }

    private static boolean isGameFinished(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("____________");
        System.out.println(boss.getName() + " health: " + boss.getHealth());
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(heroes[i].getName()
                    + " health: " + heroes[i].getHealth());
        }
        System.out.println("____________");
    }
}

