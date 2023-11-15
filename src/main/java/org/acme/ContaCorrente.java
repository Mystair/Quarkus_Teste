package org.acme;

import Exception.SaldoInsuficiente;


public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_MANUTENCAO_CC = 0.001;
    private final double taxaManutencao;
    public String getNumeroConta;


    public ContaCorrente(String numeroConta, double saldo, String titular) {
        super(numeroConta, saldo, titular);
        this.taxaManutencao = TAXA_MANUTENCAO_CC;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficiente {
        double taxa = valor * taxaManutencao;
        super.sacar(valor + taxa);
    }
}