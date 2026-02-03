package pio.daw;

public class App {
    public static void main(String[] args) {
        ArgParser argParser = null;
        try {
            argParser = ArgParser.formArgs(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.printf("El archivo CSV está en: %s\n", argParser.getPath());

    }
}
