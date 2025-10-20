import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String cadena;
       // System.out.println("Introduce una cadena o un palindromo");
        /*Necesitaremos tener una cadena que registre el original, y luego un StringBuilder que coja la misma cadena y la invierta
        * cuando la tengamos invertida, comparamos las dos y si son iguales tenemos un palíndromo*/
        cadena = sc.nextLine();
        sb.append(cadena);
        //si esta vacia se pilla
        if(cadena.isBlank()){
            /*para probar que el errorStream funciona en el otro lado, en vez de pasarlo por consola, se lo voy a tirar como un system error
            * de forma que el reader del error la coja, y el writer la escriba en el archivo de errores que tengo en res*/
            System.err.println("La cadena esta vacía");
        }else{
            if(sb.reverse().toString().equalsIgnoreCase(cadena)){
                System.out.println("La cadena introducida es un palíndromo");
            }else
                System.out.println("La cadena introducida no es un palíndromo");
        }


    }
}