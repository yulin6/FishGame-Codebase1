package xyes;

public class Main {

    public static void main(String[] args) {

        String cmd = "hello world";
        if(args.length == 0) {
            while (true) {
                System.out.println(cmd);
            }
        } else {
            if(args[0].equals("-limit")){
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < args.length; ++i){
                    builder.append(args[i]);
                    builder.append(" ");
                }
                String newCmd = builder.toString();
                cmd = newCmd.equals("") ? "hello world" : newCmd;
                for (int i = 0; i < 20; ++i) {
                    System.out.println(cmd);
                }
            } else {
                StringBuilder builder = new StringBuilder();
                for (String arg : args) {
                    builder.append(arg);
                    builder.append(" ");
                }
                cmd = builder.toString();

                while (true) {
                    System.out.println(cmd);
                }
            }
        }
    }
}
