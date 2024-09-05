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
        // Made a simple BFS algorithm for this. We learned BFS in ATCS last year.

        // Convert board to nicer format
        int[] board = this.convertToAlexandreFormat(boardsize, ladders, snakes);

        // Keep track of visited tiles
        boolean[] visited = new boolean[boardsize];
        Arrays.fill(visited, false);

        // Keep track of moves
        int moves = 0;

        // Keep track of neighbours (and add first tile)
        Queue<Integer> neighbours = new LinkedList<>();
        neighbours.add(0);

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
                if (currentTile == boardsize - 1) {
                    return moves;
                }

                // Otherwise, calculate new neighbours (for all dice rolls)
                for (int i = 1; i <= 6; i++) {
                    // Check if we are not out of bounds
                    if (currentTile + i <= boardsize - 1) {
                        // Check if we have not visited this tile before
                        if (!visited[currentTile + i - 1]) {
                            // Mark tile as visited
                            visited[currentTile + i - 1] = true;

                            // Add tile to neighbours
                            neighbours.add(board[currentTile + i]);
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
        // Create array (size boardsize)
        int[] board = new int[boardsize];

        // Fill board with default values (no snake/ladder)
        for (int i = 0; i < boardsize; i++) {
            board[i] = i;
        }

        // NOTE: I initially forgot to subtract 1 from the end of the ladder/snake
        // since the instructions specify that the board starts at 1, not 0, and I
        // was using 0-based indexing for the rest of my code.

        // Add ladders to board
        for (int[] ladder : ladders) {
            board[ladder[0]] = ladder[1] - 1;
        }

        // Add snakes to board
        for (int[] snake : snakes) {
            board[snake[0]] = snake[1] - 1;
        }

        // Return board
        return board;
    }
}