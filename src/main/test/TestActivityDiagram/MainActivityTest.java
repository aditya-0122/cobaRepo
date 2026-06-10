package ActivityDiagram;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainActivityTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() throws Exception {
        // Menangkap output console (System.out.print) agar bisa dievaluasi
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Mengosongkan map static 'activityDiagrams' menggunakan Reflection 
        // agar data dari tes sebelumnya tidak bocor ke tes yang baru
        Field mapField = MainActivity.class.getDeclaredField("activityDiagrams");
        mapField.setAccessible(true);
        Map<?, ?> map = (Map<?, ?>) mapField.get(null);
        map.clear();
    }

    @AfterEach
    void tearDown() {
        // Mengembalikan System.in dan System.out ke wujud aslinya setelah tes selesai
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    // Helper method untuk menyimulasikan input keyboard pengguna
    private void provideInput(String data) throws Exception {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        
        // Karena 'scanner' di MainActivity bersifat static dan diinisialisasi di awal, 
        // kita harus me-resetnya agar membaca dari simulasi input yang baru dibuat
        Field scannerField = MainActivity.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, new Scanner(System.in));
    }

    @Test
    void testExitMenu() throws Exception {
        // Skenario: User mengetik "6" (Keluar)
        // \n menandakan penekanan tombol 'Enter'
        provideInput("6\n");
        MainActivity.main(new String[]{});
        
        assertTrue(outContent.toString().contains("Terima kasih!"));
    }

    @Test
    void testInvalidMenu() throws Exception {
        // Skenario: User mengetik "9" (Tidak Valid), lalu "6" (Keluar)
        // INGAT: Setiap tes harus diakhiri dengan "6" agar perulangan while(true) berhenti!
        provideInput("9\n6\n");
        MainActivity.main(new String[]{});
        
        assertTrue(outContent.toString().contains("Pilihan tidak valid!"));
    }

    @Test
    void testCreateNewActivityDiagram() throws Exception {
        // Skenario: Pilih 1 (Buat Diagram) -> Ketik nama "Diagram1" -> Pilih 6 (Keluar)
        provideInput("1\nDiagram1\n6\n");
        MainActivity.main(new String[]{});
        
        assertTrue(outContent.toString().contains("Activity diagram Diagram1 berhasil dibuat!"));
    }

    @Test
    void testAddNodeToDiagram() throws Exception {
        // Skenario Berurutan:
        // 1 (Buat Diagram) -> "Sistem"
        // 2 (Tambah Node) -> "Sistem" -> "n1" -> 1 (Start) -> "Mulai"
        // 6 (Keluar)
        provideInput("1\nSistem\n2\nSistem\nn1\n1\nMulai\n6\n");
        MainActivity.main(new String[]{});
        
        assertTrue(outContent.toString().contains("Node berhasil ditambahkan!"));
    }
    
    @Test
    void testDisplayEmptyDiagram() throws Exception {
        // Skenario: Coba tampilkan diagram saat belum ada yang dibuat
        provideInput("4\n6\n");
        MainActivity.main(new String[]{});
        
        assertTrue(outContent.toString().contains("Belum ada activity diagram yang dibuat!"));
    }
}