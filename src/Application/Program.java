package Application;

import entity.Produto;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        operacao(sc);


    }

    public static int menu(Scanner sc){
        System.out.println("0 - Sair");
        System.out.println("1 - Inserir Na Lista");
        System.out.println("2 - Listar lista Gerada");
        System.out.println("3 - Arquivar Lista");
        System.out.println("4 - Ler Lista Arquivada");
        System.out.println("5 - Ler Lista Arquivada de maneira 2");
        System.out.print("Operação: ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    public static boolean operacao(Scanner sc){


        boolean cont = true;
        while(cont){

            int op = -1;
            String caminho;
            Produto produto = new Produto();

            while(op != 0){
                op = menu(sc);
                switch (op){
                    case 0:
                        System.out.println("Sair da Lista Criada");
                        break;
                    case 1:
                        System.out.print("Nome do Produto: ");
                        String nome = sc.nextLine();
                        System.out.print("Preco do Produto: ");
                        Double preco = sc.nextDouble();
                        //lista.add(new Produto(nome, preco));
                        produto.setLista(new Produto(nome, preco));
                        break;
                    case 2:
                        System.out.println(produto.toString());
                        break;
                    case 3:
                        System.out.print("Informe o Caminho: ");
                        caminho = sc.next();
                        caminho += ".txt";

                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))){

                            String [] lines ={produto.toString()};

                            for(String l:lines){
                                bw.write(l);
                                bw.newLine();
                            }

                            System.out.println("Gravado com sucesso!!!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 4:
                        System.out.print("Informe o Arquino e caminho: ");
                        caminho = sc.next();
                        caminho += ".txt";
                        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){

                            String line = br.readLine();
                            while(line != null){
                                System.out.println(line);
                                line = br.readLine();
                            }


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            System.out.println("Inexistente");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Inexistente");
                        }
                        break;
                    case 5:
                        System.out.print("Informe o Arquino e caminho: ");
                        String cam = sc.nextLine();
                        cam += ".txt";

                        try(BufferedReader br = new BufferedReader(new FileReader(cam))){

                            List<Produto> list = new ArrayList<>();

                            String line = br.readLine();

                            while(line != null){
                                String [] lines =  line.split(",");
                                list.add(new Produto(lines[0], Double.parseDouble(lines[1])));
//                                System.out.println(line);
                                line = br.readLine();
                            }

                            double media = list.stream()
                                    .map(p -> p.getPrice()) //busca os preços
                                    .reduce(0.0,(x,y) -> x+y) / list.size();//reduce irá somar os preços

                            System.out.println("Média de Valores: " + String.format("%.2f",media));
                            /*7:33 min*/

                           Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
                            List<String> nomes = list.stream()
                                    .filter(p -> p.getPrice() < media)
                                    .map(p ->p.getNome())
                                    .sorted(comp.reversed())
                                    .collect(Collectors.toList());

                            nomes.forEach(System.out::println);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            System.out.println("Erro**** " + e.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Erro**** " + e.getMessage());
                        }

                        break;
                }
            }

            System.out.println("Deseja Nova Lista? [s/n]: ");
            char s = sc.next().toUpperCase().charAt(0);
            cont = (s == 'S')?true:false;
        }
        System.out.println("Deseja Nova Lista? [s/n]: ");
        char s = sc.next().toUpperCase().charAt(0);
        cont = (s == 'S')?true:false;
        return cont;
    }

}
