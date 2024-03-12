package locations;

import player.*;
public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(2, player, "Mağaza    ", "Silah veya Zırh satın alabilirsiniz.");
    }

    @Override
    public boolean onLocation() {
        System.out.println("----------- Mağazaya Hoşgeldiniz -----------");
        System.out.println("""
                1- Silahlar
                2- Zırhlar
                3- Çıkış Yap""");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Seçiminiz : ");
            if (Location.input.hasNextInt()) {
                int selectCase = Location.input.nextInt();
                validInput = true;

                switch (selectCase) {
                    case 1:
                        printWeapon();
                        break;
                    case 2:
                        printArmor();
                        break;
                    case 3:
                        System.out.println("\n Bir daha bekleriz.");
                        return true;
                    default:
                        validInput = false;
                        System.out.println("Hatalı giriş !");
                }

            } else {
                System.out.println("Hatalı giriş !");
                Location.input.next(); // Geçersiz girişi temizle
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("Silahlar");
    }

    public void printArmor() {
        System.out.println("Zırhlar");
    }

}
