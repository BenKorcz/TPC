import java.util.Scanner;

public class blab{
    public static void main(String[] args){
        Scanner input=null;
        try{
            int number;
            input = new Scanner(System.in);

            System.out.println("message?");
            String message = input.nextLine();

            System.out.println("repeats?");
            number = input.nextInt();

            for(int i=0;i<number;i++){
                System.out.println(message);
            }
        }
        finally{
            if(input!=null){
                input.close();
            }
        }
        
    }
}