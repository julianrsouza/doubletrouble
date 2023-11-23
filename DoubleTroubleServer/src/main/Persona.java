package main;

public class Persona {
    private int hp;
    private int atk;
    private int defNormal;
    private int defMax;
    private String name;

    public Persona(int hp, int atk, int def, String name) {
        this.hp = hp;
        this.atk = atk;
        this.defNormal = def;
        this.name = name;
        this.defMax = defNormal;
    }

    public Persona(String name) {
        this(10, 10, 5, name);
        this.name = name;
        this.defMax = defNormal;
    }

    public int getHp() {
        return this.hp;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getDefNormal() {
        return this.defNormal;
    }

    public int getDefMax() {
        return this.defMax;
    }

    public void setHp(int h) {
        this.hp = h;
    }

    public void setAtk(int a) {
        this.atk = a;
    }

    public void setDefNormal(int d) {
        this.defNormal = d;
    }

    public void setDefMax(int d) {
        this.defMax = d;
    }

    public void defend() {
        this.defMax = defNormal * 2;
    }

    public void atack(Persona p) {
        int damage = this.atk - p.getDefMax();
        System.out.println(this.name + " causou " + damage + "!");
        if (p.getHp() >= damage) {
            p.setHp(p.getHp() - damage);
        } else {
            p.setHp(0);
        }
        System.out.println(p.name + " ficou com " + p.hp + " de vida!");
    }

    public String getName() {
        return name;
    }

}
