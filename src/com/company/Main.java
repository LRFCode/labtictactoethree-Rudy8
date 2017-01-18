package com.company;

import java.util.Scanner;

//Goals
//Type in code to start getting comfortable keying in and looking at Java and using an IDE
//Declare, assign, and get a value back out of a variable
//Write a loop
//Write a function
//Pass a variable into a function
//Get a value back from a function
//Start understanding how to turn human rules into a program

//Each student is expected to complete at least the lessons in Bronze
//Silver and Gold are not required but it will be to your benefit to do them as you pursue a career in software development

//Bronze
//TODO Get player names at start
//TODO Use player names when asking for each move
//TODO Change the code to pass the symbol into the win detection functions
//TODO Currently the program only detects three in a row only horizontally.  Add vertical detection.

//Silver
//TODO Change the code to distinguish between a win and a draw
//TODO Print out which symbol won or the word "Draw" when game over is detected
//TODO Use player name when announcing winner
//TODO Now that the program detects three in a row horizontally and vertically, add diagonal detection.
//TODO Make it so that invalid input no longer loses a turn but allows user to try again
//TODO Only increment move number when a valid move is entered

//Gold
//TODO Pull down Four in a Row template project and implement game meeting all above requirements for tictactoe
//TODO Feel free to copy paste any code from this project you like


public class Main
{
    public static void main(String[] args)
    {
        //The left side of the equal sign says that the variable name ticTacToeBoard refers to a two dimensional array of Strings
        //The right side of the equal sign tells the computer to reserve space to keep track of a 3 by 3 array of Strings
        //The equal sign tells the computer to assign the space it reserved to the variable named ticTacToeBoard
        String[][] ticTacToeBoard = new String[3][3];

        //Call the function clearBoard passing in the ticTacToeBoard array as a variable
        clearBoard(ticTacToeBoard);
        //Now that the clearBoard function has been called all 9 positions contain a space

        //Create a counter to store the move number and set it to 1 to start
        int moveNumber = 1;

        //Create a String to store the symbol for the current player and start it at 'X'
        String symbol = "X";

        //Print out the board right before play starts so the players can see the blank board
        printBoard(ticTacToeBoard);

        System.out.println(" Player X Enter name");
        String playerNameX = getUserInput();
        System.out.println("Player X is " + playerNameX);

        System.out.println(" Player O Enter name");
        String playerNameO = getUserInput();
        System.out.println("Player O is " + playerNameO);

        //There will always be at least one move so a do...while loop can be used
        //This loop, the code between the bracket after the do and before the bracket before the while,
        //is repeated until the gameOver function returns with the value 'true'
        do
        {
            //Tell the players what move number this is
            System.out.println("Move #:" + moveNumber);

            if (symbol.equals("X"))
            {
                System.out.println(playerNameX + " Its ya move!");
            } else
            {
                System.out.println(playerNameO + " its your move");
            }

            //Get move from the keyboard
            String input = getUserInput();

            //Attempt to perform the move entered on the keyboard
            placeSymbol(ticTacToeBoard, input, symbol);

            //Increment the move number
            moveNumber++;

            //Toggle back and forth between X and O on each move by always changing to the symbol that was not the last one
            if (symbol.equals("X"))
            {
                symbol = "O";
            } else
            {
                symbol = "X";
            }

            //Print out the board
            printBoard(ticTacToeBoard);
        }
        while (!gameOver(ticTacToeBoard));
    }

    //Detects whether or not the game is over
    private static boolean gameOver(String[][] ticTacToeBoard)
    {
        boolean gameOver = false;

        //If the are no open spaces the game must be over
        if (!openSpaces(ticTacToeBoard))
        {
            gameOver = true;
        }
        //If there are three of the same symbol in a row the game is over
        else if (threeInARow(ticTacToeBoard, "X"))
        {
            gameOver = true;
        }
        else if (threeInARow(ticTacToeBoard, "O"))
        {
            gameOver = true;
        }
        else if (threeInAColumn(ticTacToeBoard, "X"))
        {
            gameOver = true;
        }
        else if (threeInAColumn(ticTacToeBoard, "O"))
        {
            gameOver = true;
        }



        return gameOver;
    }

    //Look for open spaces in the board and return true if at least one is found
    private static boolean openSpaces(String[][] ticTacToeBoard)
    {
        boolean openSpaces = false;

        //Loop through each row
        for (int row = 0; row < 3; row++)
        {
            //For the row we are on loop through the columns
            for (int column = 0; column < 3; column++)
            {
                if (ticTacToeBoard[column][row].equals(" "))
                {
                    openSpaces = true;
                }
            }
        }

        return openSpaces;
    }

    //Detects three in a row for player 'X'
    private static boolean threeInARow(String[][] ticTacToeBoard, String symbol)
    {
        boolean threeInARow = false;

        //Loop through each row in the board
        for (int row = 0; row < 3; row++)
        {
            if (threeInARow(ticTacToeBoard, row, symbol))
            {
                threeInARow = true;
            }
        }

        return threeInARow;

    }

    //Detects three in a row for player 'X'
    private static boolean threeInAColumn(String[][] ticTacToeBoard, String symbol)
    {
        boolean threeInAColumn = false;

        //Loop through each row in the board
        for (int column = 0; column < 3; column++)
        {
            if (threeInAColumn(ticTacToeBoard, column, symbol))
            {
                threeInAColumn = true;
            }
        }

        return threeInAColumn;

    }

    private static boolean threeInAColumn(String[][] ticTacToeBoard, int column, String symbol)
    {
        boolean threeInAColumn = false;
        int count = 0;

        //Loop through the columns in a row
        for (int row = 0; row < 3; row++)
        {
            //Check to see if the symbol in the position specified by row and column
            //matches the symbol passed into this function
            if (ticTacToeBoard[row][column].equals(symbol))
            {
                //If we got here we matched so increment the counter
                count++;

                //Check to see if we have counted 3 of the passed in symbol
                if (count == 3)
                {
                    //If we got here then it means we saw three of the passed in symbol in a row
                    threeInAColumn = true;
                }
            }
        }

        return threeInAColumn;
    }

    private static boolean threeInARow(String[][] ticTacToeBoard, int row, String symbol)
    {
        boolean threeInARow = false;
        int count = 0;

        //Loop through the columns in a row
        for (int column = 0; column < 3; column++)
        {
            //Check to see if the symbol in the position specified by row and column
            //matches the symbol passed into this function
            if (ticTacToeBoard[row][column].equals(symbol))
            {
                //If we got here we matched so increment the counter
                count++;

                //Check to see if we have counted 3 of the passed in symbol
                if (count == 3)
                {
                    //If we got here then it means we saw three of the passed in symbol in a row
                    threeInARow = true;
                }
            }
        }

        return threeInARow;
    }

    private static void placeSymbol(String[][] ticTacToeBoard, String input, String symbol)
    {
        //Take the String named 'input' and split in into an array of Strings using a comma to know where to split it up
        String[] position = input.split(",");

        //We should have gotten two Strings in the array
        //Use the first to indicate the row and the second to indicate the column
        String rowText = position[0];
        String columnText = position[1];

        //We need to turn the user input which the computer currently considers text and turn it into numbers
        int row = Integer.parseInt(rowText);
        int column = Integer.parseInt(columnText);

        //Create a variable to keep track of whether or not the input breaks the rules
        //Set the variable to true to assume the rules have been followed until we find a problem
        boolean inputValid = true;

        //Check to see if a number is out of range
        if (row < 0 || row > 2 || column < 0 || column > 2)
        {
            //If we got here one of the numbers the user entered is not valid
            inputValid = false;
        }
        //Check to make sure the position the user picked is blank
        else if (!ticTacToeBoard[row][column].equals(" "))
        {
            //If we got here the position the user picked already has a symbol in it
            inputValid = false;
        }

        //Check to see if we still consider the input to be valid
        if (inputValid == true)
        {
            //If we got here we did not find anyything wrong with the input
            //so we will fill out the position specified by row and column with the symbol that was passed in
            ticTacToeBoard[row][column] = symbol;
        } else
        {
            //If we got here something invalid we entered so the turn is forfeited
            System.out.println("Invalid number. No soup for you! Forfeit your turn.");
        }
    }

    //Set each place in the ticTacToeBoard array to a blank space
    private static void clearBoard(String[][] ticTacToeBoard)
    {
        //Loop through the three rows in the board
        for (int row = 0; row < 3; row++)
        {
            //Call a function to clear the row the row specified by the row variable
            clearRow(ticTacToeBoard, row);
        }
    }

    private static void clearRow(String[][] ticTacToeBoard, int row)
    {
        //Loop through the three columns in a row
        for (int column = 0; column < 3; column++)
        {
            //Put a space in the board position specified by the row and column variables
            ticTacToeBoard[row][column] = " ";
        }
    }

    private static void printBoard(String[][] ticTacToeBoard)
    {
        //Loop through the three rows in the board
        for (int row = 0; row < 3; row++)
        {
            //Call a function to print the row specified by th row variable
            printRow(ticTacToeBoard, row);

            System.out.println("");
            System.out.println("-----");
        }
    }

    //Prints one row of the board
    private static void printRow(String[][] ticTacToeBoard, int row)
    {
        //Loop through each column in the specified row
        for (int column = 0; column < 3; column++)
        {
            System.out.print(ticTacToeBoard[row][column]);
            if (column < 2)
            {
                System.out.print("|");
            }
        }
    }

    //Gets input from the keyboard
    //Unlike other examples you might see this one works within IntelliJ
    //Warning don't try to close the input or the next call to this function will fail
    private static String getUserInput()
    {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

}
