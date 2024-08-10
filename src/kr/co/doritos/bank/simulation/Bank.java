package kr.co.doritos.bank.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Bank {

    private final BlockingQueue<Guest> queue;
    private final List<Manager> managerList;

    private final int managerCount = 5;
    private final ExecutorService executorService;

    private final int restCondition = 20;
    private final int restSecond = 10;

    public Bank() {
        queue = new LinkedBlockingQueue<>();
        managerList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(managerCount);
    }

    public int getQueueSize() { return queue.size(); }

    public int getRestCondition() { return restCondition; }

    public int getRestSecond() { return restSecond; }

    public void addGuest(Guest guest) { queue.add(guest); }

    public Guest take() throws InterruptedException { return queue.take(); }

    public void open() {
        int managerCount = 5;

        for (int i = 1; i <= managerCount; i++) {
            managerList.add(new Manager(String.format("은행원%d", i), this));
        }

        managerList.stream().forEach(e -> executorService.execute(e));
    }

    public void close() {
        executorService.shutdown();
    }
}