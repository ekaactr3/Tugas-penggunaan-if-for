// repository/ProdiTbl.java
package repository;

import java.util.ArrayList;
import model.Prodi;

public class ProdiTbl {
    private ArrayList<Prodi> dataProdi = new ArrayList<>();

    public ProdiTbl() {
        create(new Prodi(25, "Teknik Industri"));
        create(new Prodi(05, "Sistem Informasi"));
        create(new Prodi(20, "Informatika"));
    }

    public void create(Prodi prodi) {
        dataProdi.add(prodi);
    }

    public Prodi getProdiById(int id) {
        for (Prodi prodi : dataProdi) {
            if (prodi.getId() == id) {
                return prodi;
            }
        }
        return null;
    }

    public boolean isProdiExists(int id) {
        for (Prodi prodi : dataProdi) {
            if (prodi.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
