public class App {
    public static void main(String[] args) throws Exception {
        Maze maze = new Maze("D:\\TY\\AI\\Lab\\Path_Finding_Algorithms\\grid.txt");
        AStar aStar = new AStar(maze);
        aStar.findPath();
    }
}
