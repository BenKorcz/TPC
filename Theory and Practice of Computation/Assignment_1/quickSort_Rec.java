import java.io.*;
import java.util.Random;
import java.util.Arrays;

class quickSortArray{
    public static int[] tempArray;
    Random random;
    public static final int RANDOM_NUMS = 10;
    
    public quickSortArray(){
        tempArray = new int[10];
        random = new Random();
        fill();
    }

    public quickSortArray(int size){
        tempArray = new int[size];
        random = new Random();
        fill();
    }

    private void printArray(){
        System.out.println(Arrays.toString(tempArray));
    }

    private void sort(int arr[], int low, int high){
        int part_ind = part(arr,low,high);

        if(low < part_ind-1){
            sort(arr,low,part_ind-1);
        }
        if(part_ind < high){
            sort(arr,part_ind,high);
        }
    }

    private int part(int arr[], int low, int high){
        int i = low;
        int j = high;

        int piv_element = random.nextInt(high-low) + low;
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
        // int i = (low-1);
        // for(int j=low;j<high;j++){
        //     if(arr[j]<=pivot){
        //         i++;

        //         int temp = arr[i];
        //         arr[i] = arr[j];
        //         arr[j] = temp;
        //     }
        // }

        // int temp = arr[i+1];
        // arr[i+1] = arr[high];
        // arr[high] = temp;

        return i;
    }

    void fill(){
        Random random = new Random();
        for(int i=0;i<tempArray.length;i++){
            
            tempArray[i] = random.nextInt(RANDOM_NUMS);
        }
    }
    static public void main(String args[]){
        quickSortArray newArray = new quickSortArray(10);
        newArray.printArray();
        newArray.sort(newArray.tempArray, 0, (newArray.tempArray.length-1));
        newArray.printArray();
    }
};