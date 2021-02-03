package exams.noviembre_2020;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessWhoTest {
    private GuessWho game;

    @BeforeEach
    void setUp() {
        game = new GuessWho();
        game.loadGame("exams/noviembre_2020/files/marvel.txt");
    }

    @Test
    void checkTree() {
        String[] expected = {
                "Thor",
                "¿Es de los buenos?",
                "Loki",
                "¿Es mitológico?",
                "Spiderman",
                "¿Le picó un bicho?",
                "Capitan America",
                "¿Tiene poderes?",
                "Iron Man",
                "¿Ha salido en el MCU?",
                "Ms. Marvel",
                "¿Es adolescente?",
                "Caballero Negro",
                "¿Es de la era medieval?",
                "Hulka",
                "¿Es radioactivo?",
                "Spider Woman"
        };
        int count = 0;
        game.resetIterator();
        while (game.hasMoreQuestions()) {
            String q = game.nextQuestion();
            assertEquals(expected[count], q);
            count++;
        }
    }

    @Test
    void longestGame() {
        assertEquals(4, game.longestGame());
    }

    @Test
    void responses() {
        assertEquals(9, game.responses());
    }

    @Test
    void solve1() {
        String result = game.solve("exams/noviembre_2020/files/spiderman.txt");
        assertEquals("Spiderman", result);
    }

    @Test
    void solve2() {
        String result = game.solve("exams/noviembre_2020/files/incorrecto.txt");
        assertEquals("INCORRECTO", result);
    }

    @Test
    void solve3() {
        String result = game.solve("exams/noviembre_2020/files/hulka.txt");
        assertEquals("Hulka", result);
    }

    @Test
    void showGame() {
        String expected =
                "¿Ha salido en el MCU?\n" +
                "\t¿Tiene poderes?\n" +
                "\t\t¿Es mitológico?\n" +
                "\t\t\t¿Es de los buenos?\n" +
                "\t\t\t\tThor\n" +
                "\t\t\t\tLoki\n" +
                "\t\t\t¿Le picó un bicho?\n" +
                "\t\t\t\tSpiderman\n" +
                "\t\t\t\tCapitan America\n" +
                "\t\tIron Man\n" +
                "\t¿Es adolescente?\n" +
                "\t\tMs. Marvel\n" +
                "\t\t¿Es de la era medieval?\n" +
                "\t\t\tCaballero Negro\n" +
                "\t\t\t¿Es radioactivo?\n" +
                "\t\t\t\tHulka\n" +
                "\t\t\t\tSpider Woman\n";
        assertEquals(expected, game.showGame());
    }

}