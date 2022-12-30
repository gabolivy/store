package Configurações;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;
import Classes.Produto;
import Classes.Venda;

public class App {
   
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao;
        String nome = "";
        String codigo = "";
        double valor = 0; 
        double total = 0;
        int quantidadeEmEstoque = 0;
        int compraQuantid;
        LocalDate data;
        int dia;
        int mes;
        int ano;
        Produto res = new Produto("t", "0", 0, 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        boolean toggler = false;
        int formaDeBusca = 0;
        String produto_a_buscar = "";
        List<Produto> produtos = new ArrayList<Produto>();
        List<Venda> vendas = new ArrayList<Venda>();
        Scanner in = new Scanner(System.in);


        do {
            System.out.println("\n********************\n SEJA BEM VINDO(A)");
            System.out.println("       MENU\n********************\n");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

            if (opcao == 1) {
                
                System.out.println("\nInforme o nome do produto:");
                nome = in.nextLine();
                nome.toLowerCase();

                System.out.println("\nInforme o código do produto:");
                codigo = in.nextLine();
                
                for(Produto p : produtos){
                    if(p.getCodigo().equals(codigo)){
                        System.out.println("\nO código já está em uso.\nPor favor, insira um novo código.");
                        toggler = true;
                    }
                
                    
              
                    
                
                }
                if(toggler == false){
                    System.out.println("\nDigíte o valor do produto:");
                    valor = in.nextDouble();

                    System.out.println("\nDigíte a quantidade disponível no estoque:");
                    quantidadeEmEstoque = in.nextInt();
                   
                    
                    produtos.add(new Produto(nome, codigo, valor, quantidadeEmEstoque));
                    System.out.println("\nProduto adicionado com sucesso!");
                }
                in.nextLine();
                toggler = false;
                voltarMenu(in);

            } else if (opcao == 2) {

                if(produtos.isEmpty()){
                    System.out.println("Nenhum produto foi cadastrado ainda!");
                }else{
                    System.out.println("\nDigíte a forma da busca:\n1=Busca por id.\n2=Busca por nome.");
                    formaDeBusca = in.nextInt();
                    toggler = false;
                    
                    if(formaDeBusca == 1){

                        System.out.println("\nDigíte o código do produto:");
                        produto_a_buscar = in.next();

                        for(Produto p : produtos){
                            if(p.getCodigo().equals(produto_a_buscar)){

                                System.out.println("\nProduto localizado com sucesso!\n");
                                System.out.printf("Nome do produto: %s \nCodigo do produto: %s \nValor do produto: R$%s  \nQuantidade no estoque: %s\n", p.getNome(),p.getCodigo(),p.getValor(),p.getQuantidadeEmEstoque());
                                toggler = true;
                            }
                        }
                 
                    }else if(formaDeBusca == 2){

                        System.out.println("\nDigíte o nome do produto:");
                        produto_a_buscar = in.next();
                        produto_a_buscar.toLowerCase();

                        for(Produto p : produtos){
                            if(p.getNome().equals(produto_a_buscar)){

                                System.out.println("\nProduto localizado com sucesso!\n");
                                System.out.printf("Nome do produto: %s  \nCodigo do produto: %s  \nValor do produto: %s  \nQuantidade no estoque: %s\n", p.getNome(),p.getCodigo(),p.getValor(),p.getQuantidadeEmEstoque());
                                toggler = true;
                            }
                        }

                    }else{
                        System.out.println("Error!");
                    }

                    if(toggler == true){
                        toggler = false;
                    }else{
                        System.out.println("\nProduto não identificado!");
                }}

                in.nextLine();
                voltarMenu(in);
            } else if (opcao == 3) {

                if(produtos.isEmpty())
                {
                    System.out.println("Nenhum produto foi cadastrado ainda!");
                }else{
                    System.out.println("\nQual forma de ordenação deseja?\n1=Por nome");
                    formaDeBusca = in.nextInt();

                    System.out.printf("\n---Produtos no estoque---\n-Quantidade de produtos: %s- \n\n", produtos.size());//
                    
                    if(formaDeBusca ==1){
                        produtos.sort(new OrdenadorPorNome());
                    }else{
                        System.out.println("Error!");
                        toggler = true;
                    }
                if(toggler == false){
                    for(Produto p: produtos){
                        System.out.printf("Nome: %s  \nCódigo: %s  \nValor Unitário: %s  \nQuantidade em estoque: %s\n", p.getNome(),p.getCodigo(),p.getValor(),p.getQuantidadeEmEstoque());
                    }

                    Double medio = produtos.stream().collect(Collectors.averagingDouble(Produto::getValor));
                    OptionalDouble minimo = produtos.stream().mapToDouble(Produto::getValor).min();
                    OptionalDouble maximo = produtos.stream().mapToDouble(Produto::getValor).max();
                    
                    System.out.printf("\nValor Médio: %s  \nValor Maxímo: %s  \nValor Minímo: %s\n", medio, maximo.getAsDouble(), minimo.getAsDouble());
                }else{
                    toggler = false;
                }}

                in.nextLine();
                voltarMenu(in);
            
            } else if (opcao == 4) {

                if(vendas.isEmpty()){
                    System.out.println("Nenhum produto foi vendido ainda!");
                    in.nextLine();
                }else{
                    vendas.sort(new ComparadorData());
                    System.out.printf("\n\nTodas as emissões:\n%s - %s\n\n", vendas.get(0).getData().format(dtf), vendas.get(vendas.size()-1).getData().format(dtf));
                    for(Venda v: vendas){
                        System.out.printf("Data: %s - Nome: %s - Quantidade: %s - Valor unitário: %s - Valor total: %s\n", v.getData().format(dtf), v.getNome(), v.getQuantidade(),v.getValor(),v.getValorToTal());
                        
                        
                    }
                    Double medio = vendas.stream().collect(Collectors.averagingDouble(Venda::getValorToTal));
                    System.out.println("Valor médio das vendas: " + medio);
                }
                voltarMenu(in);

            } else if (opcao == 5) {
                
                if(produtos.isEmpty()){
                    System.out.println("Nenhum produto foi cadastrado ainda!");
                }else{

                System.out.println("\nDigíte a forma de compra:\n1.Por código(ID)\n2.Por nome");
                formaDeBusca = in.nextInt();

                if(formaDeBusca == 1){
                    
                    System.out.println("\nDigíte o código(ID) do produto:");
                    codigo = in.next();

                    //LocalDate.parse(data);

                    for(Produto p : produtos){
                        if(p.getCodigo().equals(codigo)){
                            res = p;}}

                }else if(formaDeBusca ==2){
                    
                    System.out.println("Digíte o nome do produto:");
                    nome = in.next();

                    for(Produto p:produtos){
                        if(p.getNome().equals(nome)){
                            res = p;
                        }}

                }else{
                    System.out.println("\nError");
                    toggler = true;
                }

                    System.out.println("\nDigíte a quantidade que deseja comprar:");
                    compraQuantid = in.nextInt();

                    if(res.getQuantidadeEmEstoque() >= compraQuantid){

                        System.out.printf("\nData da compra:\nDigíte apenas o dia: ");
                        
                        dia = in.nextInt();

                        System.out.printf("\nDigíte apenas o mes: ");
                        mes = in.nextInt();

                        System.out.printf("\nDigíte apenas o ano: ");
                        ano = in.nextInt();


                        data = LocalDate.of(ano, mes, dia); 
                        
                        total = res.getValor() * compraQuantid;
                        System.out.println(total);
                        
                        int estoqueRemove = (res.getQuantidadeEmEstoque()-compraQuantid);
                        
                        vendas.add(new Venda(res.getNome(), compraQuantid, Integer.parseInt(res.getCodigo()), data, res.getValor(), total));
                        res.setQuantidadeEmEstoque(estoqueRemove);
                        
                        System.out.printf("\nCompra do dia %s concluida com sucesso!\n\nProduto vendido: %s - Quantidade: %s - Valor Unitário: %s - Valor total: %s\n\nQuantidade de %s disponível: %s\n",data.format(dtf),res.getNome(),compraQuantid,res.getValor(),total,res.getNome(),res.getQuantidadeEmEstoque());
                        in.nextLine();
                        toggler = true;
                    
                    }else{
                        if(res.getQuantidadeEmEstoque() == 0){
                            System.out.println("Este produto está fora de estoque, por favor volte quando ele estiver reposto!");
                        }else{
                            System.out.println("Essa quantidade não esta disponível para compra!");
                        }
                    }
                if(toggler == false){
                    System.out.println("Produto não identificado!");
                    in.nextLine();
                }else{
                    toggler = false;
                }
                
                if(toggler == false){

                }else{
                    toggler = false;
                }}

                voltarMenu(in);
            }else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}