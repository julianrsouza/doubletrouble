package main;

import java.util.Arrays;
import java.util.List;

public class Fight {
    ClientSocketHandler jogador;
    ClientSocketHandler jogador2;
    List<String> possibleActions = Arrays.asList("ATACAR","DEFENDER","FUGIR");
   
    public Fight(ClientSocketHandler jogador, ClientSocketHandler jogador2) {
        this.jogador = jogador;
        this.jogador2 = jogador2;
    }

    public void start() {
        while(isBattleGoing()) {
            makePlayerTurn(jogador, jogador2);
            makePlayerTurn(jogador2, jogador);
        }
        if(isDead(jogador.getPersona())){
            sendServerMessage("Jogador1 venceu!");
            jogador.kill();
        }
        if(isDead(jogador2.getPersona())){
            sendServerMessage("Jogador1 venceu!");
            jogador2.kill();
        }
    }

    public void takeBattleAction(Persona attackingPlayerPersona, Persona deffendingPlayerPersona, String action) {
         switch(action.toUpperCase()) {
            case "ATACAR":
                atack(attackingPlayerPersona, deffendingPlayerPersona);
                break;
            case "DEFENDER":
                attackingPlayerPersona.defend(); 
                break;
            case "FUGIR":
                attackingPlayerPersona.setHp(0);
                break;
            default:
                break;
        }
    }

    private String printBattleStatus() {
        return "Vida do Jogador1: " + jogador.getPersona().getHp() 
            + " || Vida do jogador2: " + jogador2.getPersona().getHp();
    }

    private void atack(Persona attackingPlayerPersona, Persona deffendingPlayerPersona) {
       attackingPlayerPersona.atack(deffendingPlayerPersona);
    }

    private boolean isDead(Persona persona){
        return persona.getHp() <= 0;
    }

    private boolean isBattleGoing() {
        return this.jogador.getPersona().getHp() > 0 && this.jogador2.getPersona().getHp() > 0; 
    }

    private void sendServerMessage(String message) {
        this.jogador.sendMessage(message);
        this.jogador2.sendMessage(message);
    }

    private void makePlayerTurn(ClientSocketHandler attackingPlayer, ClientSocketHandler deffendingPlayer){
        if(isDead(attackingPlayer.getPersona()) || isDead(deffendingPlayer.getPersona())){
            return;
        }
        sendServerMessage(printBattleStatus());
        sendServerMessage("Vez do Jogador " + attackingPlayer.getPersona().getName());
        while(!possibleActions.contains(attackingPlayer.getInput())) {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        takeBattleAction(attackingPlayer.getPersona(), deffendingPlayer.getPersona(), attackingPlayer.getInput());
        attackingPlayer.setInput("");   
        if(deffendingPlayer.getPersona().getDefMax() > deffendingPlayer.getPersona().getDefNormal()) {
            deffendingPlayer.getPersona().setDefMax(deffendingPlayer.getPersona().getDefNormal());
        }
    }
    
}
