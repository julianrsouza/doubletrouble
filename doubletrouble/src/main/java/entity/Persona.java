package entity;

import java.util.random;


public class Persona {

  private String name;
  private int[] status;
  private int[] buff_Status;
  private int maxHp;

  public Persona(String name, int[] status, int[] buff_Status, int maxHp) {
    this.name = name;
    this.status = status;
    this.buff_Status = buff_Status;
    this.maxHp = maxHp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getStatus() {
    return status;
  }

  public void setStatus(int[] status) {
    this.status = status;
  }

  public int[] getBuff_Status() {
    return buff_Status;
  }

  public void setBuff_Status(int[] buff_Status) {
    this.buff_Status = buff_Status;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  private int attacking(Persona opponnent) {
    Random rand = new Random();
    opponnent.maxHp = opponnent.getMaxHp() - 10;
    int ataque;
    ataque = rand.nextInt() + 1;
    return opponnent.maxHp = opponnent.maxHp - (ataque - opponnent.defending());
  }

  private int defending() {
    int defese = 10;

    if (defese > 0) {
      defese = defese - attacking(null);
    } else {
      this.maxHp = maxHp - attacking(null);
    }

    return 1;
  }

  private void usingItem(Item item) {
    this.maxHp = maxHp - (int) item.getValue();
  }

  protected void takingDamage(Persona oponnent, int ataque) {
    this.maxHp = ataque - defending();
  }
}
