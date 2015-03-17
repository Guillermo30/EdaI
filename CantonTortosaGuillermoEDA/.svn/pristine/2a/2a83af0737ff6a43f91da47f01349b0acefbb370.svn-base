package org.eda1.practica01.ejercicio01;

import java.util.*;

public class Editor {
     public final static char COMMAND_START = '$';
     public final static char DELIMITER = '%';

     public final static String INSERT_COMMAND = "$Insert";

     public final static String DELETE_COMMAND = "$Delete";

     public final static String LINE_COMMAND = "$Line";

     public final static String DONE_COMMAND = "$Done";
     
     public final static String LAST_COMMAND = "$Last";

     public final static String GETLINES_COMMAND = "$GetLines";
     
     public final static String CHANGE_COMMAND = "$Change";
     
     public final static String BAD_LINE_MESSAGE =
        "Error: a command should start with " + COMMAND_START + ".\n";

     public final static String BAD_COMMAND_MESSAGE =
        "Error: not one of the given commands.\n";

     public final static String INTEGER_NEEDED =
        "Error: The command should be followed by a blank space, " +
        "\nfollowed by an integer.\n";

     public final static String TWO_INTEGERS_NEEDED =
        "Error: The command should be followed by a blank space, " +
        "\nfollowed by an integer, followed by a blank space, " +
        "followed by an integer.\n";

     public final static String FIRST_GREATER =
        "Error: the first line number is greater than the second.\n";

     public final static String FIRST_LESS_THAN_ZERO =
        "Error: the first line number given is less than 0.\n";

     public final static String SECOND_TOO_LARGE =
        "Error: the second line number given is greater than the " +
        "\nnumber of the last line in the text.\n";

     public final static String M_LESS_THAN_ZERO =
        "Error: the number is less than 0.\n";

     public final static String M_TOO_LARGE =
        "Error: the number is larger than the number of lines in the text.\n";

     public final static String LINE_TOO_LONG =
        "Error: the line exceeds the maximum number of characters allowed, ";

     public final static String INCORRECT_DELIMITERS_NUMBER =
        "Error: Delimiter must occur three times. Please try again.\n";
     
     public final static String NO_DELIMITERS_BEGIN_END =
        "Error: Bad Expression format, delimiters should be at the beginning " + 
        "\nand at the end. Please try again.\n";

     public final static String TWO_CONSECUTIVE_DELIMITERS_AT_THE_BEGINNING =
        "Error: Bad Expression format, two consecutive delimiters " +
        "\nat the beginning. Please try again.\n";

     public final static int MAX_LINE_LENGTH = 80;


     protected LinkedList<String> text;

     protected ListIterator<String> current;

     protected boolean inserting;
     

     public Editor() {
         text = new LinkedList<String>();
         current = text.listIterator();
         inserting = false;
     }


}
