package vn.techmaster.demojpa.model.bank;

import java.nio.ByteBuffer;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class BankAccount {
  @Id
  private String id;
  private int balance;

  public BankAccount(int balance) {
    this.id = Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(), Character.MAX_RADIX);

    this.balance = (balance > 0) ? balance : 0;
  }

  public BankAccount() {
    this(0);
  }

  public String getId() {
    return id;
  }


  public int getBalance() {
    return balance;
  }
  
  public void deposit(int amount) {
    balance += amount;
  }

  public void widthdraw(int amount) throws BankAcitivityException {
    if (amount > balance) throw new BankAcitivityException("Rút quá nhiều tiền");

    balance -= amount;
  }

  public void transfer(BankAccount bankAccount, int amount) throws BankAcitivityException {
    if (amount > balance) throw new BankAcitivityException("Chuyển quá nhiều tiền");
    balance -= amount;
    bankAccount.deposit(amount);
  }
}
