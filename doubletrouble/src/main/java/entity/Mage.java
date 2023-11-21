package entity;

public class Mage extends Persona{



    public Mage(String name, int[] status, int[] buff_Status, int maxHp) {
        super(name, status, buff_Status, maxHp);
    }

    public void fireball(Persona oponnent){
        int damage = (super.getStatus()[1] + 3) - oponnent.getStatus()[2];
        oponnent.takingDamage(this, damage);   
    }

    public void fireballAll(Persona p[]){
        for(int i = 0; i < p.length; i++){
            fireball(p[i]);
        }
    }
}
