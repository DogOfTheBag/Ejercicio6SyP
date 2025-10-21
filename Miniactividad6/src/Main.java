import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //se puede usar StringBuilder para invertir una cadena, asi que usaremos eso para comparar la que nos den y invertida para ver si es palindromo
        // en el otro programa lo tengo explicado
        //Ya no hacemos scanner ya que cogemos la entrada de un fichero de entrada
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una cadena");
        String cadena = sc.nextLine();
        */

        String rutaArchivoEntrada = "C:\\Users\\alber\\Documents\\IntelIJ Projects\\MiniActividad6\\res\\entrada.txt";
        String rutaArchivoError = "C:\\Users\\alber\\Documents\\IntelIJ Projects\\MiniActividad6\\res\\error.txt";
        String ruta = "C:\\Users\\alber\\Documents\\IntelIJ Projects\\Palindromo\\src";
        File archivoError = new File(rutaArchivoError);
        File archivoEntrada = new File(rutaArchivoEntrada);
        //la cadena tenemos que ponerla null porque si no nos lanza excepciones de que quiza no se haya inicializado
        String cadena = null;
        //lo mismo de siempre

        /*leemos el fichero de entrada, pillamos la cadena (en caso de meter varias cogerá la ultima
        * y se la damos a la variable*/
        try (BufferedReader readerArchivo = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while((linea = readerArchivo.readLine()) != null){
                cadena = linea;
            }
        }

        Process pb = null;
        try{
            pb = new ProcessBuilder("java", "-cp", ruta, "Palindromo").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        OutputStream os = pb.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        /*Creo que lo explique en otro de los ejercicios, pero como le tengo puesto Scanners al otro programa y al ser un proceso hijo
        * no hay consola que se ejecute, le paso las cosas mediante el PrintWriter y las coge directamente el scanner de ese programa
        * el unico inconveniente es que tengo que hacer aqui tambien el scanner pero da igual*/
        pw.println(cadena);

        String linea;

        while((linea = br.readLine()) != null){
            System.out.println(linea);
        }

        /*Para el error y pasarlo a un archivo*/
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(pb.getErrorStream()));
        PrintWriter errorWriter = new PrintWriter(new FileWriter(archivoError));
        String cadenaError;
        while((cadenaError = errorReader.readLine()) != null){
            errorWriter.println(cadenaError);
        }
        /*Para el error y pasarlo a un archivo*/

        try {
            pb.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Valor de salida =  " + pb.exitValue());

        //cierro ambos writers para que se guarde todo bien ya que no uso el try with resources
        pw.close();
        errorWriter.close();

    }
}