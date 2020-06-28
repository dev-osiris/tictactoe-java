import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class ticTacToe {

    public static void main(String[] args)  throws  Exception{
        Scanner reader = new Scanner(System.in);
        List<String> row1 = Arrays.asList(".", ".", ".");
        List<String> row2 = Arrays.asList(".", ".", ".");
        List<String> row3 = Arrays.asList(".", ".", ".");
        ArrayList<List<String>> arr;
        List<String> check  = new ArrayList<>();
        int count = 0, row_index;
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
            System.out.println("turn of '" + var + "'");
            System.out.print("row: ");
            row_index = reader.nextInt();

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
        addToDatabase("", true);
    }

    static void addToDatabase(String winner, boolean isDraw) throws  Exception{
        String winQuery, drawQuery;
        String URL = "jdbc:mysql://localhost:3306/tictactoe_db";
        String PASSWORD = "NickelcoreSlimycanRockme";
        String USERNAME = "root";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        winQuery = "INSERT INTO game_data VALUES(DEFAULT, 'WIN', ?, ?, ?)";
        drawQuery = "INSERT INTO game_data VALUES(DEFAULT, 'DRAW', NULL, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(!isDraw) {
                System.out.println("you won!\n");
                System.out.println("adding result to database...");

                PreparedStatement st = con.prepareStatement(winQuery);
                st.setString(1, winner);
                st.setString(2, dateFormat.format(date));
                st.setString(3, timeFormat.format(date));
                st.executeUpdate();
                st.close();
            }
            else {                                                  //game resulted in draw
                System.out.println("adding result to database...");
                PreparedStatement st = con.prepareStatement(drawQuery);
                st.setString(1, dateFormat.format(date));
                st.setString(
                        2, timeFormat.format(date));
                st.executeUpdate();
                st.close();
            }
            con.close();


        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.print("done\n");
        System.exit(0);
    }

    static ArrayList<List<String>> makeGrid(int row, int col, List<String> row1, List<String> row2, List<String> row3, String value ) {
        ArrayList<List<String>> arr = new ArrayList<>();
        if (row == 1) {
            row1.set(col - 1, value);
        } else if (row == 2) {
            row2.set(col - 1, value);
        } else if (row == 3) {
            row3.set(col - 1, value);
        } else
            return null;
        arr.add(row1);
        arr.add(row2);
        arr.add(row3);
        return arr;
    }

    static void horizontalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value) throws Exception {

        if (firstRow.get(0).equals(value) && firstRow.get(1).equals(value) && firstRow.get(2).equals(value))
        {
            addToDatabase(value, false);
        }
        if (secondRow.get(0).equals(value) && secondRow.get(1).equals(value) && secondRow.get(2).equals(value)){

            addToDatabase(value, false);
        }

        if (thirdRow.get(0).equals(value) && thirdRow.get(1).equals(value) && thirdRow.get(2).equals(value)){

            addToDatabase(value, false);
        }
    }


    static void verticalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value) throws Exception {
        if (firstRow.get(0).equals(value) && secondRow.get(0).equals(value) && thirdRow.get(0).equals(value)){

            addToDatabase(value, false);
        }
        if (firstRow.get(1).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(1).equals(value)){

            addToDatabase(value, false);
        }
        if (firstRow.get(2).equals(value) && secondRow.get(2).equals(value) && thirdRow.get(2).equals(value)){

            addToDatabase(value, false);
        }
    }


    static void diagonalMatching(List<String> firstRow, List<String> secondRow, List<String> thirdRow, String value) throws Exception {
        if (firstRow.get(0).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(2).equals(value)){

            addToDatabase(value, false);
        }
        if (firstRow.get(2).equals(value) && secondRow.get(1).equals(value) && thirdRow.get(0).equals(value)){

            addToDatabase(value, false);
        }
    }


    static void printGrid(List<String> row){
        System.out.printf("%s | %s | %s \n", row.get(0), row.get(1), row.get(2));
    }
}
