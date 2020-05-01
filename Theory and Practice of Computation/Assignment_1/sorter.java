import java.util.Random;
import java.util.Arrays;
// import java.util.Stack;

public class sorter{
    Random random;
    // public static final int RANDOM_NUMS = 100;
    private static int[] unsortedArray; //Use a static array to hold all of the arrays that are sorted to conserve the memory
    private static int[] copyOf; //A copy array is also held in static memory
    private static int[] copyOf_2;

    static public void main(String args[]){
        // int[] unsortedArray = new int[]{9,9,9,8,7,6,5,3,2,0};
        // int[] unsortedArray = new int[]{0,0,0,0,0,0,0,0,0,0};
        // int[] unsortedArray = new int[]{1,2,3,4,5,5,6,7,8,9};


        int[] printingUnsorted = fill(new int[10], 10);
        System.out.println("The unsorted array of 10 elements is: ");
        printArray(printingUnsorted);
        int[] sortTester_Iter = printingUnsorted.clone();
        int[] sortTester_Rec = printingUnsorted.clone();

        iter_sort(sortTester_Iter);
        rec_sort(sortTester_Rec, 0, sortTester_Rec.length-1);

        
        System.out.println("After the iterative sort, the array is now: ");
        printArray(sortTester_Iter);
        System.out.println("After the recursive sort, the array is now: ");
        printArray(sortTester_Rec);

        if(isSorted(sortTester_Iter) && isSorted(sortTester_Rec)){
            System.out.println("The arrays have been checked and they are both sorted.");
        }else{
            System.out.println("The arrays were not sorted properly.");
        }

        tester(10);
        tester(100);
        tester(1000);
        tester(10000);
        tester(100000);
        tester(1000000);

    }

    /*
    Helper function to check if the array has been sorted
    Returns false if a number is ever bigger than the next number in the array.
    */
    private static boolean isSorted(int[] toBeChecked){
        for(int i=0;i<toBeChecked.length-1;i++){
            if(toBeChecked[i] > toBeChecked[i+1]){
                return false;
            }
        }
        return true;
    }
    //Tester function, the size will dictate how elements the array will have. The unsorted array is filled with random numbers.
    private static void tester(int size){
        int times_to_run = 1000000/size;
        double runs = times_to_run/1.0;
        long runningTotal_iter=0, runningTotal_rec=0, runningTotal_builtIn=0;

        //Each loop the array is filled with new random numbers, a clone of the array is made and held in a new array.
        //The start time is taken just before the array is sorted, this is to ensure there is no extra time wasted with the setup of the array.
        for(int i=0;i<times_to_run;i++){
            unsortedArray = fill(new int[size], size);

            /*
            Copied array is so that both the recursive sort and the iterative sort are performed on the same datasets.
            This is so that the times produced can be properly compared to each other for better benchmarking and removes
            the randomness of the arrays which may affect the running time of the algorithm.
            */
            copyOf = unsortedArray.clone(); 
            copyOf_2 = unsortedArray.clone(); 

            long startTime_iter = System.nanoTime();
            iter_sort(unsortedArray);
            long endTime_iter = System.nanoTime();

            long startTime_rec = System.nanoTime();
            rec_sort(copyOf,0,size-1);
            long endTime_rec = System.nanoTime();

            long startTime_builtIn = System.nanoTime();
            Arrays.sort(copyOf_2);
            long endTime_builtIn = System.nanoTime();

            //Holds a running total of the runtime in Nanoseconds
            long timeElapsed_iter = endTime_iter - startTime_iter;
            runningTotal_iter+=timeElapsed_iter; 
            long timeElapsed_rec = endTime_rec - startTime_rec;
            runningTotal_rec+=timeElapsed_rec;
            long timeElapsed_builtIn = endTime_builtIn - startTime_builtIn;
            runningTotal_builtIn+=timeElapsed_builtIn;

        }

        double averageTime_rec = runningTotal_rec/runs;
        System.out.println("The average time taken to sort " + size + " items for each sort using recursive sort was: " + String.format("%.2f", averageTime_rec) + "ns");
        double averageTime_iter = runningTotal_iter/runs;
        System.out.println("The average time taken to sort " + size + " items for each sort using iterative sort was: " + String.format("%.2f", averageTime_iter) + "ns");
        double averageTime_builtIn = runningTotal_builtIn/runs;
        System.out.println("The average time taken to sort " + size + " items for each sort using the Arrays.sort() method was: " + String.format("%.2f", averageTime_builtIn) + "ns");
    }

    /*
    Private helper function to print the array, was used mostly for debugging throughout the code rather than re-writing the system out every time
    */
    private static void printArray(int[] printable){
        System.out.println(Arrays.toString(printable));
    }

    /*
    The iterative quicksort function, the Stack implementation has been left in comments to help show
    the transition between the Stack and an array solution.
    */
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
            // printArray(arr);
            //push the smaller sub-array on first
            if((pivot_value-start) < (end-pivot_value)){
                if(pivot_value+1 < end){
                    topStack++;
                    stack[topStack] = pivot_value+1;
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
                if(pivot_value+1 < end){
                    topStack++;
                    stack[topStack] = pivot_value+1;
                    topStack++;
                    stack[topStack] = end;
                    // stack.push(pivot_value);
                    // stack.push(end);
                }
            }
        }
    }

    /* 
    Recursive function for quicksort, the smaller partition is always sorted first, this is to reduce the number of items on the stack for memory.
    The recursive function takes the array being sorted and the start/end values as arguments.
    */
    private static void rec_sort(int arr[], int start, int end){
        int pivot_value = part(arr,start,end);

        if((pivot_value-start) < (end-pivot_value)){
            if(start < pivot_value-1){
                rec_sort(arr,start,pivot_value-1);
            }
            if(pivot_value+1 < end){
                rec_sort(arr,pivot_value+1,end);
            }
        }else{
            if(pivot_value+1 < end){
                rec_sort(arr,pivot_value+1,end);
            }
            if(start < pivot_value-1){
                rec_sort(arr,start,pivot_value-1);
            }
        }
    }

    /*
    The partition function that is commonly used between the recursive sort and the iterative sorts
    There is no need to use a different function as the partition algorithm does the same, the difference
    between the algorithms is in the splitting of the subarrays still needing to be sorted.
    */
    private static int part(int arr[], int left, int right){
        int i = left;
        int j = right;

        /*
        The middle of the array is chosen as the pivot element
        It is then swapped to the beginning, this is to reduce the chances the array is in reverse order
        or is fully sorted.
        */
        int piv_element = ((left+right)/2);
        swapper(arr, left, piv_element);
        int pivot = arr[left];
        i++;

        /*
        Code that follows the algorithm provided for the assignment, there are checks in place 
        for the i and j variables, these are to stop the fringe cases where the variable would 
        go outside past the left and right bounds due to the checks. In this case it was checking
        against elements that may have already been sorted previously or gone outside the bounds
        of the array which caused a segmentation fault.
        */
        while(i <= j){
            while(arr[i]<=pivot){
                i++;
                if(i>(arr.length-1)){
                    break;
                }
            }
            while(arr[j]>=pivot){
                j--;
                if(j<=left){
                    break;
                }
            }
            if(i < j){
                swapper(arr,i,j);
                i++;
                j--;
            }
        }

        if(arr[j]<pivot){
            swapper(arr, j, left);
            left = j;
        }
        return left;
    }

    /*
    Helper Function to swap elements in an array, since there were multiple places this code was implemented
    it made more sense to put it in a function.
    */
    private static void swapper(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /*
    Function to fill the arrays with random numbers, the random numbers have the same range as the 
    number of elements as the array being sorted.

    */
    public static int[] fill(int[] empty, int nums){
        Random random = new Random();
        for(int i=0;i<nums;i++){
            empty[i] = random.nextInt(nums);
        }
        return empty;
    }

};