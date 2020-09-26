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

    private int creditYear;


    private final int interestPercentage = 8;
    private final int maxCreditYear = 25;
    private final BigDecimal maxCreditAmount = new BigDecimal(150000);

    public Credit() {
    }
    public int getCreditYear() {
        return creditYear;
    }

    public void setCreditYear(Customer customer) {
        if(customer.getRestWorkYear() > maxCreditYear){
            this.creditYear = maxCreditYear - customer.getRestWorkYear();
        }else{
            this.creditYear = maxCreditYear;
        }
    }


    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
        setInitialPayment();
        setInterestAmount();
        setCreditAmount();
    }

    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment() {
        if(this.maxCreditAmount.compareTo(this.homePrice) >= 0 ) {
            this.initialPayment = new BigDecimal(0.00000);
        } else {
            this.initialPayment = this.homePrice.subtract(this.maxCreditAmount);
        }
    }

    public void setInitialPayment(BigDecimal initialPayment){
        this.initialPayment = initialPayment;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount() {
        this.creditAmount = homePrice.subtract(initialPayment);
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getInterestAmount() {

        return interestAmount;
    }

    public void setInterestAmount() {

        this.interestAmount = homePrice.subtract(initialPayment).multiply(BigDecimal.valueOf(interestPercentage)).divide(BigDecimal.valueOf(100));
    }

    public void setInterestAmount(BigDecimal interestAmount) {

        this.interestAmount = interestAmount;
    }


    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate() {
        this.firstPaymentDate = actionDate.plusMonths(1);
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
    public void setLastPaymentDate() {
        this.lastPaymentDate = firstPaymentDate.plusYears(creditYear);
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public void setActionDate() {
        this.actionDate = LocalDate.now();
        setFirstPaymentDate();
        setLastPaymentDate();
    }
    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }

    public int getInterestPercentage() {
        return interestPercentage;
    }

    public int getMaxCreditYear() {
        return maxCreditYear;
    }

    public BigDecimal getMaxCreditAmount() {
        return maxCreditAmount;
    }



}
