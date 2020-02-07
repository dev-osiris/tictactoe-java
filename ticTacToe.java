package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ticTacToe {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<String> row1 = Arrays.asList(".", ".", ".");
        List<String> row2 = Arrays.asList(".", ".", ".");
        List<String> row3 = Arrays.asList(".", ".", ".");
        ArrayList<List<String>> arr;
        List<String> check  = new ArrayList<>();
        int count = 0;
        String var;
        System.out.println("\t\t\t\tTIC-TAC-TOE");
        printGrid(row1);
        printGrid(row2);
        printGrid(row3);

        while (row1.contains(".") || row2.contains(".") || row3.contains(".")) {
            if (count % 2 == 0)
                var = "x";
            else
                var = "o";

            System.out.println("turn of " + var);
            System.out.print("row: ");
            int row_index = reader.nextInt();

            System.out.print("column: ");
            int col_index = reader.nextInt();
            String  s = (Integer.toString(row_index) + col_index);

            if (check.contains(s)){
                System.out.println("\nvalue already entered at that position, try again\n");
                continue;
            }
            check.add(s);

            arr = makeGrid(row_index, col_index, row1, row2, row3, var);
            if(arr == null) {
                System.out.println("enter a valid value!");
                continue;
            }
            List<String> firstRow = arr.get(0);
            List<String> secondRow = arr.get(1);
            List<String> thirdRow = arr.get(2);

            printGrid(firstRow);
            printGrid(secondRow);
            printGrid(thirdRow);

            horizontalMatching(row1, row2, row3, var);
            verticalMatching(row1, row2, row3, var);
            diagonalMatching(row1, row2, row3, var);

            count += 1;
        }
        System.out.println("MATCH DRAW");
    }


    static ArrayList<List<String>> makeGrid(int row, int col, List<String> row1, List<String> row2, List<String> row3, String value ){
        ArrayList<List<String>> arr = new ArrayList<>();
        if(row == 1){
            row1.set(col - 1, value);
        }
        else if(row == 2){
            row2.set(col - 1, value);
        }
        else if(row == 3){
            row3.set(col - 1, value);
        }
        else
            return null;
        arr.add(row1);
        arr.add(row2);
        arr.add(row3);
        return arr;
    }


    static void horizontalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value){
        if (firstRow.get(0).equals(value) && firstRow.get(1).equals(value) && firstRow.get(2).equals(value))
        {
            System.out.println("you won!");
            System.exit(0);
        }
        if (secondRow.get(0).equals(value) && secondRow.get(1).equals(value) && secondRow.get(2).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }

        if (thirdRow.get(0).equals(value) && thirdRow.get(1).equals(value) && thirdRow.get(2).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
    }


    static void verticalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value){
        if (firstRow.get(0).equals(value) && secondRow.get(0).equals(value) && thirdRow.get(0).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
        if (firstRow.get(1).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(1).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
        if (firstRow.get(2).equals(value) && secondRow.get(2).equals(value) && thirdRow.get(2).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
    }


    static void diagonalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value){
        if (firstRow.get(0).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(2).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
        if (firstRow.get(2).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(0).equals(value)){

            System.out.println("you won!");
            System.exit(0);
        }
    }


    static void printGrid(List<String> row){
        System.out.printf("%s | %s | %s \n", row.get(0), row.get(1), row.get(2));
    }
}
