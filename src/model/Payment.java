package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private static int counter = 1;

    private final int id;
    private final double amount;
    private final PaymentMethod method;
    private final LocalDateTime paidAt;

    Payment(double amount, PaymentMethod method) {
        this.id = counter++;
        this.amount = amount;
        this.method = method;
        this.paidAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public PaymentMethod getMethod() { return method; }
    public LocalDateTime getPaidAt() { return paidAt; }

    @Override
    public String toString() {
        return String.format("Төлем №%d | %.0f тг | %s | %s", id, amount, method.getLabel(),
                paidAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }
}
