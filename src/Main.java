import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static contaBancaria conta;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        System.out.print("Digite o valor inicial para abrir a conta: ");
        double valorInicial = scanner.nextDouble();
        conta = new contaBancaria(valorInicial);
        int option;

        do {
            System.out.println("===Escolha uma das opções===");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar dinheiro");
            System.out.println("3 - Sacar dinheiro");
            System.out.println("4 - Pagar boleto");
            System.out.println("5 - Consultar cheque especial disponível");
            System.out.println("6 - Verificar uso do cheque especial");
            System.out.println("7 - Sair");
            System.out.print("Qual opção você escolhe: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> verSaldo();
                case 2 -> depositar();
                case 3 -> sacar();
                case 4 -> pagarBoleto();
                case 5 -> consultarChequeEspecial();
                case 6 -> estouUsandoOChequeEspecial();
                case 7 -> System.exit(0);
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (true);
    }

    public static void verSaldo() {
        System.out.println("Seu saldo atual é de R$ " + df.format(conta.consultarSaldo()));
    }

    public static void depositar() {
        System.out.println("Informe o valor para deposito: ");
        double valorDeposito = scanner.nextDouble();
        conta.depositar(valorDeposito);
    }

    public static void sacar() {
        System.out.println("Informe o valor para saque: ");
                double valorSaque = scanner.nextDouble();
                conta.sacar(valorSaque);
    }

    public static void pagarBoleto() {
        System.out.println("Informe o valor do boleto: ");
        double valorBoleto = scanner.nextDouble();
        conta.pagarBoleto(valorBoleto);
    }

    public static double consultarChequeEspecial() {
        System.out.println("O valor de cheque especial disponivel é R$ " + df.format(conta.consultarChequeEspecial()));
        return 0;
    }

    public static void estouUsandoOChequeEspecial() {
        if (conta.estaUsandoOChequeEspecial()) {
            System.out.println("Você está usando o cheque especial");
        } else {
            System.out.println("Você NÃO está usando o cheque especial");
        }
    }
}