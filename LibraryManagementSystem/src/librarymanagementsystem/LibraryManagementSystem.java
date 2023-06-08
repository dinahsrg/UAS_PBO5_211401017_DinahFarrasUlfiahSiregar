/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagementsystem;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */

class Buku {
    private String kode;
    private String judul;
    private String penulis;
    private int jumlah;

    public Buku(String kode, String judul, String penulis, int jumlah) {
        this.kode = kode;
        this.judul = judul;
        this.penulis = penulis;
        this.jumlah = jumlah;
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}

class Perpustakaan {
    private ArrayList<Buku> daftarBuku;

    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public void tampilkanDaftarBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku terdaftar.");
        } else {
            System.out.println("Daftar Buku:");
            System.out.printf("%-10s %-20s %-20s %-10s%n", "Kode", "Judul", "Penulis", "Jumlah");
            for (Buku buku : daftarBuku) {
                System.out.printf("%-10s %-20s %-20s %-10d%n", buku.getKode(), buku.getJudul(), buku.getPenulis(), buku.getJumlah());
            }
        }
    }

    public void pinjamBuku(String kode) {
        Buku buku = cariBuku(kode);
        if (buku != null) {
            if (buku.getJumlah() > 0) {
                buku.setJumlah(buku.getJumlah() - 1);
                System.out.println("Buku berhasil dipinjam.");
            } else {
                System.out.println("Stok buku habis.");
            }
        } else {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
        }
    }

    public void kembalikanBuku(String kode) {
        Buku buku = cariBuku(kode);
        if (buku != null) {
            buku.setJumlah(buku.getJumlah() + 1);
            System.out.println("Buku berhasil dikembalikan.");
        } else {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
        }
    }

    private Buku cariBuku(String kode) {
        for (Buku buku : daftarBuku) {
            if (buku.getKode().equals(kode)) {
                return buku;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    private static Scanner scanner;
    private static Perpustakaan perpustakaan;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        perpustakaan = new Perpustakaan();

        tampilkanMenu();
    }

    public static void tampilkanMenu() {
        int pilihan = 0;
        while (pilihan != 5) {
            System.out.println("=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Input Buku");
            System.out.println("2. Daftar Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Mengonsumsi karakter newline (\n)

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanDaftarBuku();
                    break;
                case 3:
                    pinjamBuku();
                    break;
                case 4:
                    kembalikanBuku();
                    break;
                case 5:
                    System.out.println("Terima kasih. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    public static void tambahBuku() {
        System.out.print("Kode buku: ");
        String kode = scanner.nextLine();
        System.out.print("Judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Penulis buku: ");
        String penulis = scanner.nextLine();
        System.out.print("Jumlah buku: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Mengonsumsi karakter newline (\n)

        Buku buku = new Buku(kode, judul, penulis, jumlah);
        perpustakaan.tambahBuku(buku);
        System.out.println("Buku berhasil ditambahkan.");
    }

    public static void tampilkanDaftarBuku() {
        perpustakaan.tampilkanDaftarBuku();
    }

    public static void pinjamBuku() {
        System.out.print("Kode buku yang ingin dipinjam: ");
        String kode = scanner.nextLine();

        perpustakaan.pinjamBuku(kode);
    }

    public static void kembalikanBuku() {
        System.out.print("Kode buku yang ingin dikembalikan: ");
        String kode = scanner.nextLine();

        perpustakaan.kembalikanBuku(kode);
    }
}
