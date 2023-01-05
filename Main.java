

//Solving 8-puzzle problem
//using A* search algorithm
//n=8/15/....
//n=k-1

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //first input is the size of the puzzle, k
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        input.nextLine();
        //second input is the initial state of the puzzle, with one * representing the empty space
        String initial = input.nextLine();
        State initialState = new State(initial, k);

        //a priority queue to store the states
        AStarPriorityQueue queue = new AStarPriorityQueue();
        //add the initial state to the queue
        queue.add(initialState);
        //the number of steps
        int step = 0;
        //remove the state with the lowest heuristic value
        //and print the state
            State element = queue.remove(step);
            System.out.println(element);
            step++;
    }
}