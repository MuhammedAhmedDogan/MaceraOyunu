import java.util.Scanner;

public class Player {
    private int damage, health, money;
    private String name, charName;
    Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }


    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("--------------------------------------");
        System.out.println("Karakterler :");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getId() +
                    "\tKarakter: " + gameChar.getName() +
                    "\t Hasar: " + gameChar.getDamage() +
                    "\t Sağlık: " + gameChar.getHealth() +
                    "\t Para: " + gameChar.getMoney());
        }
        System.out.println("--------------------------------------");

        boolean isWrongID;
        do {
            System.out.print("Karakter ID'si giriniz : ");
            int selectChar = input.nextInt();
            isWrongID = false;
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
                    isWrongID = true;
            }
        } while (isWrongID);
        System.out.println("Seçilen Karakter : " + this.getCharName() +
                "\t Hasar: " + this.getDamage() +
                "\t Sağlık: " + this.getHealth() +
                "\t Para: " + this.getMoney());
    }

    public void selectLoc(){
        
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public int getDamage() {
        return damage;
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


}
