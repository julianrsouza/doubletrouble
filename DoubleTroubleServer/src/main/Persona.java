package main;

public class Persona {
    private int hp;
    private int atk;
    private int def;

    public Persona(int hp, int atk, int def) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    public int getHp() {
        return this.hp;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getDef() {
        return this.def;
    }

    public void setHp(int h) {
        this.hp = h;
    }

    public void setAtk(int a) {
        this.atk = a;
    }

    public void setDef(int d) {
        this.def = d;
    }

    public void defend() {
        this.def += 5;
    }

    public void atack(Persona p) {
        int damage = this.atk - p.getDef();
        if (p.getHp() >= damage) {
            p.setHp(p.getHp() - damage);
        } else {
            p.setHp(0);
        }
    }
}
