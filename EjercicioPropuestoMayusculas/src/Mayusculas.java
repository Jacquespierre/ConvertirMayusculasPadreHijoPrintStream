import java.io.*;

/**
 * @author Santiago - Juan Freitas Cuerdo 22/11/2022
 */

/*
Escribe una clase llamada Mayúsculas que haga lo siguiente
Cree un proceso hijo. El proceso padre y el proceso hijo se comunicarán de forma bidireccional utilizando streams.
El proceso padre leerá las lineas de su entrada estándar y las enviará a la entrada estándar del hijo
    (utilizando el OutputStream del hijo).
El proceso hijo leerá el texto por su entrada estándar, lo transformar todo a letras mayúsculas y lo
imprimirá por su salida estándar.
El padre imprimirá en pantalla lo que recibe del hijo a través del InputStreamdel mismo.
Ejemplo de ejecución:

hola(enter)
HOLA
mundo(enter)
MUNDO

 */

public class Mayusculas {
    public static void main(String[] args) throws IOException {
        //Inicializo el texto
        String texto;
        try {
            //ESTO SIEMPRE SE REALIZA
            //Genero el archivo Hijo
            File directorio = new File(".\\out\\production\\EjercicioPropuestoMayusculas\\Mayusculas");
            //Genero el archivo a ejecutar
            ProcessBuilder pb = new ProcessBuilder("java", "Hijo");
            //Establezco el directorio donde se ejecuta
            pb.directory(directorio);
            //Ejercutar el proceso
            Process hijo = pb.start();

            //El proceso padre e hijo se comunican en forma bidireccional usando Streams
            BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream ps = new PrintStream(hijo.getOutputStream());
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

            //Mientras lea lineas de texto
            while ((texto = br2.readLine()) != ""){
                //Las imprimo del PrintStream
                ps.println(texto);
                //Vacío el caché
                ps.flush();
                //Se comprueba el que se escriba
                if((texto = br2.readLine()) != null){
                    System.out.println(texto);
                }

            }
            System.out.println("Salida del programa");
            //Compruebo errores
            int exitVal;
            try {
                exitVal = hijo.waitFor();
                System.out.println("Valor de Salida: " + exitVal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe.getMessage());

        }

    }
}
