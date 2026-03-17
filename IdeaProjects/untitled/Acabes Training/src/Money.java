import java.math.BigDecimal;

import java.util.Currency;
public class Money {
    private final BigDecimal totalAmount;
    private final Currency currency;


    public Money(BigDecimal totalAmount, Currency currency) {

        if (totalAmount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.totalAmount = totalAmount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return totalAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return totalAmount + " " + currency.getCurrencyCode();
    }
}
