package com.revature.strings.foundationsProject;
import java.util.Scanner;
public class Application {

    public static void main(String[] args) {

        //create scanner object
        Scanner sc = new Scanner(System.in);

        //Prompt user to log in, Register, or Exit
        System.out.println("-------------------------------------------");
        System.out.println("Enter 1 to Log in, 2 to Register, 3 to Exit");
        System.out.println("-------------------------------------------");
        String choice = sc.nextLine();

        if (choice.equals("1")){

            System.out.println("Please enter your username:");
            String username = sc.nextLine();
            System.out.println("Please enter your password:");
            String password = sc.nextLine();
            //next we would implement checking the postgresql database to match the information

        } else if (choice.equals("2")){
            System.out.println("Please enter your desired username:");
            String username = sc.nextLine();
            System.out.println("Please enter your desired password:");
            String password = sc.nextLine();

            //Here is where I would pass the username/pass to the database

        } else if (choice.equals("3")){

            System.exit(0);

        }


    }
}