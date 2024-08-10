package kr.co.doritos.bank.simulation;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

public class Manager implements Runnable {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final String name;
    private final Bank bank;
    private final int ability;
    private int completed;

    private final Random random = new SecureRandom();

    public Manager(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
        ability = getRandomAbility();

        logger.info(String.format("[%s] 출근완료. 능력치 : %d", name, ability));
    }

    private int getRandomAbility() {
        int r = random.nextInt(5);
        return ++r;
    }

    @Override
    public void run() {
        while (true) {
            Guest guest;
            try {
                guest = bank.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            doWork(guest);
        }
    }

    private void doWork(Guest guest) {

        // 고객을 n명 응대한 뒤 휴식을 가질 수 있음.
        if (completed >= bank.getRestCondition()) {
            rest();
            return;
        }

        // 고객맞이
        logger.info(String.format("[%s] 순번 %d [%s] 님, 어서오십시오.", name, guest.getTicket().getNo(), guest.getName()));
        
        // 고객업무 처리
        try { Thread.sleep(1000 * ability); } catch (Exception e) {} // ignore
        completed++;
        
        // 고객배웅
        logger.info(String.format("[%s] 순번 %d [%s] 님, 안녕히가십시오. (%d)", name, guest.getTicket().getNo(), guest.getName(), completed));

    }

    private void rest() {

        logger.info(String.format("[%s] %d 초간 휴식", name, bank.getRestSecond()));

        completed = 0;
    }
}