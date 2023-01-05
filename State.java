import java.util.ArrayList;

public class State {
    private int k;
    //the state of the puzzle, represented as a 2d matrix can contain two digits for size 10 and above, and one asterisk for the empty space
    private String[][] state;
    public State(String initial, int k) {
        int n = (int) Math.sqrt(k);
        this.k = k;
        state = new String[n][n];
        int index = 0;
        //split the initial string
        String[] split = initial.split(" ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = split[index];
                index++;
            }
        }
    }

    public State getGoalState() {
        String goal = "";
        for (int i = 1; i < k; i++) {
            if (i < 10) {
                goal += i + " ";
            } else {
                goal += i;
            }
        }
        goal += "*";
        return new State(goal, k);
    }

    //return the empty space row and column simultaneously
    public int[] getEmptySpace() {
        int[] emptySpace = new int[2];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j].equals("*")) {
                    emptySpace[0] = i;
                    emptySpace[1] = j;
                }
            }
        }
        return emptySpace;
    }

    public int getHeuristicValue() {
        int value = 0;
        int n = (int) Math.sqrt(k);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!state[i][j].equals("*")) {
                    int number = Integer.parseInt(state[i][j]);
                    int row = (number - 1) / n;
                    int column = (number - 1) % n;
                    value += Math.abs(row - i) + Math.abs(column - j);
                }
            }
        }
        return value;
    }

    //override to print the state of the puzzle
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                result += state[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public ArrayList<State> createChildren() {
        //if the empty space is in the corner
        int[] emptySpace = getEmptySpace();
        int row = emptySpace[0];
        int column = emptySpace[1];
        ArrayList<State> children = new ArrayList<>();
        if (row == 0 && column == 0) {
            //create the right and down child states
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        } else if (row == 0 && column == state.length - 1) {
            //create the left and down child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        } else if (row == state.length - 1 && column == 0) {
            //create the right and up child states
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
        } else if (row == state.length - 1 && column == state.length - 1) {
            //create the left and up child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
        } //if the empty space is in the edge
        else if (row == 0) {
            //create the left, right and down child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        } else if (row == state.length - 1) {
            //create the left, right and up child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
        } else if (column == 0) {
            //create the right, up and down child states
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        } else if (column == state.length - 1) {
            //create the left, up and down child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        } //if the empty space is in the middle
        else {
            //create the left, right, up and down child states
            State left = new State(this.toString(), k);
            left.state[row][column] = left.state[row][column - 1];
            left.state[row][column - 1] = "*";
            children.add(left);
            State right = new State(this.toString(), k);
            right.state[row][column] = right.state[row][column + 1];
            right.state[row][column + 1] = "*";
            children.add(right);
            State up = new State(this.toString(), k);
            up.state[row][column] = up.state[row - 1][column];
            up.state[row - 1][column] = "*";
            children.add(up);
            State down = new State(this.toString(), k);
            down.state[row][column] = down.state[row + 1][column];
            down.state[row + 1][column] = "*";
            children.add(down);
        }
        return children;
    }
}
