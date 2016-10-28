package demo.database;

/**
 * Created by alhamdulillah on 10/20/16.
 */

public class KTP {
    int id;
    String nama;
    String telpn;
    String agama;
//1
    public KTP(){}
//2
    public KTP(int id, String nama, String telpn, String agama) {
        this.id = id;
        this.nama = nama;
        this.telpn = telpn;
        this.agama = agama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelpn() {
        return telpn;
    }

    public void setTelpn(String telpn) {
        this.telpn = telpn;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }
}
