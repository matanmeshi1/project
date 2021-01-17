package parking_lot;


public class CreditCardPayment implements Payment{

    @Override
    public boolean pay(float amount) {
        System.out.printf(String.format("paid %f in cash!", amount ));
        return true;
    }
}
