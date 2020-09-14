package xyes;

//Main class of program xyes, which will execute commands repeatedly.
//For more details, please reference the README file
public class Main {

    public static void main(String[] args) {

        String cmd = "hello world";

        //when the command is empty, repeatedly print "hello world"
        if(args.length == 0) {
            infinitePrint(cmd);
        } else {//when the command is not empty
            StringBuilder builder = new StringBuilder();
            //when the first command line argument is -limit, repeatedly print
            // the rest arguments or "hello world" (when the rest is empty)
            // for 20 times.
            if(args[0].equals("-limit")){
                for (int i = 1; i < args.length; ++i){
                    builder.append(args[i]);
                    builder.append(" ");
                }
                String newCmd = builder.toString().trim();
                cmd = newCmd.equals("") ? "hello world" : newCmd;
                for (int i = 0; i < 20; ++i) {
                    System.out.println(cmd);
                }
            } else {//when the first command line argument is NOT -limit
                //repeatedly print the rest arguments
                for (String arg : args) {
                    builder.append(arg);
                    builder.append(" ");
                }
                cmd = builder.toString().trim();
                infinitePrint(cmd);
            }
        }
    }

    private static void infinitePrint(String cmd){
        while (true) {
            System.out.println(cmd);
        }
    }
}
