import java.util.*;

public class AStar {
    private Maze maze;
    private PriorityQueue<GridElement> openList;
    private Stack<GridElement> closedList;

    public AStar(Maze maze) {
        this.maze = maze;
    }

    public void findPath(){
        GridElement[][] grid = maze.getGrid();
        GridElement start = maze.getStart();
        GridElement goal = maze.getGoal();
        GridElement current = start;
        closedList = new Stack<>();
        openList = new PriorityQueue<>(new Comparator<GridElement>() {
            @Override
            public int compare(GridElement o1, GridElement o2) {
                return o1.fScore - o2.fScore;
            }
        });
        while(current!=goal || openList.isEmpty()){
            current.findNeighbours(grid);
            closedList.push(current);
            // System.out.println(current.neighbours);
            for(GridElement neighbor : current.neighbours){
                if(!neighbor.isWall && !closedList.contains(neighbor)){
                    if(openList.contains(neighbor)){
                        if(neighbor.fScore > neighbor.gScore + current.gScore+1) {
                            openList.remove(neighbor);
                        }
                    }
                    neighbor.gScore = current.gScore + 1;
                    neighbor.fScore = neighbor.gScore + neighbor.hScore;
                    neighbor.parent = current;
                    openList.add(neighbor);

                }
            }
            // System.out.println("Open list : " + openList);
            // System.out.println("Closed list :" + closedList);
            current = openList.remove();

        }
        GridElement pathElem = goal;
        while (pathElem.parent != null){
            System.out.println(pathElem);
            pathElem = pathElem.parent;
        }

    }
}