package Classes;

public class Produto implements Comparable<Produto>{

    private String nome;
    private String codigo;
    private double valor; 
    private int quantidadeEmEstoque;

    
    public Produto(String nome, String codigo, double valor, int quantidadeEmEstoque){

        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.quantidadeEmEstoque = quantidadeEmEstoque;

    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    @Override
    public int compareTo(Produto produto2) {
        // TODO Auto-generated method stub
        return getCodigo().compareTo(
            produto2.getCodigo()
        );
    }
    
}