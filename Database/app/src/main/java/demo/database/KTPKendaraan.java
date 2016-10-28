package demo.database;

/**
 * Created by alhamdulillah on 10/27/16.
 */

public class KTPKendaraan {
    KTP mKTP;
    Kendaraan mKendaraan;

    public KTPKendaraan(){}
    public KTPKendaraan(KTP KTP, Kendaraan kendaraan) {
        mKTP = KTP;
        mKendaraan = kendaraan;
    }

    public KTP getKTP() {
        return mKTP;
    }

    public void setKTP(KTP KTP) {
        mKTP = KTP;
    }

    public Kendaraan getKendaraan() {
        return mKendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        mKendaraan = kendaraan;
    }
}
