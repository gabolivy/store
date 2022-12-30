package Classes;

import java.time.LocalDate;

public class Venda {
    
    private String nome;
    private int quantidade;
    private int codigo;
    private double valor;
    private double valorTotal;
    
    
    private LocalDate data;
    
    
    
    public Venda(String nome, int quantidade, int codigo, LocalDate data, double valor,double valorTotal){
        this.nome = nome;
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.valor = valor;
        this.data = data;
        this.valorTotal = valorTotal;
        
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public double getValorToTal() {
        return valorTotal;
    }
}