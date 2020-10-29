package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyPayment {

    private int id;
    private int creditId;
    private LocalDate paymentDate;
    private BigDecimal baseAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;

    public MonthlyPayment(int creditId, LocalDate paymentDate, BigDecimal baseAmount,
                          BigDecimal interestAmount, BigDecimal totalAmount) {
        this.creditId = creditId;
        this.paymentDate = paymentDate;
        this.baseAmount = baseAmount;
        this.interestAmount = interestAmount;
        this.totalAmount = totalAmount;
    }

    public MonthlyPayment() {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "model.MonthlyPayment{" +
                "id=" + id +
                ", creditId=" + creditId +
                ", paymentDate=" + paymentDate +
                ", baseAmount=" + baseAmount +
                ", interestAmount=" + interestAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
