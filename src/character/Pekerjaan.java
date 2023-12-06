package character;

public class Pekerjaan 
{
    private String nama;
    private int gaji;
    private int durasiKerja;

    public Pekerjaan(String nama, int gaji, int durasiKerja) 
    {
        this.nama = nama;
        this.gaji = gaji;
        this.durasiKerja = durasiKerja;
    }

    public String getNamaPekerjaan() 
    {
        return this.nama;
    }

    public int getGaji() 
    {
        return this.gaji;
    }

    public int getDurasiKerja() 
    {
        return this.durasiKerja;
    }

    public void ubahPekerjaan(String nama) 
    {
        this.nama = nama;
    }
}
