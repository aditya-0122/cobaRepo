package ActivityDiagram;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    void testNodeInitialization() {
        // Menguji apakah konstruktor Node berhasil memanggil super(id, type, label, level)
        // dan menginisialisasi ArrayList kosong untuk 'outgoing'
        Node node = new Node("n1", "activity", "Proses Login");

        // Memastikan data warisan dari kelas Element terisi dengan benar
        assertEquals("n1", node.getId(), "ID tidak sesuai");
        assertEquals("activity", node.getType(), "Type tidak sesuai");
        assertEquals("Proses Login", node.getLabel(), "Label tidak sesuai");
        assertEquals(0, node.getLevel(), "Level awal harus 0");

        // Memastikan list outgoing tidak null dan masih kosong
        assertNotNull(node.getOutgoing(), "List outgoing tidak boleh null");
        assertTrue(node.getOutgoing().isEmpty(), "List outgoing harus kosong saat inisialisasi");
    }

    @Test
    void testAddOutgoing() {
        // Menguji penambahan targetId ke dalam list outgoing
        Node node = new Node("n1", "start", "Mulai");
        
        node.addOutgoing("n2");
        node.addOutgoing("n3");

        List<String> outgoing = node.getOutgoing();
        
        assertEquals(2, outgoing.size(), "Jumlah elemen outgoing harus 2");
        assertTrue(outgoing.contains("n2"), "List harus mengandung 'n2'");
        assertTrue(outgoing.contains("n3"), "List harus mengandung 'n3'");
    }

    @Test
    void testGetSymbolAllTypes() {
        // Menguji seluruh percabangan (branch) pada switch-case
        // Ini penting untuk mencapai 100% Branch Coverage di SonarCloud
        
        Node startNode = new Node("1", "start", "Start");
        assertEquals("(●)", startNode.getSymbol());

        Node endNode = new Node("2", "end", "End");
        assertEquals("(◉)", endNode.getSymbol());

        Node activityNode = new Node("3", "activity", "Login");
        assertEquals("[Login]", activityNode.getSymbol());

        Node decisionNode = new Node("4", "decision", "Valid?");
        assertEquals("<>Valid?", decisionNode.getSymbol());

        Node mergeNode = new Node("5", "merge", "Merge");
        assertEquals("<>", mergeNode.getSymbol());

        Node forkNode = new Node("6", "fork", "Fork");
        assertEquals("===", forkNode.getSymbol());

        Node joinNode = new Node("7", "join", "Join");
        assertEquals("===", joinNode.getSymbol());

        // Menguji kondisi default (tipe yang tidak dikenali)
        Node defaultNode = new Node("8", "unknown", "Custom Label");
        assertEquals("Custom Label", defaultNode.getSymbol(), "Default case harus mengembalikan label");
    }
}