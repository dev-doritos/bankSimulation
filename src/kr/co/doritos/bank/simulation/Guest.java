package kr.co.doritos.bank.simulation;

public class Guest {

    private final String name;
    private Ticket ticket;

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    public void takeATicket(BankWaitSystem waitSystem) {
        this.ticket = waitSystem.takeATicket();
    }
}