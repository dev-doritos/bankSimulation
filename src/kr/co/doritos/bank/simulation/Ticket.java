package kr.co.doritos.bank.simulation;

public class Ticket {

    private int no;
    private int waitNo;
    private String createAtYmd;
    private String createAtHms;

    public Ticket(int no, int waitNo, String createAtYmd, String createAtHms) {
        this.no = no;
        this.waitNo = waitNo;
        this.createAtYmd = createAtYmd;
        this.createAtHms = createAtHms;
    }

    public int getNo() {
        return no;
    }

    public int getWaitNo() {
        return waitNo;
    }

    public String getCreateAtYmd() {
        return createAtYmd;
    }

    public String getCreateAtHms() {
        return createAtHms;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "no=" + no +
                ", waitNo=" + waitNo +
                ", createAtYmd='" + createAtYmd + '\'' +
                ", createAtHms='" + createAtHms + '\'' +
                '}';
    }
}