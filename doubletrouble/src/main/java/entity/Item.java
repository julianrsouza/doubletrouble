package entity;

public class Item {

  private String nome;
  private String Status_Effect;
  private double value;
  private String discription;

  public Item(
    String nome,
    String Status_Effect,
    double value,
    String discription
  ) {
    this.nome = nome;
    this.Status_Effect = Status_Effect;
    this.value = value;
    this.discription = discription;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getStatus_Effect() {
    return Status_Effect;
  }

  public void setStatus_Effect(String status_Effect) {
    Status_Effect = status_Effect;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public String getDiscription() {
    return discription;
  }

  public void setDiscription(String discription) {
    this.discription = discription;
  }
}
