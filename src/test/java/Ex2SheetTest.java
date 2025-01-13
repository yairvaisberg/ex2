import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ex2SheetTest {

    @Test
    void value() {



        Ex2Sheet A2 = new Ex2Sheet(2,0);
        Ex2Sheet B1 = new Ex2Sheet(1,1);
        Ex2Sheet C4 = new Ex2Sheet(2,5);
        Ex2Sheet C0 = new Ex2Sheet(2,0);
        Ex2Sheet E1 = new Ex2Sheet(4,1);


        assertEquals(4, A2.computeForm("=2+2"));
        assertEquals(22, B1.computeForm("=2+20"));
        assertEquals(0.5, C4.computeForm("=4/8"));
        assertEquals(4, C0.computeForm("=2+2"));
        assertEquals(4, E1.computeForm("=2+2"));



    }
}