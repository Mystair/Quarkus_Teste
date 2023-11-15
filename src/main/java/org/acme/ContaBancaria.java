package org.acme;
import Exception.SaldoInsuficiente;

public class ContaBancaria {
    private String numeroConta;
    private double saldo;
    private Cliente titular;
    public double getSaldo;
    private static final double TAXA_DE_TRANSFERENCIA = 0.001;

    public void sacar(double valor) throws SaldoInsuficiente {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido.");
        }
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new SaldoInsuficiente("Saldo insuficiente para realizar o saque.");
        }
    }

    public void depositar(double valor) {
        this.saldo += valor;
        System.out.printf("Valor de %.2f depositado na conta de número %s \n", valor, this.numeroConta);
    }

    public void transferir(ContaBancaria origem, ContaBancaria destino, double valor) {
        try {
            if (origem.getSaldo < valor) {
                throw new SaldoInsuficiente("Saldo insuficiente para realizar a transferência.");
            }
            origem.equals(origem.getSaldo - valor - (valor * TAXA_DE_TRANSFERENCIA ));
            destino.equals(destino.getSaldo + valor);
        } catch (SaldoInsuficiente e) {
            System.out.println(e.getMessage());
        }
    }
}