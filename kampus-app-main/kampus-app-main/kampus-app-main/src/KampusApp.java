import java.util.Scanner;
import model.Mahasiswa;
import model.Prodi;
import repository.Database;

public class KampusApp {
    public static void main(String[] args) {
        KampusApp kampusApp = new KampusApp();
        kampusApp.showMenu();
    }

    private Scanner scanner;
    private Database db;

    public KampusApp() {
        scanner = new Scanner(System.in);
        db = new Database();
    }

    public void showMenu() {
        System.out.println("---------------------------------");
        System.out.println("Selamat Datang di Aplikasi Kampus");
        System.out.println("Pilihan Menu:");
        System.out.println("1 -> Tambah Data Mahasiswa");
        System.out.println("2 -> Ubah Data Mahasiswa");
        System.out.println("3 -> Hapus Data Mahasiswa");
        System.out.println("4 -> Cari Data Mahasiswa");
        System.out.println("0 -> Keluar Aplikasi");
        System.out.print("Silakan masukkan menu yang dipilih: ");
        int menuYangDipilih = scanner.nextInt();
        scanner.nextLine();
        switch (menuYangDipilih) {
            case 1:
                menuTambah();
                break;
            case 2:
                menuUbah();
                break;
            case 3:
                menuHapus();
                break;
            case 4:
                menuCari();
                break;
            default: {
                System.out.print("* Terima kasih sudah menggunakan Aplikasi Kampus *");
                System.exit(0);
            }
        }
        scanner.close();
    }

    public void menuTambah() {
        System.out.println("----- Menu Tambah Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan ID Prodi: ");
        int idProdi = scanner.nextInt();
        scanner.nextLine();

        Prodi prodi = db.prodiTbl.getProdiById(idProdi);
        if (prodi == null) {
            System.out.println("Prodi dengan ID " + idProdi + " tidak ditemukan.");
        } else {
            Mahasiswa mahasiswa = new Mahasiswa(nim, nama, prodi);
            db.mahasiswaTbl.create(mahasiswa);
            System.out.println("Mahasiswa berhasil ditambahkan.");
        }

        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuUbah() {
        System.out.println("----- Menu Ubah Data Mahasiswa -----");
        System.out.print("Masukkan NIM Sebelumnya: ");
        String nimLama = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nimBaru = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan ID Prodi: ");
        int idProdi = scanner.nextInt();
        scanner.nextLine();

        Prodi prodi = db.prodiTbl.getProdiById(idProdi);
        if (prodi == null) {
            System.out.println("Prodi dengan ID " + idProdi + " tidak ditemukan.");
        } else {
            Mahasiswa mahasiswa = new Mahasiswa(nimBaru, nama, prodi);
            db.mahasiswaTbl.update(nimLama, mahasiswa);
            System.out.println("Data Mahasiswa berhasil diubah.");
        }

        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuCari() {
        System.out.println("----- Menu Cari Data Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        Mahasiswa mahasiswa = db.mahasiswaTbl.getMahasiswaByNim(nim);
        if (mahasiswa == null) {
            System.out.println("* NIM tidak ditemukan *");
        } else {
            System.out.println("* NIM: " + mahasiswa.getNim() + " *");
            System.out.println("* Nama: " + mahasiswa.getNama() + " *");
            System.out.println("* Prodi: " + mahasiswa.getProdi().getNama() + " *");
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuHapus() {
        System.out.println("----- Menu Hapus Data Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        db.mahasiswaTbl.delete(nim);
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
}
