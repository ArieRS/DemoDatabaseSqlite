package demo.database;

/**
 * Created by alhamdulillah on 10/27/16.
 */

public class Kendaraan {
    int id_kendaraan;
    int id;
    String Jenis;
    int jumlah;

    public Kendaraan(int id_kendaraan, int id, String jenis, int jumlah) {
        this.id_kendaraan = id_kendaraan;
        this.id = id;
        Jenis = jenis;
        this.jumlah = jumlah;
    }

    public int getId_kendaraan() {
        return id_kendaraan;
    }

    public void setId_kendaraan(int id_kendaraan) {
        this.id_kendaraan = id_kendaraan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
