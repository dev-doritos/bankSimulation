package kr.co.doritos.bank.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class BankWaitSystem {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    private final DateFormat HHmmss = new SimpleDateFormat("HHmmss");

    private final Bank bank;

    private int visitNo;

    public BankWaitSystem(Bank bank) {
        this.bank = bank;
    }

    public Ticket takeATicket() {
        Ticket ticket = new Ticket(++visitNo, bank.getQueueSize(), getCreateAtYmd(), getCreateAtHms());

        logger.info(String.format("[번호표 시스템] 순번 : %d, 대기순번 : %d, 생성일자 : %s"
                , ticket.getNo()
                , ticket.getWaitNo()
                , ticket.getCreateAtYmd() + " " + ticket.getCreateAtHms()));

        return ticket;
    }

    private String getCreateAtYmd() {
        return yyyyMMdd.format(Calendar.getInstance().getTime());
    }

    private String getCreateAtHms() {
        return HHmmss.format(Calendar.getInstance().getTime());
    }

}