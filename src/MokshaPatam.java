import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Alexandre Haddad-Delaveau
 *
 */

public class MokshaPatam {
    public int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Convert board to nicer (more efficient and easier to use) format
        int[] board = this.convertToAlexandreFormat(boardsize, ladders, snakes);

        // Keep track of moves
        int moves = 0;

        // Keep track of neighbours (and add first tile)
        Queue<Integer> neighbours = new LinkedList<>();
        neighbours.add(1);

        // Repeat until no more neighbours / tiles can be visited
        while (!neighbours.isEmpty()) {
            // Loop through all (current) neighbours
            // To do this we save the size of neighbours before we start the loop
            // since the size of neighbours will change during the loop.
            int neighboursSize = neighbours.size();

            while (neighboursSize > 0) {
                // Decrease neighboursSize
                neighboursSize--;

                // Get current tile
                int currentTile = neighbours.remove();

                // Check if this is the final tile
                if (currentTile == boardsize) {
                    return moves;
                }

                // Otherwise, calculate new neighbours (for all dice rolls)
                for (int i = 1; i <= 6; i++) {
                    // Check if we are not out of bounds
                    if (currentTile + i <= boardsize) {
                        // Check if we have not visited this tile before
                        if (board[currentTile + i] != -1) {
                            // Add tile to neighbours
                            neighbours.add(board[currentTile + i]);

                            // Mark tile as visited
                            board[currentTile + i] = -1;
                        }
                    }
                }
            }

            // Increase moves
            moves++;
        }

        // Return -1 if no solution
        return -1;
    }

    private int[] convertToAlexandreFormat(int boardsize, int[][] ladders, int[][] snakes) {
        // Create array to store board
        int[] board = new int[boardsize + 1];

        // Fill board with default values
        for (int i = 0; i < boardsize + 1; i++) {
            board[i] = i;
        }

        // Add ladders to board
        for (int[] ladder : ladders) {
            board[ladder[0]] = ladder[1];
        }

        // Add snakes to board
        for (int[] snake : snakes) {
            board[snake[0]] = snake[1];
        }

        // Return board
        return board;
    }
}