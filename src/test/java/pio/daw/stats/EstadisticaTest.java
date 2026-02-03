package pio.daw.stats;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EstadisticaTest {

    public static boolean checkDouble(Double resultado, Double esperado, Double precision){
        return Math.abs(resultado - esperado) < precision;
    }

    @Test
    public void mediaTest(){
        List<Double> nums = List.of(1., 3., 5.);
        List<Double> nums2 = List.of(10., 20., 30., 40.);
        Double media = Estadistica.media(nums);
        Double media2 = Estadistica.media(nums2);
        assertTrue(checkDouble(media, 3., 0.000000001));
        assertTrue(checkDouble(media2, 25., 0.000000001));
    }

    @Test
    public void mediaEmptyListTest(){
        List<Double> nums = new ArrayList<>();
        assertTrue(checkDouble(Estadistica.media(nums), 0., 0.000000001));
    }

    @Test 
    public void maxText(){
         List<Double> nums = List.of(10., 20., 30., 40.);
         Double max = Estadistica.max(nums);
         assertTrue(checkDouble(max, 40., 0.000000000001));
    }

    @Test 
    public void minText(){
         List<Double> nums = List.of(10., 20., 30., 40.);
         Double min = Estadistica.min(nums);
         assertTrue(checkDouble(min, 10., 0.000000000001));
    }

    @Test
    public void varianzaTest(){
        List<Double> nums = List.of(1., 3., 5.);
        Double varianza = Estadistica.var(nums);
        assertTrue(checkDouble(varianza, 2.67, 0.01));
    }


}
