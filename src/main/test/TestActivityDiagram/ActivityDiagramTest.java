package ActivityDiagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityDiagramTest {

    private ActivityDiagram diagram;

    // @BeforeEach akan dijalankan sebelum setiap @Test, memastikan objek selalu baru
    @BeforeEach
    void setUp() {
        diagram = new ActivityDiagram("Sistem Pemesanan");
    }

    @Test
    void testActivityDiagramInitialization() {
        assertNotNull(diagram, "Objek ActivityDiagram seharusnya berhasil diinisialisasi");
    }

    @Test
    void testAddNodeAndSymbols() {
        // Menguji pembuatan node dan memastikan switch-case pada getNodeSymbol berjalan benar
        // Memanggil konstanta langsung dari interface INodeTypes yang diimplementasikan
        diagram.addNode("n1", ActivityDiagram.START, "Mulai");
        diagram.addNode("n2", "action", "Proses Data");
        diagram.addNode("n3", ActivityDiagram.DECISION, "Validasi");
        diagram.addNode("n4", ActivityDiagram.FORK, "Paralel");
        diagram.addNode("n5", ActivityDiagram.END, "Selesai");

        String result = diagram.toString();

        // Verifikasi apakah header dan simbol tercetak dengan benar
        assertTrue(result.contains("=== Activity Diagram: Sistem Pemesanan ==="));
        assertTrue(result.contains("(*)"), "Node START harus disimbolkan dengan (*)");
        assertTrue(result.contains("[Proses Data]"), "Node action harus menggunakan label aslinya [Label]");
        assertTrue(result.contains("<>"), "Node DECISION harus disimbolkan dengan <>");
        assertTrue(result.contains("==="), "Node FORK harus disimbolkan dengan ===");
        assertTrue(result.contains("(/)"), "Node END harus disimbolkan dengan (/)");
    }

    @Test
    void testAddFlowAndCalculateLevels() {
        // Membuat alur sederhana untuk menguji logika BFS (Breadth-First Search)
        diagram.addNode("n1", ActivityDiagram.START, "Mulai");
        diagram.addNode("n2", "action", "Tampilkan Menu");
        diagram.addNode("n3", ActivityDiagram.END, "Selesai");

        // Menyambungkan antar node
        diagram.addFlow("n1", "n2");
        diagram.addFlow("n2", "n3");

        String result = diagram.toString();

        // Verifikasi output panah relasi (->)
        assertTrue(result.contains("-> [Tampilkan Menu]"), "Flow dari start ke action gagal dibuat");
        assertTrue(result.contains("-> (/)"), "Flow dari action ke end gagal dibuat");
    }

    @Test
    void testEmptyDiagram() {
        // Menguji bagaimana class menangani diagram yang kosong tanpa error
        String result = diagram.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("=== Activity Diagram: Sistem Pemesanan ==="));
        assertFalse(result.contains("(*)")); // Seharusnya tidak ada node apa pun
    }
}