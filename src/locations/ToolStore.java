package locations;

import player.*;
import weapon.*;

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
            System.out.print("\nSeçiminiz : ");
            if (Location.input.hasNextInt()) {
                int selectCase = Location.input.nextInt();
                validInput = true;

                switch (selectCase) {
                    case 1:
                        selectWeapon();
                        break;
                    case 2:
                        printArmor();
                        break;
                    case 3:
                        System.out.println("\nBir daha bekleriz.");
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

    public void selectWeapon() {
        System.out.println("----------- Silahlar -----------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "- " + w.getName() + "\tHasar: " + w.getDamage() + "\tÜcret: " + w.getPrice());
        }
        System.out.println("4- Mağazaya dön\n");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Seçiminiz : ");
            if (input.hasNextInt()) {
                int selectWeaponId = input.nextInt();
                validInput = true;
                if (selectWeaponId == 4) {
                    this.onLocation();
                } else if (selectWeaponId > 0 && selectWeaponId < 4) {
                    Weapon selectedWeapon = Weapon.getWeaponObjID(selectWeaponId);
                    if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                        System.out.println("Yeterli paranız bulunmuyor.");
                        selectWeapon();
                    } else {
                        System.out.println(selectedWeapon.getName() + " satın aldınız.");
                        int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                        this.getPlayer().setMoney(balance);
                        System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                        this.onLocation();
                    }
                } else {
                    System.out.println("Hatalı giriş yaptınız !");
                    validInput = false;
                }

            } else {
                System.out.println("Hatalı giriş yaptınız !");
                input.next(); // Geçersiz girişi temizle
            }
        }

    }

    public void printArmor() {
        System.out.println("Zırhlar");
    }

}
