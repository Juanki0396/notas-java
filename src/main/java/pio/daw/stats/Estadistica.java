package pio.daw.stats;

import java.util.List;

public class Estadistica {

    /**
     * Calcula la media de una muestra
     * @param nums Muestra de numeros
     * @return Media 
     */
    public static Double media(List<Double> nums){
        Double suma = 0.;
        if(nums.size() == 0){
            return suma;
        }
        for(Double n : nums){
            suma += n;
        }
        return suma / nums.size();
    }

    
    /**
     * Calcula el máximo de una muestra
     * @param nums Muestra de numeros
     * @return Máximo 
     */
     public static Double max(List<Double> nums){
        Double maxNum = nums.get(0);
        for(Double n: nums){
            if (n > maxNum){
                maxNum = n;
            }
        }
        return maxNum;
    }

    
    /**
     * Calcula el mínimo de una muestra
     * @param nums Muestra de numeros
     * @return Mínimo 
     */
     public static Double min(List<Double> nums){
        Double minNum = nums.get(0);
        for(Double n: nums){
            if (n < minNum){
                minNum = n;
            }
        }
        return minNum;
    }

    /**
     * Calcula la varianza de una muestra
     * @param nums Muestra
     * @return Varianza
     */
     public static Double var(List<Double> nums){
        Double suma = 0.;
        Double media = media(nums);
        for(Double n : nums){
            suma += Math.pow(n - media, 2);
        }
        return suma / (nums.size());
    }
    
}
