import java.io.*;
import java.util.Random;
import java.util.Arrays;

class quickSort_Rec{
    public static int[] tempArray;
    Random random;
    public static final int RANDOM_NUMS = 10;
    
    public quickSort_Rec(){
        tempArray = new int[10];
        random = new Random();
        fill(10);
    }

    public quickSort_Rec(int size){
        tempArray = new int[size];
        random = new Random();
        fill(size);
    }

    private void printArray(){
        System.out.println(Arrays.toString(tempArray));
    }

    private void rec_sort(int arr[], int start, int end){
        int pivot_value = part(arr,start,end);

        if((pivot_value-start) < (end-pivot_value)){
            if(start < pivot_value-1){
                sort(arr,start,pivot_value-1);
            }
            if(pivot_value+1 < end){
                sort(arr,pivot_value+1,end);
            }
        }else{
            if(pivot_value+1 < end){
                sort(arr,pivot_value+1,end);
            }
            if(start < pivot_value-1){
                sort(arr,start,pivot_value-1);
            }
        }
    }

    private int part(int arr[], int left, int right){
        int i = left;
        int j = right;

        int piv_element = ((left+right)/2);
        swapper(arr, left, piv_element);
        int pivot = arr[left];
        i++;

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

    void fill(int numbers){
        Random random = new Random();
        for(int i=0;i<tempArray.length;i++){
            
            tempArray[i] = random.nextInt(numbers);
        }
    }

    private static void swapper(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static public void main(String args[]){

        quickSort_Rec newArray = new quickSort_Rec(10);
        // newArray.printArray();

        long startTime = System.nanoTime();
        newArray.sort(newArray.tempArray, 0, (newArray.tempArray.length-1));
        long endTime = System.nanoTime();
        // newArray.printArray();

        long timeElapsed = endTime - startTime;
        System.out.println("Time taken to sort: " + timeElapsed + "ns");
    }
};