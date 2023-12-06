package building;

import main.Panel;
import main.EntityControl;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import food.BahanMakanan;
import food.Makanan;
import otherobject.OtherObjects;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Ruangan {

    // Atribut Ruangan
    BufferedImage image;
    Graphics2D graph2D;
    Panel panel;
    private String nama;
    private ArrayList<OtherObjects> listObjek;
    private ArrayList<BahanMakanan> listBahanMakanan;
    private ArrayList<Makanan> listMakanan;
    EntityControl entityControl;
    boolean[] listRuanganSamping = new boolean[4];

    public Ruangan(Panel panel, String nama, ArrayList<OtherObjects> listObjek,
            ArrayList<BahanMakanan> listBahanMakanan,
            ArrayList<Makanan> listMakanan) {
        this.nama = nama; // inisiasi nama ruangan
        this.listObjek = listObjek;
        this.listBahanMakanan = listBahanMakanan;
        this.listMakanan = listMakanan;
        this.panel = panel;
        entityControl = new EntityControl(this.panel);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/ruangan/ruangan.png"));
            entityControl.utility(image, panel.tileSize, panel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getter
    public String getNama() {
        return this.nama;
    }

    public ArrayList<OtherObjects> getListObjek() {
        return this.listObjek;
    }

    public ArrayList<Makanan> getListMakanan() {
        return this.listMakanan;
    }

    public ArrayList<BahanMakanan> getListBahanMakanan() {
        return this.listBahanMakanan;
    }

    // setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void addOtherObjects(OtherObjects objek, ArrayList<OtherObjects> listObjek) {
        // Jika objek belum ada
        for (int i = 0; i < listObjek.size(); i++) {
            if (listObjek.get(i) != objek) {
                listObjek.add(objek);
                objek.tambahKuantitas();
            }
            // jika objek sudah ada maka jumlahnya bertambah
            else if (listObjek.get(i) == objek) {
                objek.tambahKuantitas();
            }

        }

    }

    public void addListBahanMakanan(BahanMakanan bahanmakanan, ArrayList<BahanMakanan> listBahanMakanan) {
        for (int i = 0; i < listBahanMakanan.size(); i++) {
            if (listBahanMakanan.get(i) != bahanmakanan) {
                listBahanMakanan.add(bahanmakanan);
                bahanmakanan.addKuantitasMakanan();
                ;
            }
            // jika objek sudah ada maka jumlahnya bertambah
            else if (listBahanMakanan.get(i) == bahanmakanan) {
                bahanmakanan.addKuantitasMakanan();
            }

        }
    }

    public void addListMakanan(Makanan makanan, ArrayList<Makanan> listMakanan) {
        for (int i = 0; i < listMakanan.size(); i++) {
            if (listMakanan.get(i) != makanan) {
                listMakanan.add(makanan);
                makanan.addKuantitasMakanan();
            }
            // jika objek sudah ada maka jumlahnya bertambah
            else if (listMakanan.get(i) == makanan) {
                makanan.addKuantitasMakanan();
            }

        }
    }

    // Aksi

}
