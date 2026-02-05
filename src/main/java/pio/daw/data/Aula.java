package pio.daw.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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
        try{ 
            //// Otra Manera
            // String line = null;
            // while( (line = reader.readLine()) != null){
            //     alumnos.add(Alumno.fromLine(line));
            // }
            for(String line : Files.readAllLines(path)){
                alumnos.add(Alumno.fromLine(line));
            }
        } catch (IOException e) {
            System.err.printf("ERROR: Cannot read file: %f\n", path);
            System.exit(1);
        }
        Collections.sort(alumnos, (a1, a2) -> a1.getNombre().compareTo(a2.getNombre()));
        return new Aula(alumnos);
    }

    private Aula(List<AlumnoABC> alumnos) {
		super(alumnos);
	}

    /**
     * Obtiene un alumno a partir de su nombre.
     * @param nombre Nombre del alumno que se quiere obtener
     * @return Alumno o null si no se encuentra
     */
    public AlumnoABC getAlumno(String nombre){
        for(AlumnoABC a : this.getAlumnos()){
            if(a.getNombre().equals(nombre)){
                return a;
            }
        }
        return null;
    }

    /**
     * Devuelve el alumno con mejor media
     * @return Alumno con mejor media.
     */
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

    /**
     * Devuelve el alumno con peor media
     * @return Alumno con peor media.
     */
	public AlumnoABC getWorstAlumno(){
        AlumnoABC worstAlumno = null;
        Double worstMedia = 10.;
        for(AlumnoABC a : this.getAlumnos()){
            Double media = Estadistica.media(a.getNotas());
            if (media < worstMedia){
                worstAlumno = a;
                worstMedia = media;
            }
        }
        return worstAlumno;
    }

    private List<Double> getAllNotes(){
        List<Double> notas = new ArrayList<>();
        for(AlumnoABC a : this.getAlumnos()){
            notas.addAll(a.getNotas());
        }
        return notas;
    }

    /**
     * Devuelve la media global de la clase.
     * @return Media clase
     */
	public Double getMedia(){
        return Estadistica.media(this.getAllNotes());
    }

    /**
     * Devuelve la máximo global de la clase.
     * @return Máximo clase
     */
	public Double getMax(){
        return Estadistica.max(this.getAllNotes());
    }

    /**
     * Devuelve el mínimo global de la clase.
     * @return Mínimo clase
     */
	public Double getMin(){
        return Estadistica.min(this.getAllNotes());
    }

    /**
     * Devuelve la varianza global de la clase.
     * @return Varianza clase
     */
	public Double getVar(){
        return Estadistica.var(this.getAllNotes());
    }

    /**
     * Imprime en consola las estadísticas de toda la clase: Mejor y peor alumno,
     * media, máximo, mínimo y varianza
     */
    public void printAulaStats(){
        System.out.printf("%1$s%2$sEstadísticas%2$s%1$s\n\n", "* * * * *", "   ");
        AlumnoABC bestAlumno = this.getBestAlumno();
        AlumnoABC wostAlumno = this.getWorstAlumno();
        System.out.printf("%-18s -> %s: %.2f\n", "Mejor Alumno", bestAlumno.getNombre(), Estadistica.media(bestAlumno.getNotas()));
        System.out.printf("%-18s -> %s: %.2f\n", "Peor Alumno" ,wostAlumno.getNombre(), Estadistica.media(wostAlumno.getNotas()));
        System.out.printf("%-18s -> %.2f\n", "Media de la clase", this.getMedia());
        System.out.printf("%-18s -> %.2f\n", "Máximo de la clase", this.getMedia());
        System.out.printf("%-18s -> %.2f\n", "Media de la clase", this.getMedia());
        System.out.printf("%-18s -> %.2f\n\n", "Media de la clase", this.getMedia());
    }

    /**
     * Imprime en consola una gráfica de barras con las notas medias de cada uno de los alumnos.
     */
    public void printBarPlot(){
        System.out.printf("%1$s%2$sNOTAS%2$s%1$s\n\n", "* * * * *", "   ");
        for (AlumnoABC a : this.getAlumnos()){
            Integer mediaRedondeada = (int) Math.round(Estadistica.media(a.getNotas()));
            System.out.printf("%-20s |%s\n", a.getNombre(), String.join(" ", Collections.nCopies(mediaRedondeada, "*")));
        }
    }

}

