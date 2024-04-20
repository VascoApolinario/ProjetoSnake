package FigurasGeo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreProcessamentoTest {

    @Test
    public void testRectIntercept() {

        PreProcessamento rectA = new PreProcessamento(0,5,0,5);
        PreProcessamento rectB = new PreProcessamento(0,5,0,9);
        PreProcessamento rectC = new PreProcessamento(1,4,1,3);
        assertTrue(rectA.rectIntercept(rectB));
        assertTrue(rectA.rectIntercept(rectC));

        PreProcessamento rectD = new PreProcessamento(0,3,3,5);
        PreProcessamento rectE = new PreProcessamento(1,4,0,4);
        PreProcessamento rectF = new PreProcessamento(1,5,1,5);
        PreProcessamento rectG = new PreProcessamento(4,8,1,5);
        PreProcessamento rectH = new PreProcessamento(1,5,4,8);
        assertTrue(rectD.rectIntercept(rectE));
        assertTrue(rectF.rectIntercept(rectG));
        assertTrue(rectF.rectIntercept(rectH));

        PreProcessamento rectI = new PreProcessamento(2,6,0,3);
        PreProcessamento rectJ = new PreProcessamento(8,12,1,6);
        PreProcessamento rectK = new PreProcessamento(2,6,5,8);
        PreProcessamento rectL = new PreProcessamento(8,13,0,3);
        assertFalse(rectI.rectIntercept(rectJ));
        assertFalse(rectI.rectIntercept(rectK));
        assertFalse(rectI.rectIntercept(rectL));

    }
}