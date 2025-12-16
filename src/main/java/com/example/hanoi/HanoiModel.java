package com.example.hanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HanoiModel {
    // Az összes rúd együtt (Stack --> LIFO --> Last In, First Out – amit utoljára tettél be, azt veszed ki először.)
    private final List<Stack<Integer>> pegs;

    private final int numDisks;

    public HanoiModel(int numDisks) {
        this.numDisks = numDisks;
        pegs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            pegs.add(new Stack<>());
        }
        reset();
    }

    public void reset() {
        for (Stack<Integer> peg : pegs) {
            peg.clear();
        }

        for (int i = numDisks; i >= 1; i--) {
            pegs.getFirst().push(i);
        }
    }

    public List<Stack<Integer>> getPegs() {
        return pegs;
    }
}