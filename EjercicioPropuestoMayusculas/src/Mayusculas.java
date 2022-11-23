import java.io.*;

/**
 * @author Santiago - Juan Freitas Cuerdo 22/11/2022
 */

/*
Escribe una clase llamada May�sculas que haga lo siguiente
Cree un proceso hijo. El proceso padre y el proceso hijo se comunicar�n de forma bidireccional utilizando streams.
El proceso padre leer� las lineas de su entrada est�ndar y las enviar� a la entrada est�ndar del hijo
    (utilizando el OutputStream del hijo).
El proceso hijo leer� el texto por su entrada est�ndar, lo transformar todo a letras may�sculas y lo
imprimir� por su salida est�ndar.
El padre imprimir� en pantalla lo que recibe del hijo a trav�s del InputStreamdel mismo.
Ejemplo de ejecuci�n:

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
                //Vac�o el cach�
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
