package building;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import main.EntityControl;
import main.Panel;
import main.Controller;

public class Rumah {
    private String nama;
    private int absis;
    private int ordinat;
    public int screenAbs;
    public int screenOrd;
    private ArrayList<Ruangan> listRuangan;

    private Graphics2D g;
    private Panel panel;
    private BufferedImage image;
    public EntityControl entityControl = new EntityControl(this.panel);

    public final int defTileSize = 16;
    public final int scale = 3;
    public final int tileSize = defTileSize * scale;
    public Controller controlKey;
    public boolean collision = false;
    public boolean isGeser = false;

    // Konstruktor
    public Rumah(Panel panel, String nama, Controller controlKey) {
        this.nama = nama;
        this.absis = panel.tileSize * 40;
        this.ordinat = panel.tileSize * 24;
        // this.listRuangan.add(ruangan);
        entityControl = new EntityControl(this.panel);
        this.image = entityControl.setup("/object/Home_1.png", panel.tileSize * 3, panel.tileSize * 3);
    }

    // Getter
    public String getNama() {
        return this.nama;
    }

    public int getAbsis() {
        return this.absis; // mengambil titik absis dari rumah
    }

    public int getOrdinat() {
        return this.ordinat; // mengambil titik ordinat dari rumah
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama; // inisiasi nama rumah
    }

    // Method
    public void showLokasi() {

    }

    public void upgradeRumah() {

        /*
         * 1. minta input mau ruangan mana yang dijadiin acuan
         * 2. cari dari arraylist ruangan nama nya ada/engga, gaada minta input lagi.
         * 3. tambahin ruangan di atas/bawah/kiri/kanan
         */

        // ngebuat window buat nunjukin menu nya
        int x = tileSize * 2;
        int y = tileSize / 2;
        int width = (tileSize * 20) - (tileSize * 4);
        int height = tileSize * 5;

        // atur ukuran teks
        int textX = x + 5;
        int textY = y - 5;
        int fontSize = 5;

        // pilih ruangan yang mau ditambahkan ruangan nya
        if (listRuangan.size() == 1) {

        } else {
            String namaRuangan = JOptionPane.showInputDialog(null, "Masukkan nama ruangan: ");
        }

        // mekanisme pake drawstring sama window
        for (Ruangan ruangan : listRuangan) {
            if (namaRuangan == ruangan.getNama()) {
                drawSubWindow(x, y, width, height);
                g.drawString("Mau menambahkan ruangan di sisi mana?", x + 5, y - 5);
                textY -= fontSize;
                // check ruangan yang available dan kasih tau
                if (ruangan.listRuanganSamping[0] == false) {
                    String atas = "Atas.";
                    g.drawString(atas, textX, textY);
                    textY -= fontSize;
                }
                if (ruangan.listRuanganSamping[1] == false) {
                    String kanan = "Kanan.";
                    g.drawString(kanan, textX, textY);
                    textY -= fontSize;
                }
                if (ruangan.listRuanganSamping[2] == false) {
                    String bawah = "Bawah.";
                    g.drawString(bawah, textX, textY);
                    textY -= fontSize;
                }
                if (ruangan.listRuanganSamping[3] == false) {
                    String kiri = "Kiri.";
                    g.drawString(kiri, textX, textY);
                    textY -= fontSize;
                }
            } else {
                // ngapain kalau engga ada
            }
        }
    }

    /*
     * //buat button untuk setiap pilihan ruangan
     * JButton tombolAtas = new JButton("Atas");
     * JButton tombolKanan = new JButton("Kanan");
     * JButton tombolBawah = new JButton("Bawah");
     * JButton tombolKiri = new JButton("Kiri");
     * 
     * for (Ruangan ruangan : listRuangan) {
     * if (namaRuangan == ruangan.getNama()) { //kalau udah ada ruangan nyya
     * 
     * //pilih mau tambah di atas/bawah/kiri/kanan konsep input button click
     * tombolAtas.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * //tambah ruangan atas
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[0] = true;
     * }
     * });
     * tombolKanan.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * //tambah ruangan atas
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[1] = true;
     * }
     * });
     * tombolBawah.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * //tambah ruangan atas
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[2] = true;
     * }
     * });
     * tombolKiri.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * //tambah ruangan atas
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[3] = true;
     * }
     * });
     * 
     * //pilih mau tambah di atas/bawah/kiri/kanan konsep input button click
     * String pilihan = JOptionPane.showInputDialog(null,
     * "Mau menambahkan ruangan di sisi mana? ");
     * // atas = 0, kanan = 1, bawah = 2, kiri = 3
     * if (pilihan == "Atas") {
     * //tambah ruangan atas
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[0] = true;
     * }
     * if (pilihan == "Kanan") {
     * //tambah ruangan kanan
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[1] = true;
     * }
     * if (pilihan == "Bawah") {
     * //tambah ruangan bawah
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[2] = true;
     * }
     * if (pilihan == "Kiri") {
     * //tambah ruangan kiri
     * Ruangan ruanganBaru = new Ruangan(namaRuangan);
     * listRuangan.add(ruanganBaru);
     * ruangan.listRuanganSamping[3] = true;
     * }
     * }
     * else {
     * //kalau belum ada ruangan tersebut
     * }
     * }
     * }
     */
    public void daftarRuangan() {

    }

    public void draw(Graphics2D graph2d, Panel panel) {
        screenAbs = absis - panel.sim.absis + panel.sim.screenAbs;
        screenOrd = ordinat - panel.sim.ordinat + panel.sim.screenOrd;
        if (absis + panel.tileSize > panel.sim.absis - panel.sim.screenAbs
                && absis - panel.tileSize < panel.sim.absis + panel.sim.screenAbs
                && absis + panel.tileSize > panel.sim.absis - panel.sim.screenOrd
                && absis - panel.tileSize < panel.sim.absis + panel.sim.screenOrd) {
            graph2d.drawImage(this.image, screenAbs, screenOrd, null);
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 205); // R,G,B, alfa(opacity)
        g.setColor(c);
        g.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g.setColor(c);
        g.setStroke(new BasicStroke(5)); // 5 = width of outlines of graphics
        g.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void update() {
        if (isGeser == true) {
            if (controlKey.up == true || controlKey.down == true || controlKey.left == true
                    || controlKey.right == true) {
                this.collision = false;
                // panel.collisionCont.checkTile(this);

                if (this.controlKey.up) {
                    this.ordinat -= panel.tileSize;
                }
                if (this.controlKey.down) {
                    this.ordinat += panel.tileSize;
                }
                if (this.controlKey.left) {
                    this.absis -= panel.tileSize;
                }
                if (this.controlKey.right) {
                    this.absis += panel.tileSize;
                }
                if (this.controlKey.enter) {

                }
                if (this.controlKey.space) {

                }
            }

        }
    }

}