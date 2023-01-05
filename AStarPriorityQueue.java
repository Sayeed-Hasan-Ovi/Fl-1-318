import java.util.ArrayList;

//a priority queue to store the states
//the priority is the heuristic value of the state
//the heuristic value is the number of misplaced tiles
//and manhattan distance

public class AStarPriorityQueue{
    private ArrayList<State> queue;
    private ArrayList<Integer> priority;

    public AStarPriorityQueue() {
        queue = new ArrayList<>();
        priority = new ArrayList<>();
    }

    public State remove(int step) {
        State element = queue.get(0);
        if (element.getHeuristicValue() == 0) {
            System.out.println("Goal state found!");
            System.out.println("Number of steps: " + step);
            System.out.println(element);
            System.exit(0);
        }
        queue.set(0, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        priority.set(0, priority.get(priority.size() - 1));
        priority.remove(priority.size() - 1);
        int index = 0;
        while (index < queue.size()) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            if (left >= queue.size()) {
                break;
            }
            int min = left;
            if (right < queue.size()) {
                if (priority.get(right) < priority.get(left)) {
                    min = right;
                }
            }
            if (priority.get(index) > priority.get(min)) {
                swap(index, min);
                index = min;
            } else {
                break;
            }
        }
        //create the transition child states
        //an array list to store the child states
        ArrayList<State> children = new ArrayList<>();
        children = element.createChildren();
        for (State child : children) {
            add(child, child.getHeuristicValue() + step);
        }
        return element;
    }

    public void add(State element) {
        if (queue.isEmpty()) {
            add(element, element.getHeuristicValue());
        }
    }

    //min heap
    private void add(State state, int value) {
        queue.add(state);
        priority.add(state.getHeuristicValue());
        int index = queue.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (priority.get(index) < priority.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int index, int parent) {
        State tempState = queue.get(index);
        queue.set(index, queue.get(parent));
        queue.set(parent, tempState);
        int tempPriority = priority.get(index);
        priority.set(index, priority.get(parent));
        priority.set(parent, tempPriority);
    }
}