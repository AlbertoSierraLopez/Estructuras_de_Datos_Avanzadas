package exams.junio_2016;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Test {
    BinarySearchTreeExtension<Student> b;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        b = new BinarySearchTreeExtension<>();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        b = null;
    }

    @org.junit.jupiter.api.Test
    void find() {
        final int N = 10;
        for (int i = 0; i < N; i++) {
            b.insert(new Student("Nombre"+i, "Apellido"+i, i));
        }

        b.removeRangeAlt(new Student(null, null, 1), new Student(null, null, 8));
        assertEquals(2, b.size());
    }
}
