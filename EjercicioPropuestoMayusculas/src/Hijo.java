import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * @author Santiago - Juan Freitas Cuerdo 22/11/2022
 */

public class Hijo {
    public static void main(String[] args) {
        //Entradas del proceso padre
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        try{
            System.out.println("Teclea algo para pasarlo a mayusculas. Dos veces intro para sallir.");
            //Inicializo el texto a null
            String texto = "";
            while ((texto = br.readLine()) != null && !texto.isEmpty()){
                System.out.println(texto.toUpperCase(Locale.ROOT));
                System.out.println("Teclea algo para pasarlo a mayusculas. Dos veces intro para sallir.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
