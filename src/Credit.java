import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Gulzar Safar on 9/25/2020
 */

public class Credit {

    private BigDecimal homePrice;
    private BigDecimal initialPayment;
    private BigDecimal creditAmount;
    private BigDecimal interestAmount;
    private LocalDate firstPaymentDate;
    private LocalDate lastPaymentDate;
    private LocalDate actionDate;

    private final int interestPercentage = 8;
    private final int creditYear = 25;
    private final BigDecimal maxCreditAmount = new BigDecimal(150000);

    public Credit() {
    }

    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
    }

    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(BigDecimal initialPayment) {
        this.initialPayment = initialPayment;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }

    public int getInterestPercentage() {
        return interestPercentage;
    }

    public int getCreditYear() {
        return creditYear;
    }

    public BigDecimal getMaxCreditAmount() {
        return maxCreditAmount;
    }
}
