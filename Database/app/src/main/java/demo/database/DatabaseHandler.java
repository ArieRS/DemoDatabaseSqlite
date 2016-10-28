package demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alhamdulillah on 10/20/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static String DATABASE_NAME = "dataPenduduk";
    private static int DB_VERSION = 8;

    private static String TABLE_NAME = "ktp";
    private static String KEY_ID_KTP = "id";
    private static String KEY_NAMA = "nama";
    private static String KEY_TELPN = "telpn";
    private static String KEY_AGAMA = "agama";

    private static String TABLE_NAME_VEHICLE = "kendaraan";
    private static String KEY_ID_VEHICLE = "id_kendaraan";
    private static String KEY_TYPE = "jenis";
    private static String KEY_COUNT = "jumlah";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      sqLiteDatabase.execSQL("Create Table KTP (id INTEGER PRIMARY KEY,
//      nama TEXT, telpn TEXT, agama TEXT);");
        String query = "Create TABLE " + TABLE_NAME +"(" +
                KEY_ID_KTP+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_NAMA+" TEXT, "+
                KEY_TELPN+" TEXT ,"+
                KEY_AGAMA+" TEXT"+
                ");";
        String query1 = "Create TABLE " + TABLE_NAME_VEHICLE +"(" +
                KEY_ID_VEHICLE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_ID_KTP+" INTEGER, "+
                KEY_TYPE+" TEXT ,"+
                KEY_COUNT+" INTEGER"+
                ");";
        db.execSQL(query);
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_VEHICLE);
        onCreate(sqLiteDatabase);
    }

    public int insertDataKTP(KTP ktp){
        SQLiteDatabase db = this.getWritableDatabase();
        //cara pertama
//      db.rawQuery("INSERT INTO "+TABLE_NAME+" (id,nama,telpn,agama) values ("+String.valueOf(ktp.getId())
//                +","+ktp.getNama()+","+ktp.getTelpn()+","+ktp.getAgama()+")",null);

        ContentValues mValues = new ContentValues();
        mValues.put(KEY_NAMA,ktp.getNama());
        mValues.put(KEY_TELPN,ktp.getTelpn());
        mValues.put(KEY_AGAMA,ktp.getAgama());
        long index = db.insert(TABLE_NAME,null,mValues) ;
        db.close();

        return (int) index;
    }

    public KTP getDataKTP(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        //cara pertama
        Cursor mCursor=  db.rawQuery("Select * from "+ TABLE_NAME+" Where id = "+ String.valueOf(id),null);
        //cara kedua
//        Cursor mCursor2 = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAMA, KEY_TELPN,KEY_AGAMA},KEY_ID+"=?",
//                new String[]{String.valueOf(id)},null,null,null);

        if (mCursor != null) {
            if (mCursor.moveToFirst()){
                KTP mKtp = new KTP(mCursor.getInt(0),mCursor.getString(1),
                        mCursor.getString(2),mCursor.getString(3));
                return mKtp;
            }

        }
        return null;
    }

    public int updateDataKTP(KTP ktp){
        SQLiteDatabase db = this.getWritableDatabase();
//        //cara pertama
//        db.rawQuery("Update ktp set nama = "+ktp.getNama()+
//                " , telpn= "+ktp.getTelpn()+
//                " , agama = "+ktp.getAgama()+
//                " Where id ="+ktp.getId(),null);
//        //cara kedua
//        db.rawQuery("Update ktp set "+ KEY_NAMA+"= "+ktp.getNama()+
//                " ,"+KEY_TELPN+" = "+ktp.getTelpn()+
//                " , "+KEY_AGAMA+"agama = "+ktp.getAgama()+
//                " Where "+KEY_ID_KTP+" ="+ktp.getId(),null);

        //cara ketiga
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA,ktp.getNama());
        values.put(KEY_TELPN,ktp.getTelpn());
        values.put(KEY_AGAMA,ktp.getAgama());

        int updateRows = db.update(TABLE_NAME,values, KEY_ID_KTP+"=?",
                new String[]{String.valueOf(ktp.getId())});
        db.close();
        return updateRows;
    }

    public int deleteDataKTP(int id){
        SQLiteDatabase db = this.getWritableDatabase();
//        //cara pertama
//        db.rawQuery("Delete From "+ TABLE_NAME+" Where id = "+String.valueOf(id),null);
        //cara kedua
        int affectedRows = db.delete(TABLE_NAME,KEY_ID_KTP+" =?",new String[]{String.valueOf(id)});
        db.close();
        return  affectedRows;
    }

    public List<KTP> getAllDataKTP(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<KTP> listKTP = new ArrayList<KTP>();

        //cara pertama
        Cursor mCursor =  db.rawQuery("Select * from "+ TABLE_NAME,null);
        //cara kedua
        //Cursor mCursor = db.query(TABLE_NAME,new String[]{KEY_ID_KTP,KEY_NAMA, KEY_TELPN,KEY_AGAMA},
        //          null, null,null,null,null);

        if (mCursor != null) {
            if (mCursor.moveToFirst()){
                do {
                    KTP mKtp = new KTP(mCursor.getInt(0),mCursor.getString(1),
                            mCursor.getString(2),mCursor.getString(3));
                    listKTP.add(mKtp);
                }while (mCursor.moveToNext());
            }
        }
        return  listKTP;
    }
    //contoh melakukan insert ke tabel ke dua
    public int insertDataKendaraan(Kendaraan kendaraan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_KTP, kendaraan.getId());
        values.put(KEY_TYPE, kendaraan.getJenis());
        values.put(KEY_COUNT, kendaraan.getJumlah());
        int affected  = (int) db.insert(TABLE_NAME_VEHICLE,null,values);
        return affected;
    }
    //contoh melakukan get lebih dari satu tabel
    //kalau melakukan get jangan lupa di insert dulu datanya
    public KTPKendaraan getDataKTPKendaraan(int idKTP){
        SQLiteDatabase db = this.getWritableDatabase();
        //cara pertama
        Cursor mCursor  = db.rawQuery("Select ktp.*,kendaraan.jumlah" +
                " from ktp, kendaraan "+
                " where ktp.id = "+String.valueOf(idKTP)+
                " and kendaraan.id="+String.valueOf(idKTP),null);

        if (mCursor!=null){
            if (mCursor.moveToFirst()){
                KTPKendaraan ktpKendaraan = new KTPKendaraan();
                KTP ktp =new KTP(mCursor.getInt(0),mCursor.getString(1),
                        mCursor.getString(2),mCursor.getString(3));
                Kendaraan kendaraan = new Kendaraan(0,0,"",Integer.parseInt(mCursor.getString(4)));

                ktpKendaraan.setKTP(ktp);
                ktpKendaraan.setKendaraan(kendaraan);
                return ktpKendaraan;
            }
        }
        return null;
    }
}
