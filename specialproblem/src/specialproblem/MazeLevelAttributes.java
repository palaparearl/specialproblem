package specialproblem;

public class MazeLevelAttributes {
	public static String[][] questions;
	public static int[][][] doorPositions;
	public static int[][][] doorDestinations;
	public static int[][] correctAnswer;
	public static int[][] numChoices;
	public static int[][] mapRoomValues;
	
	public static void init() {
		questions = new String[10][9];
		
		questions[0][0] = "what are shell scripts";
		questions[0][1] = "basic command of the shell which prints as string argument";
		questions[0][2] = "command to make a shell script executable";
		questions[0][3] = "an example of a simple command";
		questions[0][4] = "an example of a complex command";
		questions[0][5] = "command separator, indicates where one command ends and another begins";
		questions[0][6] = "an example of a compound command";
		questions[0][7] = "typed in the prompt (command line) to execute shell script";
		questions[0][8] = "command that is helpful in displaying details of another command provided as argument";
		
		questions[1][0] = "shebang to invoke Bourne Again shell";
		questions[1][1] = "an example of an alias declaration";
		questions[1][2] = "command added in the bash script to make aliases work";
		questions[1][3] = "an example of a function definition";
		questions[1][4] = "not a syntax for invoking a function";
		questions[1][5] = "an example of a comment in a shell script";
		questions[1][6] = "shell function definition must appear before function call";
		questions[1][7] = "a function must contain at least one command";
		questions[1][8] = "command to check if a keyword is not reserved and can be used as an alias name";
		
		questions[2][0] = "proper way of referencing a variable";
		questions[2][1] = "proper way of defining a variable";
		questions[2][2] = "performed by the shell when replacing the name of a variable with its value";
		questions[2][3] = "which are not allowed to be used in variable names";
		questions[2][4] = "variables that have been preset by the shell, and in the shell environment";
		questions[2][5] = "command used to view variables in the environment";
		questions[2][6] = "an example of making a variable's value unchangeable";
		questions[2][7] = "an example of making the shell \"forget\" a variable";
		questions[2][8] = "an example of adding a variable to the environment for the current shell session";
		
		questions[3][0] = "example of substituting the results of a command";
		questions[3][1] = "naming convention for a constant in shell scripting";
		questions[3][2] = "an example of substituting a filename with a given prefix and/or suffix";
		questions[3][3] = "an example of substituting a filename's character with a given set of characters";
		questions[3][4] = "same principle as filename substitution, but applying set negation";
		questions[3][5] = "symbol used to match one occurrence of any character in shell scripting";
		questions[3][6] = "proper syntax for performing arithmetic substitution";
		questions[3][7] = "output of the integer arithmetic $((5 / 2))";
		questions[3][8] = "another syntax for command substitution in bash";
		
		questions[4][0] = "an empty function definition will cause a syntax error upon execution of script";
		questions[4][1] = "a simple way to perform stubbing is to place this command in an empty function definition";
		questions[4][2] = "variables that cease to exist once the function terminates";
		questions[4][3] = "the entire script being executed can be considered as a single large function";
		questions[4][4] = "in the code snippet \"shell=\"bash\"; func () { greet=hello; }\", which is the local variable";
		questions[4][5] = "local variables supersedes global variables";
		questions[4][6] = "syntax of a function call with provided arguments";
		questions[4][7] = "way to access the parameters provided for the function";
		questions[4][8] = "shell function naming rules are the same with variable naming rules";
		
		questions[5][0] = "an example of an if condition";
		questions[5][1] = "the exit status, which evaluates the success of a command, has values 0-255 and 0 indicates success, any other value indicates failure";
		questions[5][2] = "command that causes the script to terminate immediately and sets exit status";
		questions[5][3] = "an elif and else condition is optional";
		questions[5][4] = "command that is used to determine exit status of an expression, and usually provided as condition to the if statement";
		questions[5][5] = "improper use of test command";
		questions[5][6] = "an example of case statement, given 'fruit=peach', is";
		questions[5][7] = "the most practical use of case statements is through pattern matching in strings, applying the principle of substitution";
		questions[5][8] = "minimum number of patterns required in the case statement";
		
		questions[6][0] = "syntax of read command where input is stored in variable";
		questions[6][1] = "where value of read command is stored if there is no argument supplied after";
		questions[6][2] = "read command with timer to specify number of seconds to give response";
		questions[6][3] = "read command which hides the text being typed (used for passwords)";
		questions[6][4] = "whitespace is ignored when shell evaluates arithmetic expressions";
		questions[6][5] = "an example of referencing variables inside arithmetic expression";
		questions[6][6] = "result of $((5 & 3)) (bitwise and)";
		questions[6][7] = "result of $((5 ^ 3)) (bitwise or)";
		questions[6][8] = "symbol used for binary one's complement";
		
		questions[7][0] = "a good practice is to test the script frequently when adding new code";
		questions[7][1] = "shebang of the shell script when tracing is done";
		questions[7][2] = "alternate method to apply tracing, uses the commands to turn tracing on";
		questions[7][3] = "alternate method to apply tracing, uses the commands to turn tracing off";
		questions[7][4] = "bash supports multi-line comment";
		questions[7][5] = "command in the text editor \"Vim\" to turn on syntax highlighting";
		questions[7][6] = "given the line \"num=\", what is the result of the expansion of the statement \"if [ $num = 1 ]\" which produces an error";
		questions[7][7] = "errors that do not prevent a program from running, but produces faulty output";
		questions[7][8] = "it is a good practice to enclose variables and command substitutions in double quotes";
		
		questions[8][0] = "an example of providing positional parameters in the command line";
		questions[8][1] = "variable of starting positional parameter";
		questions[8][2] = "variable which contains the value of the number of positional parameters provided";
		questions[8][3] = "the value of $4 becomes the value of $5 using the shift command";
		questions[8][4] = "variable which contains the value of the list of all positional parameters";
		questions[8][5] = "way to access a positional parameter with an index greater than 9";
		questions[8][6] = "options can be considered as positional parameters";
		questions[8][7] = "positional parameters can be passed as arguments to functions";
		questions[8][8] = "the positional parameter $0 contains the value";
		
		questions[9][0] = "what is the exit status of the command 'false;'";
		questions[9][1] = "variable that contains the exit status of the previous program executed";
		questions[9][2] = "second command executes if and only if first command is successful (exit status 0)";
		questions[9][3] = "second command executes if and only if first command is not successful (exit status 1)";
		questions[9][4] = "an example of referencing a variable within a string";
		questions[9][5] = "an example of 'trap' command";
		questions[9][6] = "function that may be called in the 'trap' command to perform actions before the script ends, solution for the one-line limitation of 'trap' command";
		questions[9][7] = "variable that holds the process id of the current program";
		questions[9][8] = "signals can arrive at any point of time during the execution of a script";
		
		doorPositions = new int[10][][];
		
		doorPositions[0] = new int[][] {{-1, 1, 1, -1}, {1, -1, 1, -1}, {1, 1, -1, -1}, {1, 1, -1, 1}, {-1, 1, 1, -1}, {-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, 1, -1, 1}, {-1, -1, -1, 1}};
		doorPositions[1] = new int[][] {{-1, 1, -1, -1}, {-1, 1, 1, -1}, {1, -1, -1, -1}, {1, 1, -1, -1}, {1, -1, 1, 1}, {-1, 1, 1, 1}, {-1, -1, -1, 1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
		doorPositions[2] = new int[][] {{-1, 1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, -1}, {1, 1, -1, 1}, {-1, -1, 1, 1}, {-1, 1, -1, 1}, {-1, -1, 1, 1}, {1, 1, -1, -1}, {-1, -1, -1, 1}};
		doorPositions[3] = new int[][] {{-1, 1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, -1}, {1, 1, -1, 1}, {-1, -1, 1, 1}, {-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
		doorPositions[4] = new int[][] {{-1, 1, 1, -1}, {1, 1, 1, -1}, {1, -1, -1, -1}, {1, 1, -1, -1}, {-1, -1, 1, 1}, {-1, 1, -1, 1}, {-1, -1, 1, 1}, {1, 1, -1, -1}, {-1, -1, -1, 1}};
		doorPositions[5] = new int[][] {{-1, -1, 1, -1}, {1, 1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, 1}, {1, -1, -1, 1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
		doorPositions[6] = new int[][] {{-1, 1, -1, -1}, {-1, 1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, 1}, {1, -1, -1, 1}, {-1, 1, 1, 1}, {-1, -1, -1, 1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
		doorPositions[7] = new int[][] {{-1, 1, -1, -1}, {-1, 1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, 1}, {1, -1, -1, 1}, {-1, 1, 1, 1}, {-1, -1, 1, 1}, {1, 1, -1, -1}, {-1, -1, -1, 1}};
		doorPositions[8] = new int[][] {{-1, 1, 1, -1}, {1, -1, 1, -1}, {1, 1, -1, -1}, {-1, 1, -1, 1}, {1, -1, -1, -1}, {-1, 1, 1, 1}, {-1, -1, -1, 1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
		doorPositions[9] = new int[][] {{-1, -1, 1, -1}, {1, -1, 1, -1}, {1, 1, -1, -1}, {1, 1, -1, 1}, {1, 1, 1, -1}, {-1, 1, 1, -1}, {-1, -1, -1, 1}, {-1, 1, -1, 1}, {-1, -1, -1, 1}};
		
		doorDestinations = new int[10][][];
		
		doorDestinations[0] = new int[][] {{-1, 5, 1, -1}, {0, -1, 2, -1}, {1, 3, -1, -1}, {4, 8, -1, 2}, {-1, 7, 3, -1}, {-1, 6, -1, 0}, {-1, -1, -1, 5}, {-1, 99, -1, 4}, {-1, -1, -1, 3}};
		doorDestinations[1] = new int[][] {{-1, 5, -1, -1}, {-1, 4, 2, -1}, {1, -1, -1, -1}, {4, 8, -1, -1}, {5, -1, 3, 1}, {-1, 6, 4, 0}, {-1, -1, -1, 5}, {-1, 99, 8, -1}, {7, -1, -1, 3}};
		doorDestinations[2] = new int[][] {{-1, 5, 1, -1}, {0, 4, -1, -1}, {-1, 3, -1, -1}, {4, 8, -1, 2}, {-1, -1, 3, 1}, {-1, 6, -1, 0}, {-1, -1, 7, 5}, {6, 99, -1, -1}, {-1, -1, -1, 3}};
		doorDestinations[3] = new int[][] {{-1, 5, 1, -1}, {0, 4, -1, -1}, {-1, 3, -1, -1}, {4, 8, -1, 2}, {-1, -1, 3, 1}, {-1, 6, -1, 0}, {-1, -1, -1, 5}, {-1, 99, 8, -1}, {7, -1, -1, 3}};
		doorDestinations[4] = new int[][] {{-1, 5, 1, -1}, {0, 4, 2, -1}, {1, -1, -1, -1}, {4, 8, -1, -1}, {-1, -1, 3, 1}, {-1, 6, -1, 0}, {-1, -1, 7, 5}, {6, 99, -1, -1}, {-1, -1, -1, 3}};
		doorDestinations[5] = new int[][] {{-1, -1, 1, -1}, {0, 4, 2, -1}, {1, 3, -1, -1}, {-1, 8, -1, 2}, {5, -1, -1, 1}, {-1, 6, 4, -1}, {-1, -1, -1, 5}, {-1, 99, 8, -1}, {7, -1, -1, 3}};
		doorDestinations[6] = new int[][] {{-1, 5, -1, -1}, {-1, 4, 2, -1}, {1, 3, -1, -1}, {-1, 8, -1, 2}, {5, -1, -1, 1}, {-1, 6, 4, 0}, {-1, -1, -1, 5}, {-1, 99, 8, -1}, {7, -1, -1, 3}};
		doorDestinations[7] = new int[][] {{-1, 5, -1, -1}, {-1, 4, 2, -1}, {1, 3, -1, -1}, {-1, 8, -1, 2}, {5, -1, -1, 1}, {-1, 6, 4, 0}, {-1, -1, 7, 5}, {6, 99, -1, -1}, {-1, -1, -1, 3}};
		doorDestinations[8] = new int[][] {{-1, 5, 1, -1}, {0, -1, 2, -1}, {1, 3, -1, -1}, {-1, 8, -1, 2}, {5, -1, -1, -1}, {-1, 6, 4, 0}, {-1, -1, -1, 5}, {-1, 99, 8, -1}, {7, -1, -1, 3}};
		doorDestinations[9] = new int[][] {{-1, -1, 1, -1}, {0, -1, 2, -1}, {1, 3, -1, -1}, {4, 8, -1, 2}, {5, 7, 3, -1}, {-1, 6, 4, -1}, {-1, -1, -1, 5}, {-1, 99, -1, 4}, {-1, -1, -1, 3}};
		
		correctAnswer = new int[10][];
		
		correctAnswer[0] = new int[] {1, 2, 0, 1, 0, 2, 0, 1, 1};
		correctAnswer[1] = new int[] {1, 1, 1, 0, 2, 1, 1, 0, 1};
		correctAnswer[2] = new int[] {2, 1, 1, 0, 0, 2, 1, 0, 1};
		correctAnswer[3] = new int[] {2, 0, 0, 2, 2, 1, 2, 0, 2};
		correctAnswer[4] = new int[] {1, 1, 1, 0, 2, 0, 0, 2, 1};
		correctAnswer[5] = new int[] {2, 1, 1, 0, 2, 1, 0, 0, 2};
		correctAnswer[6] = new int[] {2, 2, 1, 2, 1, 0, 1, 2, 2};
		correctAnswer[7] = new int[] {1, 2, 0, 0, 1, 1, 1, 2, 0};
		correctAnswer[8] = new int[] {0, 1, 0, 0, 1, 0, 0, 1, 1};
		correctAnswer[9] = new int[] {1, 1, 2, 0, 1, 2, 1, 0, 1};
		
		numChoices = new int[10][];
		numChoices[0] = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3};
		numChoices[1] = new int[] {3, 3, 3, 3, 3, 3, 2, 2, 3};
		numChoices[2] = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3};
		numChoices[3] = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3};
		numChoices[4] = new int[] {2, 3, 3, 2, 3, 2, 3, 3, 2};
		numChoices[5] = new int[] {3, 2, 3, 2, 3, 3, 3, 2, 3};
		numChoices[6] = new int[] {3, 3, 3, 3, 2, 3, 3, 3, 3};
		numChoices[7] = new int[] {2, 3, 3, 3, 2, 3, 3, 3, 2};
		numChoices[8] = new int[] {3, 3, 3, 2, 3, 3, 2, 2, 3};
		numChoices[9] = new int[] {3, 3, 3, 3, 3, 3, 3, 3, 2};
		
		mapRoomValues = new int[10][];
		mapRoomValues[0] = new int[] {0, 1, 2, 3, 0, 4, 5, 4, 5};
		mapRoomValues[1] = new int[] {6, 0, 7, 2, 8, 9, 5, 0, 10};
		mapRoomValues[2] = new int[] {0, 2, 6, 3, 11, 4, 11, 2, 5};
		mapRoomValues[3] = new int[] {0, 2, 6, 3, 11, 4, 5, 0, 10};
		mapRoomValues[4] = new int[] {0, 1, 7, 2, 11, 4, 11, 2, 5};
		mapRoomValues[5] = new int[] {12, 1, 2, 4, 10, 0, 5, 0, 10};
		mapRoomValues[6] = new int[] {6, 0, 2, 4, 10, 9, 5, 0, 10};
		mapRoomValues[7] = new int[] {6, 0, 2, 4, 10, 9, 11, 2, 5};
		mapRoomValues[8] = new int[] {0, 13, 2, 4, 7, 9, 5, 0, 10};
		mapRoomValues[9] = new int[] {12, 13, 2, 3, 1, 0, 5, 4, 5};
	}
}
