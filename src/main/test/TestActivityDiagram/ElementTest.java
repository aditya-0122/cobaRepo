package ActivityDiagram;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElementTest {

    // Membuat kelas konkret tiruan (dummy) khusus untuk menguji kelas abstract Element
    private static class DummyElement extends Element {
        public DummyElement(String id, String type, String label, int level) {
            super(id, type, label, level);
        }
    }

    @Test
    void testConstructorAndGetters() {
        // Inisialisasi variabel yang diharapkan
        String expectedId = "node-1";
        String expectedType = "action";
        String expectedLabel = "Tampilkan Halaman Utama";
        int expectedLevel = 2;

        // Membuat objek dari kelas tiruan
        Element element = new DummyElement(expectedId, expectedType, expectedLabel, expectedLevel);

        // Memastikan constructor mengisi nilai dengan benar dan getter mereturn nilai yang tepat
        assertEquals(expectedId, element.getId(), "ID tidak sesuai dengan yang diinisialisasi");
        assertEquals(expectedType, element.getType(), "Tipe tidak sesuai dengan yang diinisialisasi");
        assertEquals(expectedLabel, element.getLabel(), "Label tidak sesuai dengan yang diinisialisasi");
        assertEquals(expectedLevel, element.getLevel(), "Level tidak sesuai dengan yang diinisialisasi");
    }

    @Test
    void testSetLevel() {
        // Membuat objek dengan level awal 0
        Element element = new DummyElement("node-2", "decision", "Validasi Input", 0);

        // Mengubah nilai level menggunakan setter
        element.setLevel(5);

        // Memastikan nilai level berhasil diubah
        assertEquals(5, element.getLevel(), "Fungsi setLevel gagal mengubah nilai level");
    }
}