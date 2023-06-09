package com.joffrey.bolber.business;

import com.joffrey.bolber.business.domain.map.Block;
import com.joffrey.bolber.business.domain.map.CreationBlock;

import static com.joffrey.bolber.business.domain.map.BlockType.ROAD;

/**
 * Manage the map creation from different creation blocks
 */
public class MapManagement {

    private Block[] mapData;
    private int horizontalLength;

    public void generateMap(final int verticalLength, final int horizontalLength) {
        this.horizontalLength = horizontalLength;
        mapData = new Block[verticalLength * horizontalLength];

        int index = 0;
        for (int v = 0; v < verticalLength; v++) {
            for (int h = 0; h < horizontalLength; h++) {
                mapData[index++] = new Block(ROAD, ROAD.getColor(), h, v);
            }
        }
    }

    public void generateBlocks(final CreationBlock... creationBlocks) {

        for (CreationBlock cb : creationBlocks) {
            int startX = cb.xStart();
            int endX = cb.xEnd();
            int startY = cb.yStart();

            for (; startY <= cb.yEnd(); startY++) {
                int offset = startY * horizontalLength;
                for (; startX <= endX; startX++) {
                    mapData[startX + offset] = new Block(cb.blockType(), cb.blockType().getColor(), startX, startY);
                }
                startX = cb.xStart();
            }
        }
    }

    public Block[] map() {
        return mapData;
    }

    public void generateBlocksFromCsvInput(final String[][] csvContent) {
        for (final String[] row : csvContent) {
            generateBlocks(CreationBlock.fromCsv(row));
        }
    }

}
