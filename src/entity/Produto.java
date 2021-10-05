package entity;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private String nome;
    private Double price;
    private List<Produto> lista = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, Double price) {
        this.nome = nome;
        this.price = price;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Produto> getLista() {
        return lista;
    }


    public void setLista(Produto p) {
        lista.add(p);
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        for(Produto p: lista){
            s.append(p.getNome());
            s.append(", ");
            s.append(p.getPrice());
            s.append("\n");
        }

        return s.toString();
    }
}
