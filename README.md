# CS4500 - Software Development
*Dadiel Hantoro & Yu Lin*

## Assignment 2: xyes
The xyes is a shell-executable command-line program that echoes the command line arguments to standard output. The program has three different test cases:
If the user does not specify any arguments with the jar file associated with the xyes program, the default function of the program would be to print out “hello world”.
If the user specifies a limit by typing “-limit” before a command, the program will print out 20 lines of the argument.
If the user does not specify a limit before a command, the program will continuously print out the argument and if there are no arguments it will print out “hello world” infinitely.
(Assignment Link: https://www.ccs.neu.edu/home/matthias/4500-f20/B.html)

#### Folder & File Purposes:
    /B : containing the executable program xyes and a folder other.
    /B/xyes : the main executable program.
    /B/other : containing all auxiliary files for xyes project.
    /B/other/out : contains the artifacts of the program and has the jar file, that allows users to use the program in their command prompt/terminal.
    /B/other/src/xyes : containing the source code of xyes.
    /B/other/src/xyes/Main.java : the source code of xyes, written in Java.
    /B/other/test/xyes : containing the test script for xyes.
    /B/other/src/xyes/MainTest.java : the test script of xyes, written in Java with JUnit.
    
#### Roadmap:
    | annetta
    |  README.md
    |  B
    |  | xyes
    |  | Other/xyes
    |  |  | .idea
    |  |  | out
    |  |  | xyes.iml
    |  |  | src/xyes
    |  |  |      | Main.java
    |  |  | test/xyes
    |  |  |      | MainTest.java



## Assignment 3: xjson
The xjson is a program that consumes a sequence of well-formed JSON values from STDIN and delivers JSON to STDOUT. The output of the sequence is a JSON object that has two values a “count” and a “seq”, the count is the number of JSON values read and the second field is the JSON list of all JSON values read in order. The second one is a JSON list whose first element is the count of JSON values read and the remainder is the sequence of JSON values read in reverse order. The main method scans the values that are typed in by the user, the isBracesOrBracketCompleted determines whether the values that are typed are proper JSON objects. The addSpaceForQuotes is for specific instances when there is a string two quotation mark that is inserted into the STDIN. The xjsonTest is the test for the main, the first tests the quote on strings with no quotation marks, then the couple of tests after that tests whether a string is inside a map of JSON values to indicate if a JSON object is complete or not. Then the next couple of tests were used to determine whether printing nothing, numbers, strings and JSON objects were outputted JSON objects explained above. 

#### Roadmap:
    |  C
    |  | xjson
    |  | Other/xjson
    |  |  | .idea
    |  |  | out
    |  |  | xyes.iml
    |  |  | src/xjson
    |  |  |      | Main.java
    |  |  | test/xyes
    |  |  |      | MainTest.java

## Assignment 4: xgui
The xgui is a program that consumes an string from the command line with an integer value that creates a panel containing a hexagon shape. The integer is used to determine the size of the hexagon shape in the panel, which in turn shapes the panel size as well. The main class has a main method that consumes an argument which is a string that contains an integer value, it then calls the isPosInt method to determine whether the input is a valid, if not the main method will print an error message to the command line. If the inputs are valid, the integer value is sent to the HexagonPanel class in which the paintComponent is called to make the actual drawing of the shape in the panel. In order to close the program, the user are able to do this by clicking anywhere within the panel and this is done through the addMouseListenerToPanel method that listens to mouse clicks. The MainTest class has tests for isPosInt to determine wether the user point is valid point and the other tests after that are test methods to check the messages that comes out from inputing invalid commands. 

#### Roadmap:
    |  D
    |  | xgui
    |  | Other/xgui
    |  |  | .idea
    |  |  | out
    |  |  | xyes.iml
    |  |  | src/xjson
    |  |  |      | Main.java
    |  |  |      | HexagonPanel.java
    |  |  | test/xyes
    |  |  |      | MainTest.java
    