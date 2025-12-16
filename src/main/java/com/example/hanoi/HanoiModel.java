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

    public boolean move(int fromPegIndex, int toPegIndex) {
        if (fromPegIndex < 0 || fromPegIndex > 2 || toPegIndex < 0 || toPegIndex > 2) return false;
        if (fromPegIndex == toPegIndex) return false; // Ugyanoda nem mozgatható

        // Szükséges adatok lekérése
        Stack<Integer> source = pegs.get(fromPegIndex);
        Stack<Integer> target = pegs.get(toPegIndex);

        // Ellenőrizzük hogy van-e mit levenni.
        if (source.isEmpty()) return false;

        int diskToMove = source.peek(); // Megnézzük melyik a felső korong

        // Vagy üresnek kell lennie, vagy az alatta lévőnek nagyobbnak kell lennie
        if (!target.isEmpty() && target.peek() < diskToMove) {
            return false;
        }

        // Ha minden oké, akkor mozgatunk
        target.push(source.pop());
        return true;
    }

    public boolean checkWin() {
        return pegs.get(2).size() == numDisks;
    }
}