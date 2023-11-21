package entity;

import java.util.ArrayList;

public class Player {
    private Persona[] party;
    private ArrayList<Item> inventario;
    private String name;
    private String login;
    private String password; 

    public Player(Persona[] party,String nome, String login,String senha){
        this.party = party;
        this.inventario = new ArrayList<Item>();
        this.name = nome;
        this.login = login;
        this.password = senha;
    } 

    //Set e Get
    public Persona[] getParty(){ return this.party;}
    public ArrayList<Item> getInventario(){return this.inventario;}
    public String getNome() { return this.name;}
    public String getLogin() { return this.login;}
  
    public void add_item(Item item){
        this.inventario.add(item);
    }

    //Tente achar uma sala
    public void search(){

    }

    //Criar um sala

}
