package lc.topliked.medium;

import java.util.TreeSet;

class Party implements Comparable<Party> {

    int size;
    String name;
    int time;

    public Party(int size, String name, int time) {
        super();
        this.size = size;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Party [size=" + size + ", name=" + name + ", time=" + time + "]";
    }

    @Override
    public int compareTo(Party o) {
        return Integer.compare(o.time, time);
    }

}
public class YelpWaitingList {
    TreeSet<Party> waiting = new TreeSet<>();

    public void addToWaiting(Party p) {
        waiting.add(p);

    }

    public String findWaitingParty(int size) {
        for (Party p : waiting) {
            if (p.size <= size) {
                return p.name;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        YelpWaitingList sol = new YelpWaitingList();
        Party[] input = new Party[] { new Party(4, "test1", 100), new Party(6, "test2", 200) };
        for (Party a : input) {
            sol.addToWaiting(a);
        }
        System.out.println(sol.findWaitingParty(7));
        sol.waiting.stream().forEach(party -> System.out.println(party));
        // TODO Auto-generated method stub

    }

}
