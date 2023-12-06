package food;

public class BahanMakanan extends Makanan {
    private String nama;
    private int harga;
    private int kekenyangan;

    public BahanMakanan(String nama, int harga, int kekenyangan) {
        super(nama, kekenyangan);
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
