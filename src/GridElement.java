import java.util.ArrayList;

public class GridElement {
    int i, j,gScore, hScore, fScore, number;
    ArrayList<GridElement> neighbours = new ArrayList<>();
    boolean isWall, isStart, isGoal, isPath;
    GridElement parent;

    public GridElement(int i, int j){
        this.i = i;
        this.j = j;
        this.fScore = 0;
        this.gScore = 0;
        this.hScore = 0;
        this.isWall = false;
        this.isStart = false;
        this.isGoal = false;
        this.parent = null;
        this.isPath = false;
        this.number = 0;
    }

    @Override
    public String toString() {
        return number + ": " + fScore;
    }

    public void findNeighbours(GridElement[][] grid){
        var rows = grid.length;
        var cols = grid[0].length;
        if(j+1 != cols){
            this.neighbours.add(grid[i][j+1]);
        }
        if(i+1 != rows){
            this.neighbours.add(grid[i+1][j]);
        }
        if(j-1 > 0){
            this.neighbours.add(grid[i][j-1]);
        }
        if(i-1 > 0){
            this.neighbours.add(grid[i-1][j]);
        }
    }
}