import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Credit {


    private int id;
    private int customerId;
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
    private Customer customer;

    public Credit(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Credit() {
    }

    public int getCreditYear() {
        return creditYear;
    }

    public void setCreditYear(Customer customer) {
        if(customer.getRestWorkYear() < maxCreditYear){
            this.creditYear = customer.getRestWorkYear();
        }else{
            this.creditYear = maxCreditYear;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
        setInitialPayment();
        setInterestAmount();
        setCreditAmount();
        setActionDate();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment() {
        if(this.maxCreditAmount.compareTo(homePrice) >= 0 ) {
            this.initialPayment = BigDecimal.ZERO;
        } else {
            this.initialPayment = homePrice.subtract(maxCreditAmount);
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
        this.lastPaymentDate = firstPaymentDate.plusYears(getCreditYear());
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

    public List monthlyPaymentPlan() throws SQLException {
        List<MonthlyPayment> paymentPlan = new ArrayList<>();

        int nbMonth= 12 * this.getCreditYear();

        BigDecimal baseAmount = this.getCreditAmount().divide(BigDecimal.valueOf(nbMonth),2,RoundingMode.HALF_EVEN);
        BigDecimal interestAmount = this.getInterestAmount().divide(BigDecimal.valueOf(nbMonth),2,RoundingMode.HALF_EVEN);
        BigDecimal totalAmount = baseAmount.add(interestAmount);




        for (int i = 0; i < nbMonth ; i++) {

            MonthlyPayment payment = new MonthlyPayment(DatabaseQueries.getCreditId(this.customer),
                                        this.getFirstPaymentDate().plusMonths(i),
                                        baseAmount, interestAmount, totalAmount);

            paymentPlan.add(payment);
        }

        return paymentPlan;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", homePrice=" + homePrice +
                ", initialPayment=" + initialPayment +
                ", creditAmount=" + creditAmount +
                ", interestAmount=" + interestAmount +
                ", firstPaymentDate=" + firstPaymentDate +
                ", lastPaymentDate=" + lastPaymentDate +
                ", actionDate=" + actionDate + '}';
    }
}
