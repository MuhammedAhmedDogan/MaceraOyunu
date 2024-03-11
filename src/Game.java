import java.util.Scanner;

public class Game {
    private final Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoş geldiniz.");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz.");
        System.out.println("Lütfen bir karakter seçiniz.");
        player.selectChar();
    }
}
