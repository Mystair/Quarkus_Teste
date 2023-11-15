package services;

import org.acme.ContaCorrente;
import org.acme.ContaBancaria;
import org.acme.Cliente;
import Exception.SaldoInsuficiente;
import Exception.ContaInvalida;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ContaCorrenteServiceImpl implements ContaCorrenteService {
    private final List<ContaCorrente> contasCorrentes;
    private static final AtomicInteger contadorContas = new AtomicInteger(1);


    public ContaCorrenteServiceImpl(List<ContaCorrente> contasCorrentes) {
        this.contasCorrentes = contasCorrentes;
    }

    //Implementação da API Stream!
    @Override
    public ContaCorrente getContaPorNumero(String numeroConta) {
        return contasCorrentes.stream()
                .filter(conta -> conta.getNumeroConta.equals(numeroConta))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void depositar(String numeroConta, double valor) throws ContaInvalida {
        ContaCorrente conta = getContaPorNumero(numeroConta);
        if (conta == null) {
            throw new ContaInvalida("Conta inválida. Por favor, verifique o número da conta.");}
        conta.depositar(valor);
    }

    @Override
    public void sacar(String numeroConta, double valor) throws ContaInvalida, SaldoInsuficiente {
        ContaCorrente conta = getContaPorNumero(numeroConta);
        if (conta != null) {
            if (conta.getSaldo >= valor) {
                conta.sacar(valor);
            } else {
                throw new SaldoInsuficiente("Saldo insuficiente para realizar o saque.");
            }
        } else {
            throw new ContaInvalida("Conta inválida. Por favor, verifique o número da conta.");
        }
    }

    @Override
    public void transferir(String contaOrigem, String contaDestino, double valor) throws ContaInvalida, SaldoInsuficiente {
        ContaBancaria origem = getContaPorNumero(contaOrigem);
        ContaBancaria destino = getContaPorNumero(contaDestino);

        if (origem == null || destino == null) {
            throw new ContaInvalida("Conta de origem ou destino inválida. Por favor, verifique os números das contas.");
        }
        if (origem.getSaldo >= valor) {
            origem.transferir(origem, destino, valor);
        } else {
            throw new SaldoInsuficiente("Saldo insuficiente para realizar a transferência.");
        }
    }

    @Override
    public ContaCorrente criarConta(String nome, String cpf) throws ContaInvalida {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty()) {
            throw new ContaInvalida("Nome e CPF são obrigatórios.");
        }

        String numeroConta = gerarNumeroContaUnico();

        String cliente = new Cliente().toString();

        return new contaCorrente(numeroConta, 0.0, cliente);
    }
    private String gerarNumeroContaUnico() {
        return String.format("%05d", contadorContas.getAndIncrement());
    }
}