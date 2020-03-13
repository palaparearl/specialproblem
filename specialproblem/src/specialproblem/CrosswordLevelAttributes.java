package specialproblem;

public class CrosswordLevelAttributes {
	public static int[][] lettersIndices;
	public static String[][] words;
	public static String[][] defs;
	
	public static void init() {
		lettersIndices = new int[10][];
		lettersIndices[0] = new int[]{1, 17, 25, 2, 8, 11, 12, 13, 18, 13, 12, 7, 0, 4, 10, 13, 14, 11, 3, 13, 11, 4, 3, 19, 23};
		lettersIndices[1] = new int[]{0, 11, 13, 2, 19, 14, 13, 8, 0, 18, 5, 7, 4, 1, 6, 12, 12, 20, 17, 16, 21, 6, 23, 13, 24};
		lettersIndices[2] = new int[]{21, 14, 0, 15, 17, 23, 8, 19, 0, 18, 1, 13, 11, 20, 4, 13, 14, 11, 3, 13, 11, 4, 3, 19, 17};
		lettersIndices[3] = new int[]{6, 14, 11, 12, 14, 12, 1, 0, 1, 3, 8, 13, 13, 8, 6, 19, 2, 4, 12, 7, 12, 19, 0, 17, 3};
		lettersIndices[4] = new int[]{5, 17, 20, 2, 13, 11, 2, 13, 19, 13, 8, 7, 14, 4, 13, 13, 18, 1, 3, 1, 11, 6, 3, 19, 23};
		lettersIndices[5] = new int[]{4, 11, 23, 2, 8, 14, 19, 8, 2, 18, 0, 7, 18, 1, 14, 12, 13, 20, 3, 16, 8, 6, 14, 11, 13};
		lettersIndices[6] = new int[]{17, 17, 4, 2, 0, 11, 3, 13, 8, 13, 13, 7, 19, 4, 6, 13, 4, 11, 11, 13, 14, 4, 2, 19, 11};
		lettersIndices[7] = new int[]{18, 11, 24, 2, 13, 14, 19, 8, 0, 18, 2, 7, 19, 1, 8, 12, 2, 20, 14, 16, 12, 6, 12, 17, 4};
		lettersIndices[8] = new int[]{0, 17, 17, 2, 6, 11, 20, 13, 12, 13, 4, 7, 13, 4, 19, 13, 18, 11, 7, 13, 8, 14, 5, 15, 14};
		lettersIndices[9] = new int[]{4, 11, 23, 8, 8, 14, 19, 8, 11, 18, 13, 7, 13, 1, 14, 12, 17, 20, 0, 16, 15, 6, 18, 8, 6};
		
		
		words = new String[10][];
		words[0] = new String[]{"COMMAND", "SHELL", "BASH"};
		words[1] = new String[]{"ALIAS", "FUNCTION", "SHEBANG", "COMMENT"};
		words[2] = new String[]{"VARIABLE", "UNSET", "EXPORT"};
		words[3] = new String[]{"GLOBBING", "COMMAND", "ARITHMETIC"};
		words[4] = new String[]{"FUNCTION", "STUBBING"};
		words[5] = new String[]{"EXIT", "CASE", "CONDITIONAL"};
		words[6] = new String[]{"READ", "INTEGER", "LOGICAL"};
		words[7] = new String[]{"SYNTACTIC", "COMMENT", "TRACING"};
		words[8] = new String[]{"ARGUMENT", "SHIFT", "OPTION"};
		words[9] = new String[]{"EXIT", "LINENO", "TRAP", "SIGINT"};
		
		defs = new String[10][];
		defs[0] = new String[]{"-a program that you can run", "-provides you with an interface to the unix system", "-type of shell to be taught in this application"};
		defs[1] = new String[]{"-way to create a new command which acts as an abbreviation for longer one", "-way of mapping a name to a list of commands", "-first line of the script", "-statement that is not executed by the shell"};
		defs[2] = new String[]{"-\"words\" that hold a value", "-process of removing a variable from the shell", "-process of placing variables in the environment"};
		defs[3] = new String[]{"-other term for filename substitution", "-type of substitution that uses the result of an executed instruction", "-type of substitution that uses the result of integer mathematics"};
		defs[4] = new String[]{"-binds a name to a list of commands, which can be invoked", "-technique we use on empty shell commands to keep script functional"};
		defs[5] = new String[]{"-____ status is the value returned when the program terminates", "-a form of flow control aside from if", "-primary characteristic of flow control commands"};
		defs[6] = new String[]{"-command that takes input from the keyboard", "-type of number that the shell can perform arithmetic operations with", "-operators that are allowed in bash and returns a 0 or 1 value"};
		defs[7] = new String[]{"-errors produced that prevents a program from running", "-way to isolate the errors in a code", "-technique to make bash display the flow as it executes a program"};
		defs[8] = new String[]{"-placed in the command line that can be handled through positional parameters", "-when invoked, displaces the positional parameters down by one", "-change the behavior of the command"};
		defs[9] = new String[]{"-status that is checked to find errors", "-environment variable that contains the line in the script where error occurred", "-command that is used to execute commands upon receiving signals", "-signal that is sent when typing ctrl-c, short for \"signal interrupt\""};
	}
}
