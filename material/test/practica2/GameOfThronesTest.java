package material.test.practica2;

import material.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.practica2.FamilyMember;
import usecase.practica2.GameOfThrones;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GameOfThronesTest {
    private GameOfThrones got;
    private String path = "usecase/practica2/GOT_Families.txt";

    @BeforeEach
    void setUp() throws FileNotFoundException {
        got = new GameOfThrones();
        got.loadFile(path);
    }


    @Test
    void testFindHeir() {
        String expected1 = "Eddard Stark";
        String output1 = got.findHeir("Stark");
        assertEquals(expected1, output1);

        String expected2 = "Aeron Greyjoy";
        String output2 = got.findHeir("Greyjoy");
        assertEquals(expected2, output2);
    }

    @Test
    void testChangeFamily() {
        got.changeFamily("Daenerys Targaryen", "Eddard Stark");
        assertTrue(got.areFamily("Daenerys Targaryen", "Arya Stark"));
        assertFalse(got.areFamily("Daenerys Targaryen", "Viserys Targaryen"));

        // Deshacer lo anteriormente descrito y comprobar
        got.changeFamily("Daenerys Targaryen", "Aerys Targaryen");
        assertFalse(got.areFamily("Daenerys Targaryen", "Arya Stark"));
        assertTrue(got.areFamily("Daenerys Targaryen", "Viserys Targaryen"));
    }

    @Test
    void testAreFamily() {
        assertTrue(got.areFamily("Arya Stark", "Robb Stark"));
        assertFalse(got.areFamily("Catelyn Tully", "Aerys Targaryen"));

        got.changeFamily("Robert Baratheon", "Rickard Stark");
        // Despues de cambiar de familia:
        // Los Stark y los hijos de Robert deben ser familia
        assertTrue(got.areFamily("Arya Stark", "Tommen Baratheon"));
        // Los hijos de Robert deben seguir siendo familia entre ellos
        assertTrue(got.areFamily("Joffrey Baratheon", "Myrcella Baratheon"));
        // Robert y Stannis ya no son hermanos
        assertFalse(got.areFamily("Stannis Baratheon", "Robert Baratheon"));
    }

}