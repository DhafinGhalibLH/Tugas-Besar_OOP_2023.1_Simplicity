package food;

public class Makanan {
    private String nama;
    private int kekenyangan;
    private int kuantitasMakanan;

    public Makanan(String nama, int kekenyangan) {
        this.nama = nama;
        this.kekenyangan = kekenyangan;
        this.kuantitasMakanan = 1;

    }

    public String getNama() {
        return nama;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getKuantitasMakanan() {
        return this.kuantitasMakanan;
    }

    public void addKuantitasMakanan() {
        this.kuantitasMakanan++;
    }
}
