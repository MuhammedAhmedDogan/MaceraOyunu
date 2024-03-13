package player;

import gameChar.*;
import items.*;

import java.util.Scanner;

public class Player {
    private int damage, health, money;
    private String name, charName;
    private Inventory inventory;
    private final Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }


    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Lütfen bir karakter seçiniz.");
        System.out.println("--------------------------------------");
        System.out.println("Karakterler :");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getId() +
                    "\tKarakter: " + gameChar.getName() +
                    "\tHasar: " + gameChar.getDamage() +
                    "\tSağlık: " + gameChar.getHealth() +
                    "\tPara: " + gameChar.getMoney());
        }
        System.out.println("--------------------------------------");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Karakter ID'si giriniz : ");
            if (input.hasNextInt()) {
                int selectChar = input.nextInt();
                validInput = true;

                switch (selectChar) {
                    case 1:
                        initPlayer(new Samurai());
                        break;
                    case 2:
                        initPlayer(new Archer());
                        break;
                    case 3:
                        initPlayer(new Knight());
                        break;
                    default:
                        System.out.println("Hatalı bir ID girdiniz !");
                        validInput = false;
                }

            } else {
                System.out.println("Hatalı bir ID girdiniz !");
                input.next(); // Geçersiz girişi temizle
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("Seçilen Karakter : " + this.getCharName() +
                "\t Hasar: " + this.getDamage() +
                "\t Sağlık: " + this.getHealth() +
                "\t Para: " + this.getMoney());
        System.out.println("--------------------------------------");
    }


    private void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo() {
        System.out.println("\nSilah: " + this.getInventory().getWeapon().getName() +
                "\tZırh: "+this.getInventory().getArmor().getName()+
                "\t\tHasar: " + this.getDamage() +
                "\t  Bloklama: "+this.getInventory().getArmor().getBlock()+
                "\t\tSağlık: " + this.getHealth() +
                "\t\tPara: " + this.getMoney() + "\n");
    }

    public int getDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
