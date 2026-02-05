package pio.daw.data;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends AlumnoABC {

    /**
     * A partir de una línea del CSV se obtiene un objeto de tipo Alumno con su
     * nombre y sus notas
     * @param line Linea del CSV con formato: "Nombre,Nota1,Nota2..."
     * @return Objeto Alumno
     */
	public static Alumno fromLine(String line){
        String[] splited = line.strip().split(",");
        String nombre = splited[0];
        List<Double> notas = new ArrayList<>();
        for(int i = 1; i < splited.length ; i++){
            notas.add(Double.parseDouble(splited[i]));
        }
        return new Alumno(nombre, notas);
    }

    private Alumno(String nombre, List<Double> notas){
        super(nombre, notas);
    }
    
}
