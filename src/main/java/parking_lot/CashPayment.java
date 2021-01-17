package parking_lot;

public class CashPayment implements Payment{
    @Override
    public boolean pay(float amount) {
        System.out.printf(String.format("paid %f in cash!", amount ));
        return true;
    }
}
