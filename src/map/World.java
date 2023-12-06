package map;

import building.Rumah;
import main.Panel;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;

public class World {

    public int width;
    public int length;
    public int[][] map;
    public ArrayList<Rumah> daftarRumah = new ArrayList<>();
    Font textJudul;
    Panel panel;
    Graphics2D graph2D;

    // matrix gimana deklarasinya dah
    // tipedata[][] variabel = new tipedata[kolom][baris]
    // konstruktor
    public World(int length, int width) {
        this.length = length;
        this.width = width;
        this.map = new int[width][length];
        // this.daftarRumah = daftarRumah;
    }

    // method
    public void expandWorld() {

    }

    public void addDaftarRumah(Rumah rumah) {
        this.daftarRumah.add(rumah);
    }

    public void showDaftarRumah() {
        final int frameX = panel.tileSize;
        final int frameY = panel.tileSize;
        final int frameWidth = panel.tileSize * 5;
        final int frameHeight = panel.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // text
        graph2D.setColor(Color.white);
        graph2D.setFont(graph2D.getFont().deriveFont(8F));
        String namaRumah;
        int textX = frameX + 350;
        int textY = frameY + panel.tileSize;
        int lineHeight = 25;
        for (int i = 0; i < daftarRumah.size(); i++) {
            // draw list of Rumah
            namaRumah = daftarRumah.get(i).getNama();
            textY += lineHeight;
            graph2D.drawString(namaRumah, textX, textY);
        }
    }

    // draw SubWindow ( mungkin ada saran mau naruh ini dimana )
    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        graph2D.setColor(color);
        graph2D.fillRoundRect(x, y, width, height, 35, 35);

        Color color2 = new Color(255, 255, 255);
        graph2D.setColor(color2);
        graph2D.setStroke(new BasicStroke(5));
        graph2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}
