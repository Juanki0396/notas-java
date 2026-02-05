package pio.daw;

import pio.daw.data.Aula;

public class App {
    public static void main(String[] args) {
        ArgParser argParser = null;
        try {
            argParser = ArgParser.formArgs(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        Aula clase = Aula.fromFile(argParser.getPath());
        clase.printAulaStats();
        clase.printBarPlot();
    }
}
