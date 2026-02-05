package pio.daw.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import pio.daw.stats.Estadistica;
import pio.daw.stats.EstadisticaBasica;
import pio.daw.stats.Graficable;


public class Aula extends AulaABC implements EstadisticaBasica, Graficable {
    
    /**
     * Lee un archivo csv con los alumnos y sus notas y devuelve un objeto de tipo
     * Aula ya creado.
     * @param path Ruta al archivo csv
     * @return Aula con los alumnos y sus notas.
     */
    public static Aula fromFile(Path path){
        List<AlumnoABC> alumnos = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path)){ 
            //// Otra Manera
            // String line = null;
            // while( (line = reader.readLine()) != null){
            //     alumnos.add(Alumno.fromLine(line));
            // }
            for(String line : reader.readAllLines()){
                alumnos.add(Alumno.fromLine(line));
            }
        } catch (IOException e) {
            System.err.printf("ERROR: Cannot read file: %f\n", path);
            System.exit(1);
        }
        return new Aula(alumnos);
    }

    private Aula(List<AlumnoABC> alumnos) {
		super(alumnos);
	}

    public AlumnoABC getAlumno(String nombre){
        for(AlumnoABC a : this.getAlumnos()){
            if(a.getNombre().equals(nombre)){
                return a;
            }
        }
        return null;
    }

	public AlumnoABC getBestAlumno(){
        AlumnoABC mejorAlumno = null;
        Double bestMedia = 0.;
        for(AlumnoABC a : this.getAlumnos()){
            Double media = Estadistica.media(a.getNotas());
            if (media > bestMedia){
                mejorAlumno = a;
                bestMedia = media;
            }
        }
        return mejorAlumno;
    }

	public AlumnoABC getWorstAlumno(){
        //TODO
        return null;
    }

	public Double getMedia(){
        //TODO
        return null;
    }

	public Double getMax(){
        //TODO
        return null;
    }

	public Double getMin(){
        //TODO
        return null;
    }

	public Double getVar(){
        //TODO
        return null;
    }

    public void printAulaStats(){
        //TODO
    }

    public void printBarPlot(){
        //TODO
    }

}

