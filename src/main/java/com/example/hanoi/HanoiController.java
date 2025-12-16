package com.example.hanoi;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class HanoiController {
    @FXML private Pane board;
    @FXML private Rectangle disk1, disk2, disk3;

    private HanoiModel model;

    private Map<Integer, Rectangle> diskMap;

    private Integer selectedPeg = null;

    private final int[] PEG_CENTERS = {105, 300, 495};
    private final int BASE_Y = 260;
    private final int DISK_HEIGHT = 20;

    public void initialize() {
        model = new HanoiModel(3);

        diskMap = new HashMap<>();
        diskMap.put(1, disk1);
        diskMap.put(2, disk2);
        diskMap.put(3, disk3);

        render();
    }

    private void render() {
        List<Stack<Integer>> pegs = model.getPegs();

        for (int i = 0; i < pegs.size(); i++) {
            Stack<Integer> stack = pegs.get(i);
            int centerX = PEG_CENTERS[i];

            for (int j = 0; j < stack.size(); j++) {
                Integer diskSize = stack.get(j);
                Rectangle diskRect = diskMap.get(diskSize);

                double diskWidth = diskRect.getWidth();
                diskRect.setLayoutX(centerX - (diskWidth / 2));

                diskRect.setLayoutY(BASE_Y - ((j + 1) * DISK_HEIGHT));

                diskRect.setStroke(null);
            }
        }
    }

    /**
     * Handles clicks on the background pane to determine peg selection.
     */
    @FXML
    public void onBoardClick(MouseEvent event) {
        // X koordináta ahová kattintottunk
        double mouseX = event.getX();
        // Meghatározzuk melyik oszlopra történt
        int clickedPegIndex = getPegFromX(mouseX);

        if (clickedPegIndex == -1) return; // Ha egyik oszlopnál se, nem történik semmi

        if (selectedPeg == null) {
            // Rúd kiválasztása
            Stack<Integer> sourceStack = model.getPegs().get(clickedPegIndex);

            if (!sourceStack.isEmpty()) {
                selectedPeg = clickedPegIndex;

                // Felső korong kiemelése
                int topDiskSize = sourceStack.peek();
                diskMap.get(topDiskSize).setStroke(Color.WHITE);
                diskMap.get(topDiskSize).setStrokeWidth(3);
                System.out.println("Kiválasztott rúd: Rúd " + (clickedPegIndex + 1));
            }
        } else {
            boolean success = model.move(selectedPeg, clickedPegIndex);

            if (success) {
                System.out.println("A korong sikeresen átmozgatva ide: Rúd " + (clickedPegIndex + 1));
                render(); // Újrarajzoljuk
            } else {
                System.out.println("Helytelen lépés");
                render(); // Kiemelés eltávolítása
            }

            selectedPeg = null;
        }
    }

    // Felosztjuk a képernyőt 3 részre
    private int getPegFromX(double x) {
        double width = board.getWidth(); // 600px
        if (x < width / 3) return 0; // Bal oldali rúd (0-200)
        if (x < (width / 3) * 2) return 1; // Középső rúd (200-400)
        return 2; // Jobb oldali rúd (400-600)
    }
}