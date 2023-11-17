package entity;

import java.util.ArrayList;

import javax.swing.text.html.ListView;

public class Player {
    private Persona[] party;
    private ArrayList<Item> inventario;
    private String name;
    private String login;
    private String password; 

    public Player(Persona[] party,String nome, String login,String senha){
        this.party = party;
        this.inventario = new ArrayList<Item>();
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    } 

    //Set e Get
    public Player[] getParty(){ return this.party;}
    public Item[] getInventario(){return this.inventario;}
    public String getNome() { return this.nome;}
    public String getLogin() { return this.nome;}
  
    public void add_item(Item item){
        this.inventario.add(item);
    }



    //Tente achar uma sala
    public void search(){

    }

    //Criar um sala

}
