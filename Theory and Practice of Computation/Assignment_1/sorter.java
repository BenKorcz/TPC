// import java.io.*;
// import java.math.*;
import java.util.Random;
import java.util.Arrays;
// import java.util.Stack;

public class sorter{
    Random random;
    public static final int RANDOM_NUMS = 10;

    static public void main(String args[]){
        
        int[] unsortedArray = fill(new int[RANDOM_NUMS]);
        printArray(unsortedArray);
        long startTime = System.nanoTime();
        
        iter_sort(unsortedArray);
        long endTime = System.nanoTime();


        long timeElapsed = endTime - startTime;
        System.out.println("Time taken to sort: " + timeElapsed + "ns");

        printArray(unsortedArray);
    }

    //Private function to print the array, was used mostly for debugging throughout the code rather than re-writing the system out every time
    private static void printArray(int[] printable){
        System.out.println(Arrays.toString(printable));
    }

    //the iterative quicksort function
    private static void iter_sort(int[] arr){
        //Assign a new int array that will hold the pairs for the partition, the stack has been given a size of 64 elements
        //This is enough to store 32 pairs (end,start) to tell the algorithm which part to sort next
        //32 pairs would mean there would be 32 times the program has halved the array, this is 2^32 elements, since the maximum number of elements we are testing is 1000000
        //this should be more than enough to sort even a fully sorted or reverse order array.
        int stack[] = new int[64];
        int topStack = -1;  //Start below the first element for consistency as all calls to "pop" or "push" an element will be similar to the below format
                            //This variable is used to keep track of where the top of the stack currently points to.

        topStack++;
        stack[topStack] = 0; //Push in the start of the array, i.e. the first element and then add 1 to the stacks current position tracker
        topStack++;
        stack[topStack] = arr.length-1;
        
        // Stack<Integer> stack = new Stack<Integer>();
        // stack.push(0);
        // stack.push(arr.length-1);

        int end, start, pivot_value; //Initialise variables to be used in the loop, this ensures that a new copy isnt created every loop

        //while there are still items on the stack continue to loop over.
        while(topStack >= 0){
        // while(!stack.isEmpty()){
            //get the current start and end value to sort
           
            // int end = (Integer)(stack.pop());
            // int start = (Integer)(stack.pop());

            end = stack[topStack]; //The end is the element at the top of the stack so it is "popped" and the tracker is pushed down
            topStack--;
            start = stack[topStack];
            topStack--;
            //choose a partition value that is between the start and end value
            pivot_value = part(arr,start,end);
            //push the smaller sub-array on first
            if((pivot_value-start) < (end-pivot_value)){
                if(pivot_value < end){
                    topStack++;
                    stack[topStack] = pivot_value;
                    topStack++;
                    stack[topStack] = end;
                    // stack.push(pivot_value);
                    // stack.push(end);
                }
                if(start < pivot_value-1){
                    topStack++;
                    stack[topStack] = start;
                    topStack++;
                    stack[topStack] = pivot_value-1;
                    // stack.push(start);
                    // stack.push(pivot_value-1);   
                }

            }else{
                if(start < pivot_value-1){
                    topStack++;
                    stack[topStack] = start;
                    topStack++;
                    stack[topStack] = pivot_value-1;
                    // stack.push(start);
                    // stack.push(pivot_value-1);   
                }
                if(pivot_value < end){
                    topStack++;
                    stack[topStack] = pivot_value;
                    topStack++;
                    stack[topStack] = end;
                    // stack.push(pivot_value);
                    // stack.push(end);
                }
            }
        }
    }

    private static int part(int arr[], int left, int right){
        int i = left;
        int j = right;

        int piv_element = ((left+right)/2);
        swapper(arr, left, piv_element);
        int pivot = arr[left];

        while(i <= j){
            while(arr[i]<pivot){
                i++;
            }
            while(arr[j]>pivot){
                j--;
            }
            if(i <= j){
                swapper(arr,i,j);
                i++;
                j--;
            }
        }

        return i;
    }

    private static void swapper(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int[] fill(int[] empty){
        Random random = new Random();
        for(int i=0;i<RANDOM_NUMS;i++){
            empty[i] = random.nextInt(RANDOM_NUMS);
        }
        return empty;
    }

};