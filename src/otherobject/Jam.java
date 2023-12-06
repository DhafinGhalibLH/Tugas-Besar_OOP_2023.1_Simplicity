package otherobject;

import java.util.HashMap;
import java.awt.Rectangle;

import main.Panel;
import character.Sim;

public class Jam extends OtherObjects{
    public static int hari = 1;
    public static int menit = 0;
    public static int detik = 0;
    public int isUpgradeRumah = 0;
    public static boolean isTimerRun = true;
    public HashMap<String, Integer> beli = new HashMap<String, Integer>();

    public Jam(Panel panel, String nama, int harga, int width, int height) {
        super(panel, nama, harga, width, height);
        this.solid = new Rectangle(16, 16, width - 32, height - 32);
        this.image = this.entity.setup("/object/Jam.png", this.width, this.height);
    }

    public static void updateTime() {
        if ((menit == 3) && (detik == 59)) {
            hari++;
            menit = 0;
            detik = 0;
        } else {
            if (detik == 59) {
                menit++;
                detik = 0;
            } else {
                detik++;
            }
        }
    }

    public void aksi(Sim player) {
        
    }
}
