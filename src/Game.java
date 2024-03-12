import java.util.Scanner;

public class Game {
    private final Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("\nMacera Oyununa Hoş geldiniz.");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("\nSayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz.");
        System.out.println("Lütfen bir karakter seçiniz.");
        player.selectChar();

        Location location = null;
        Location[] locationList = {new SafeHouse(player), new ToolStore(player)};
        while (true) {
            System.out.println("\n############### BÖLGELER ###############\n");
            for (Location locationPrint : locationList) {
                System.out.println(locationPrint.getId() +
                        "-\t" + locationPrint.getName() +
                        " :\t" + locationPrint.getExplanation());
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
                        default:
                            System.out.println("Hatalı bir bölge numarası girdiniz !");
                            validInput = false;
                    }
                } else {
                    System.out.println("Hatalı bir bölge numarası girdiniz !");
                    input.next(); // Geçersiz girişi temizle
                }
            }

            System.out.println();
            if (!location.onLocation()){
                System.out.println("\n############### GAME OVER ###############\n");
                break;
            }
        }
    }
}
