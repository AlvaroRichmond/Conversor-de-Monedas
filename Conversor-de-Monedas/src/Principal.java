import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public Principal() throws IOException {
    }

    public static void main(String[] args) {

        int opcion = 0;
        APIrequest apiRequest = new APIrequest();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        String menu = """
                *********************************************
                  Sea bienvenido al convertor de monedas
                  Por favor ingrese una opción
                  1- Doláres ==> Colónes
                  2- Pesos Argentinos ==> Pesos Mexicanos
                  3- Pesos Colombianos ==> Colónes
                  4 Euros ==> Doláres
                  5 Doláres ==> Euros
                  6- Colónes ==> Euros
                  7- Finalizar el programa
                  """;

        Scanner teclado = new Scanner(System.in);

        while (opcion != 7) {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    conversorDeMonedas(apiRequest, teclado, "USD", "CRC");
                    break;
                case 2:
                    conversorDeMonedas(apiRequest, teclado, "EUR", "MXN");
                    break;
                case 3:
                    conversorDeMonedas(apiRequest, teclado, "COP", "CRC1");
                    break;
                case 4:
                    conversorDeMonedas(apiRequest, teclado, "EUR", "USD");
                    break;
                case 5:
                    conversorDeMonedas(apiRequest, teclado, "USD", "EUR");
                    break;
                case 6:
                    conversorDeMonedas(apiRequest, teclado, "CRC", "EUR");
                    break;
                case 7:
                    salir = true;
                    System.out.println("Finalizando el programa, gracias por su preferencia");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
        teclado.close();


    }

    private static void conversorDeMonedas(APIrequest apiRequest, Scanner teclado, String monedaBase, String nuevaMoneda) {
        try {
            Monedas monedas = apiRequest.buscaMonedas(monedaBase);
            System.out.print("Ingrese el monto que desea convertir: ");
            double monto = teclado.nextDouble();
            teclado.nextLine();
            Double montoBase = monedas.conversion_rates().get(nuevaMoneda);

            if (montoBase == null) {
                System.out.println("No se logro realizar la conversion");
            } else {
                double nuevoMonto = monto * montoBase;
                System.out.printf("El Valor De %.2f %s en %s es: %.3f%n", monto, monedaBase, nuevaMoneda, nuevoMonto);
            }
        } catch (IOException | InterruptedException e) {


        }
    }


}
