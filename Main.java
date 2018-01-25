import java.util.*;

/**
 *
 * @author vinnyfiore
 */

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        //The next few lines are for inputting the array for testing.
        //Input number of rows, then columns, then each element in the array.
        int limit0 = kb.nextInt();
        int limit1 = kb.nextInt();
        int[][] arr = new int[limit0][limit1];
        for(int i = 0;i < limit0;i++){
            for(int j = 0; j < limit1;j++){
                arr[i][j] = kb.nextInt();
            }
        }

        //Calculates the middle of the array.
        int[] middle = calcMiddle(arr);
        //Starts the process.
        int numOfCarrotsEaten = startTraverse(arr, middle);
        //Prints the number.
        System.out.println(numOfCarrotsEaten + "");
    }

    //Calculates the middle point.
    public static int[] calcMiddle(int[][] arr){
        int[] returnArray = new int[2];
        int rowLength = arr.length;
        int columnLength= arr[0].length;

        //If both the row and column length are odd.
        if (rowLength%2==1 && columnLength%2==1){
            returnArray[0]= rowLength/2;
            returnArray[1]= columnLength/2;
        } //If only row length is odd.
        else if (rowLength%2==1 && columnLength%2==0){
            returnArray[0]= rowLength/2;
            if (arr[returnArray[0]][(columnLength/2)] > arr[returnArray[0]][(columnLength/2)-1] ){
                returnArray[1] = (columnLength/2);
            }
            else{
                returnArray[1] = (columnLength/2)-1;
            }
        } //If only column length is odd.
        else if (rowLength%2==0 && columnLength%2==1){
            returnArray[1]= columnLength/2;
            if (arr[(rowLength/2)][returnArray[1]] > arr[(rowLength/2)-1][returnArray[1]] ){
                returnArray[0] = (rowLength/2);
            }
            else{
                returnArray[0] = (rowLength/2)-1;
            }
        }
        else{ //If both row and column length are even. This checks the 4 possible middles and then returns whichever is largest.
            int biggestCell = 0;
            int biggestCellNum = 0;
            if (arr[(rowLength/2)][(columnLength/2)] > biggestCell ){
                biggestCell = arr[(rowLength/2)][(columnLength/2)];
                biggestCellNum = 1;
            }
            else if (arr[(rowLength/2)][(columnLength/2)-1] > biggestCell ){
                biggestCell = arr[(rowLength/2)][(columnLength/2)-1];
                biggestCellNum = 2;
            }
            else if (arr[(rowLength/2)-1][(columnLength/2)] > biggestCell ){
                biggestCell = arr[(rowLength/2)-1][(columnLength/2)];
                biggestCellNum = 3;
            }
            else if (arr[(rowLength/2)-1][(columnLength/2)-1] > biggestCell ){
                biggestCell = arr[(rowLength/2)-1][(columnLength/2)-1];
                biggestCellNum = 4;
            }
            switch (biggestCellNum){
                case 1:
                    returnArray[0] = (rowLength/2);
                    returnArray[1] = (columnLength/2);
                    break;
                case 2:
                    returnArray[0] = (rowLength/2);
                    returnArray[1] = (columnLength/2)-1;
                    break;
                case 3:
                    returnArray[0] = (rowLength/2)-1;
                    returnArray[1] = (columnLength/2);
                    break;
                case 4:
                    returnArray[0] = (rowLength/2)-1;
                    returnArray[1] = (columnLength/2)-1;
                    break;
            }
        }

        return returnArray;
    }

    //Completes the traversal.
    public static int startTraverse(int [][] arr, int[] middles){
        int answer =0;

        //Adds the first calculated middle to the final answer.
        answer = answer + arr[middles[0]][middles[1]];

        //Determines which direction to move. Returns x if there is no valid direction. This terminates the traversal.
        char directionToMove = 'z';
        while('x' != directionToMove){
            int tempN = 0;
            int tempW = 0;
            int tempS = 0;
            int tempE = 0;
            //Checks if the move is within the confines of the 2dArray, then grabs all values to find the highest.
            if (middles[0]-1 < arr[0].length && middles[0]-1 >= 0){
                tempN = arr[middles[0]-1][middles[1]];
            }
            if (middles[1]-1 < arr[0].length && middles[1]-1 >= 0){
                tempW = arr[middles[0]][middles[1]-1];
            }
            if (middles[0]+1 < arr[1].length && middles[0]+1 >= 0){
                tempS = arr[middles[0]+1][middles[1]];
            }
            if (middles[1]+1 < arr[1].length && middles[1]+1 >= 0){
                tempE = arr[middles[0]][middles[1]+1];
            }

            //Sets the current position to 0 before leaving.
            arr[middles[0]][middles[1]]=0;
            //Calls checkSides to see which is largest.
            directionToMove = checkSides(tempN, tempW, tempS, tempE);

            //Moves to the determined direction, north/south/east/west.
            switch(directionToMove){
                case 'n':
                    middles[0]= middles[0]-1;
                    answer = answer + arr[middles[0]][middles[1]];
                    arr[middles[0]][middles[1]]=0;
                    break;
                case 'w':
                    middles[1]= middles[1]-1;
                    answer = answer + arr[middles[0]][middles[1]];
                    arr[middles[0]][middles[1]]=0;
                    break;
                case 's':
                    middles[0]= middles[0]+1;
                    answer = answer + arr[middles[0]][middles[1]];
                    arr[middles[0]][middles[1]]=0;
                    break;
                case 'e':
                    middles[1]= middles[1]+1;
                    answer = answer + arr[middles[0]][middles[1]];
                    arr[middles[0]][middles[1]]=0;
                    break;
                case 'x':
                    break;
            }
        }

        return answer;
    }

    public static char checkSides (int n, int w, int s, int e) {
        //Returns the direction of the largest neighbor.
        int largest = 0;
        char direction = 'x';
        if (n > largest){
            largest = n;
            direction = 'n';
        }
        if (w > largest){
            largest = w;
            direction = 'w';
        }
        if (s > largest){
            largest = s;
            direction = 's';
        }
        if (e > largest){
            largest = e;
            direction = 'e';
        }
        if (n+w+s+e == 0){
            direction = 'x';
        }

        return direction;
    }
}

