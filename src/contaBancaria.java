import java.text.DecimalFormat;

public class contaBancaria {
    private double saldo;
    private double limiteChequeEspecial;
    private double usoChequeEspecial;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public contaBancaria(double valorInicial) {
        this.saldo = valorInicial;
        if (valorInicial <= 500) {
            this.limiteChequeEspecial = 50;
        } else {
            this.limiteChequeEspecial = 0.5 * valorInicial;
        }
        this.usoChequeEspecial = 0;
    }

    public double consultarSaldo(){
        return saldo;
    }

    private boolean tentaSacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        double valorFaltante = valor - saldo;
        if (valorFaltante <= (limiteChequeEspecial - usoChequeEspecial)) {
            saldo = 0;
            usoChequeEspecial += valorFaltante;
            return true;
        }
            return false;
        }

    public double consultarChequeEspecial() {
        return limiteChequeEspecial - usoChequeEspecial;
    }

    public void depositar(double valor) {
        double valorRestante = valor;
        if (usoChequeEspecial > 0) {
            double taxa = usoChequeEspecial * 0.2;
            double totalDivida = usoChequeEspecial + taxa;
            if (valorRestante > totalDivida) {
                valorRestante -= totalDivida;
                usoChequeEspecial = 0;
                saldo += valorRestante;
                System.out.println("Divida do cheque especial paga com taxa de R$ " + df.format(taxa));
            } else {
                double valorPago = valorRestante / 1.2;
                usoChequeEspecial -= valorPago;
                System.out.println("Divida do cheque especial pago no valor de R$ " + df.format(valorPago) + " a taxa de juros foi de " + df.format(taxa));
                if (usoChequeEspecial < 0) usoChequeEspecial = 0;
                return;
            }
        } else {
            saldo += valorRestante;
            System.out.println("Depósito de R$ " + df.format(valorRestante) + " realizado com sucesso");
        }
    }

    public void sacar(double valor) {
        if (tentaSacar(valor)) {
            System.out.println("Você sacou o valor de R$ " + df.format(valor) + " com sucesso");
            if (usoChequeEspecial > 0) {
                System.out.println("O valor de R$ " + df.format(usoChequeEspecial) + " está sendo usado");
            }
        } else {
            System.out.println("Você não tem saldo e nem cheque especial suficiente");
        }
    }

    public void pagarBoleto(double valor){
        System.out.println("Aguarde, sistema fazendo o pagamento...");
        if (tentaSacar(valor)) {
            System.out.println("Você pagou o boleto no valor de R$ " + df.format(valor) + " com sucesso");
            if (usoChequeEspecial > 0) {
                System.out.println("O valor de R$ " + df.format(usoChequeEspecial) + " está sendo usado do cheque especial");
            }
        } else {
            System.out.println("Você não tem saldo e nem cheque especial suficiente");
        }
    }

    public boolean estaUsandoOChequeEspecial() {
        return usoChequeEspecial > 0;
    }
}
