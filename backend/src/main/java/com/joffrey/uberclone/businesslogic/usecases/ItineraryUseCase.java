package com.joffrey.uberclone.businesslogic.usecases;

import com.joffrey.uberclone.businesslogic.models.Block;
import com.joffrey.uberclone.businesslogic.models.Coordinates;

import java.util.*;

public class ItineraryUseCase {
    
    public List<Coordinates> findShortestPath(Block[][] matrix, Coordinates start, Coordinates end) {
        int rowsNumber = matrix.length;
        int columnNumber = matrix[0].length;

        boolean[][] visitedNodes = new boolean[rowsNumber][columnNumber];
        Coordinates[][] parent = new Coordinates[rowsNumber][columnNumber];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Coordinates> bfsQueue = new LinkedList<>();
        bfsQueue.offer(start);
        visitedNodes[start.row()][start.col()] = true;

        while (!bfsQueue.isEmpty()) {
            Coordinates current = bfsQueue.poll();

            if (current.row() == end.row() && current.col() == end.col()) {
                return reconstructPath(parent, current);
            }

            exploreNeighbors(matrix, rowsNumber, columnNumber, directions, visitedNodes, parent, bfsQueue, current);
        }

        return Collections.emptyList();
    }

    private List<Coordinates> reconstructPath(Coordinates[][] parent, Coordinates current) {
        List<Coordinates> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = parent[current.row()][current.col()];
        }
        Collections.reverse(path);
        return path;
    }

    private void exploreNeighbors(Block[][] matrix, int rowsNumber, int columnNumber, int[][] directions,
                                  boolean[][] visitedNodes, Coordinates[][] parent, Queue<Coordinates> bfsQueue,
                                  Coordinates current) {
        for (int[] dir : directions) {
            int newRow = current.row() + dir[0];
            int newCol = current.col() + dir[1];

            if (isValidNeighbor(newRow, newCol, rowsNumber, columnNumber) &&
                    isRoadBlock(matrix[newRow][newCol]) && !visitedNodes[newRow][newCol]) {
                bfsQueue.offer(new Coordinates(newRow, newCol));
                visitedNodes[newRow][newCol] = true;
                parent[newRow][newCol] = current;
            }
        }
    }

    private boolean isValidNeighbor(int newRow, int newCol, int rowsNumber, int columnNumber) {
        return newRow >= 0 && newRow < rowsNumber && newCol >= 0 && newCol < columnNumber;
    }

    private boolean isRoadBlock(Block block) {
        return Objects.equals(block.type(), "ROAD");
    }
}