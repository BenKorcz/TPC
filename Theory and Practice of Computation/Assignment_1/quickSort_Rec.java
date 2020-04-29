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

    private void sort(int arr[], int low, int high){
        int part_ind = part(arr,low,high);

        if((part_ind-low) < (high-part_ind)){
            if(low < part_ind-1){
                sort(arr,low,part_ind-1);
            }
            if(part_ind < high){
                sort(arr,part_ind,high);
            }
        }else{
            if(part_ind < high){
                sort(arr,part_ind,high);
            }
            if(low < part_ind-1){
                sort(arr,low,part_ind-1);
            }
        }
    }

    private int part(int arr[], int low, int high){
        int i = low;
        int j = high;

        int piv_element = ((low+high)/2);
        int pivot = arr[piv_element];

        while(i <= j){
            while(arr[i] < pivot){
                i++;
            }
            while(arr[j] > pivot){
                j--;
            }
            if(i <= j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        return i;
    }

    void fill(int numbers){
        Random random = new Random();
        for(int i=0;i<tempArray.length;i++){
            
            tempArray[i] = random.nextInt(numbers);
        }
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