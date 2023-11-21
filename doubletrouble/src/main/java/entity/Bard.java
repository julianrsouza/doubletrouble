package entity;

public class Bard extends Persona{
    

    public Bard(String name, int[] status, int[] buff_Status, int maxHp) {
        super(name, status, buff_Status, maxHp);

    }

    public void atkBuff(Persona ally){
        ally.buff_Status[1] += 3;
    }

    public void defBuff(Persona ally){
        ally.buff_Status[2] += 3;
    }

    public void spdBuff(Persona ally){
        ally.buff_Status[3] += 3;
    }

    public void cure(Persona ally){
        ally.status[0] = 30;
    }
}
