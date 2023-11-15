package services;

import org.acme.ContaCorrente;
import Exception.SaldoInsuficiente;
import Exception.ContaInvalida;

public interface ContaCorrenteService {
    ContaCorrente getContaPorNumero(String numeroConta);
    void depositar(String numeroConta, double valor) throws ContaInvalida;
    void sacar(String numeroConta, double valor) throws ContaInvalida, SaldoInsuficiente;
    void transferir(String contaOrigem, String contaDestino, double valor) throws ContaInvalida, SaldoInsuficiente;
    ContaCorrente criarConta(String nome, String cpf) throws ContaInvalida;

}
