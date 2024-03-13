package locations;

import player.*;
import items.*;

import java.util.Objects;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(2, player, "Dükkan", "    :\tSilah veya Zırh satın alabilirsiniz.","");
    }

    @Override
    public boolean onLocation() {
        System.out.println("----------- Mağazaya Hoşgeldiniz -----------");
        System.out.println("""
                1- Silahlar
                2- Zırhlar
                0- Çıkış Yap""");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("\nSeçiminiz : ");
            if (Location.input.hasNextInt()) {
                int selectCase = Location.input.nextInt();
                validInput = true;

                switch (selectCase) {
                    case 1:
                        buyWeapon();
                        break;
                    case 2:
                        buyArmor();
                        break;
                    case 0:
                        System.out.println("\nMağazadan çıkıldı.");
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

    public void buyWeapon() {
        System.out.println("----------- Silahlar -----------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "- " + w.getName() + "\tHasar: " + w.getDamage() + "\tÜcret: " + w.getPrice());
        }
        System.out.println("0- Mağazaya dön\n");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Seçiminiz : ");
            if (input.hasNextInt()) {
                int selectWeaponId = input.nextInt();
                validInput = true;
                if (selectWeaponId == 0) {
                    this.onLocation();
                } else if (selectWeaponId > 0 && selectWeaponId < 4) {
                    Weapon selectedWeapon = Weapon.getWeaponObjID(selectWeaponId);
                    if (Objects.requireNonNull(selectedWeapon).getPrice() > this.getPlayer().getMoney()) {
                        System.out.println("Yeterli paranız bulunmuyor.");
                        buyWeapon();
                    } else {
                        if (this.getPlayer().getInventory().getWeapon().getId()==selectWeaponId){
                            System.out.println("Zaten bu silahı kullanıyorsunuz.");
                            buyWeapon();
                        }else {
                            System.out.println(selectedWeapon.getName() + " satın aldınız.");
                            int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                            this.getPlayer().setMoney(balance);
                            System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                            System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                            this.getPlayer().getInventory().setWeapon(selectedWeapon);
                            System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                            this.onLocation();
                        }

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

    public void buyArmor() {
        System.out.println("----------- ZIRHLAR -----------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "- " + a.getName() + "\tZırh: " + a.getBlock() + "\t\tÜcret: " + a.getPrice());
        }
        System.out.println("0- Mağazaya dön\n");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Seçiminiz : ");
            if (input.hasNextInt()) {
                int selectArmorId = input.nextInt();
                validInput = true;
                if (selectArmorId == 0) {
                    this.onLocation();
                } else if (selectArmorId > 0 && selectArmorId < 4) {
                    Armor selectedArmor = Armor.getArmorObjID(selectArmorId);
                    if (Objects.requireNonNull(selectedArmor).getPrice() > this.getPlayer().getMoney()) {
                        System.out.println("Yeterli paranız bulunmuyor.");
                        buyArmor();
                    } else {
                        if (this.getPlayer().getInventory().getArmor().getId()==selectArmorId){
                            System.out.println("Zaten bu zırhı kullanıyorsunuz.");
                            buyArmor();
                        }else {
                            System.out.println(selectedArmor.getName() + " zırh satın aldınız.");
                            int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                            this.getPlayer().setMoney(balance);
                            System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                            System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                            this.getPlayer().getInventory().setArmor(selectedArmor);
                            System.out.println("Yeni zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                            this.onLocation();
                        }

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

}
