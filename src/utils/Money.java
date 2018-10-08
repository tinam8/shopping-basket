package utils;

public class Money {
  private int amount = 0;

  public Money(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object obj) {
    return ((Money) obj).amount == amount;
  }
}
