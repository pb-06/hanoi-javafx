package com.example.hanoi;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
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

}