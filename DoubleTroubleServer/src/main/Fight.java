package main;

public class Fight {
    ClientSocketHandler jogador1;
    ClientSocketHandler jogador2;
   
    public Fight(ClientSocketHandler jogador1, ClientSocketHandler jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    public void start() {
        jogador1.setCurrentPlayer(true);
        jogador1.sendMessageToAll("Iniciando batalha entre os jogadores!", true);
        while(isBattleGoing()) {
            jogador1.sendMessageToAll(printBattleStatus(), true);
            if(jogador1.isCurrentPlayer()) {
                if(isDead(jogador1)){
                    jogador1.sendMessageToAll("Jogador2 venceu, parabéns!", true);
                    jogador1.kill();
                    jogador2.kill();
                }
                jogador1.sendMessageToAll("Vez do Jogador1", true);
                while(jogador1.listenPlayer() == null) {}
                takeBattleAction(jogador1, jogador1.listenPlayer());
                jogador1.sendMessageToAll(printBattleStatus(), true);
                jogador1.setCurrentPlayer(false);
                jogador2.setCurrentPlayer(true);
            } else {
                if(isDead(jogador2)){
                    jogador1.sendMessageToAll("Jogador1 venceu, parabéns!", true);
                    jogador1.kill();
                    jogador2.kill();
                }
                jogador2.sendMessageToAll("Vez do Jogador2", true);
                while(jogador2.listenPlayer() == null) {}
                takeBattleAction(jogador2, jogador2.listenPlayer());
                jogador2.sendMessageToAll(printBattleStatus(), true);
                jogador2.setCurrentPlayer(false);
                jogador1.setCurrentPlayer(true);
            }
            jogador1.sendMessageToAll("CLEAR", true);
        }
    }

    public void takeBattleAction(ClientSocketHandler player, String action) {
         switch(action.toUpperCase()) {
            case "ATACAR":
                player.sendMessageToAll(player.getName() + "atacou.", true);
                atack(player);
                break;
            case "DEFENDER":
                player.sendMessageToAll(player.getName() + "defendeu. (defesa durante o próximo ataque aumentada)", true);
                player.getPersona().defend(); 
                break;
            case "FUGIR":
                player.sendMessageToAll(player.getName() + "Fugiu.", true);
                player.getPersona().setHp(0);
                break;
        }
    }

    private String printBattleStatus() {
        return "Vida do Jogador1: " + jogador1.getPersona().getHp() 
            + "|| Vida do jogador2: " + jogador2.getPersona().getHp();
    }

    private void atack(ClientSocketHandler player) {
         if(player.getName().equals("Jogador1")){
                    player.getPersona().atack(this.jogador2.getPersona());
        } else {
            player.getPersona().atack(this.jogador1.getPersona());
        }
    }

    private boolean isDead(ClientSocketHandler player){
        return player.getPersona().getHp() <= 0;
    }

    private boolean isBattleGoing() {
        return this.jogador1.getPersona().getHp() > 0 && this.jogador2.getPersona().getHp() > 0; 
    }
    
}
