import player.*;
import locations.*;

import java.util.Scanner;

public class Game {
    private final Scanner input = new Scanner(System.in);
    Player player = new Player(playerName());
    Location location = null;
    Location[] locationList = {new SafeHouse(player), new ToolStore(player), new Cave(player), new Forest(player), new River(player)};

    public void start() {
        player.selectChar();

        while (true) {
            player.printInfo();
            this.selectLocation();

            if (!location.onLocation()) {
                System.out.println("\n############### GAME OVER ###############\n");
                break;
            }
        }
    }

    private String playerName() {
        System.out.println("\nMacera Oyununa Hoş geldiniz.");
        System.out.print("Lütfen bir isim giriniz : ");
        String name = input.nextLine();
        System.out.println("\nSayın " + name + " bu karanlık ve sisli adaya hoşgeldiniz.");
        return name;
    }

    private void selectLocation() {
        System.out.println("############### BÖLGELER ###############");
        for (Location printLocation : locationList) {
            System.out.println(printLocation.getId() +
                    "-\t" + printLocation.getName() +
                     printLocation.getExplanation()+
                    "\t"+printLocation.getAward());
        }
        System.out.println();

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Lütfen gitmek istediğiniz bölgenin numarasını giriniz : ");
            if (input.hasNextInt()) {
                int selectLoc = input.nextInt();
                validInput = true;

                switch (selectLoc) {
                    case 1:
                        location = locationList[0];
                        break;
                    case 2:
                        location = locationList[1];
                        break;
                    case 3:
                        location = locationList[2];
                        break;
                    case 4:
                        location = locationList[3];
                        break;
                    case 5:
                        location = locationList[4];
                        break;
                    default:
                        System.out.println("Hatalı bir bölge numarası girdiniz !");
                        validInput = false;
                }
            } else {
                System.out.println("Hatalı bir bölge numarası girdiniz !");
                input.next(); // Geçersiz girişi temizle
            }
        }
    }

}
