package kr.co.doritos.bank.simulation;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

public class BankSimulation {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final Bank bank;
    private final BankWaitSystem waitSystem;
    private final Random random;

    public BankSimulation() {
        bank = new Bank();
        waitSystem = new BankWaitSystem(bank);
        random = new SecureRandom();
    }

    public void simulation() {

        bank.open();

        while (true) {
            // 고객 생성 및 고객 은행 입장
            Guest guest = new Guest(randomString());

            logger.info(String.format("[%s] 손님 방문", guest.getName()));

            // 고객 번호표 발행
            guest.takeATicket(waitSystem);

            // 은행 고객 대기열 등록
            bank.addGuest(guest);

            try { Thread.sleep(1000 * randomInt()); } catch (Exception e) { break; }
        }
    }

    private int randomInt() {
        return random.nextInt(10);
    }

    private String randomString() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 16);
    }

    public static void main(String[] args) {
        BankSimulation simulation = new BankSimulation();
        simulation.simulation();
    }
}