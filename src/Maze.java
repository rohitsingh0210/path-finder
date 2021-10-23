import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private final int rows;
    private final int cols;
    private GridElement[][] grid;

    public GridElement[][] getGrid() {
        return grid;
    }

    public GridElement getStart() {
        return start;
    }

    public GridElement getGoal() {
        return goal;
    }

    private GridElement start, goal;

    public Maze(String location) {
        int[] dimensions = getDimensions(location);
        this.rows = dimensions[0];
        this.cols = dimensions[1];
        this.grid = new GridElement[rows][cols];
        // System.out.println(rows);
        initializeGrid(location);
        calculateHeuristic();
        // System.out.println(Arrays.deepToString(grid));
    }



    private void initializeGrid(String location) {
        try {
            File matrix = new File(location);
            Scanner scanner = new Scanner(matrix);
            scanner.nextLine();
            int number = 1;
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    if(scanner.hasNext()) {
                        int elementInfo = Integer.parseInt(scanner.next());
                        GridElement element = new GridElement(i, j);
                        switch (elementInfo) {
                            case 1 -> element.isWall = true;
                            case 2 -> {
                                element.isStart = true;
                                start = element;
                                element.isPath = true;
                            }
                            case 3 -> {
                                element.isGoal = true;
                                goal = element;
                                element.isPath = true;
                            }
                        }
                        element.number = number++;
                        grid[i][j] = element;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int[] getDimensions(String location) {
        String dimensions = null;
        int rows = 0, cols = 0;
        try {
            File matrix = new File(location);
            Scanner scanner = new Scanner(matrix);
            dimensions = scanner.nextLine();
            rows = Integer.parseInt(dimensions.split(" ")[0]);
            cols = Integer.parseInt(dimensions.split(" ")[1]);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new int[]{rows, cols};
    }

    private void calculateHeuristic(){
        GridElement current;
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                current = grid[i][j];
                if(!(current.isGoal || current.isWall || current.isStart)){
                    current.hScore = Math.abs(current.i - goal.i) + Math.abs(current.j - goal.j);
                }
            }
        }
    }
}