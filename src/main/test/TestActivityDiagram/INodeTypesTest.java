package ActivityDiagram;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class INodeTypesTest {

    @Test
    void testConstantValues() {
        // Memastikan tidak ada yang secara tidak sengaja mengubah nilai string dari konstanta
        // karena kelas lain sangat bergantung pada string pasti ini untuk melakukan pencocokan (switch-case)
        
        assertEquals("start", INodeTypes.START, "Konstanta START telah berubah");
        assertEquals("end", INodeTypes.END, "Konstanta END telah berubah");
        assertEquals("decision", INodeTypes.DECISION, "Konstanta DECISION telah berubah");
        assertEquals("merge", INodeTypes.MERGE, "Konstanta MERGE telah berubah");
        assertEquals("fork", INodeTypes.FORK, "Konstanta FORK telah berubah");
        assertEquals("join", INodeTypes.JOIN, "Konstanta JOIN telah berubah");
        assertEquals("action", INodeTypes.ACTION, "Konstanta ACTION telah berubah");
    }
}