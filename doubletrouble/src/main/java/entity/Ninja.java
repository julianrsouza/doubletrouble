package entity;

public class Ninja extends Persona {
    
    public Ninja(){

    }

    public void atkBuff(Persona oponnent){
        oponnent.buff_Status[1] -= 3;
    }

    public void defBuff(Persona oponnent){
        oponnent.buff_Status[2] -= 3;
    }

    public void spdBuff(Persona oponnent){
        oponnent.buff_Status[3] -= 3;
    }
}
