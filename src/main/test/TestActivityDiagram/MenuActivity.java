package ActivityDiagram;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuActivityTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Menyiapkan wadah (stream) untuk menangkap output dari System.out
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Penting: Mengembalikan aliran output ke konsol asli setelah tes selesai
        // agar tidak mengganggu output dari tes-tes lain di dalam pipeline
        System.setOut(originalOut);
    }

    @Test
    void testMainMenuActivityOutput() {
        // Inisialisasi objek MenuActivity
        MenuActivity menu = new MenuActivity();
        
        // Panggil method yang akan mencetak teks ke konsol
        menu.mainMenuActivity();

        // Ambil semua teks yang berhasil ditangkap
        String output = outContent.toString();

        // Verifikasi bahwa semua baris menu benar-benar tercetak
        assertTrue(output.contains("Main Menu:"), "Header menu gagal dicetak");
        assertTrue(output.contains("1. Activity Diagram"), "Opsi 1 (Activity) tidak ditemukan");
        assertTrue(output.contains("2. Use Case Diagram"), "Opsi 2 (Use Case) tidak ditemukan");
        assertTrue(output.contains("3. Sequence Diagram"), "Opsi 3 (Sequence) tidak ditemukan");
        assertTrue(output.contains("4. Class Diagram"), "Opsi 4 (Class) tidak ditemukan");
        assertTrue(output.contains("5. Exit"), "Opsi Exit tidak ditemukan");
        assertTrue(output.contains("Enter your choice:"), "Prompt input user tidak ditemukan");
    }
}