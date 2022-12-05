import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FSM1 {
    int[][] transition_table;
    HashSet<Integer> finalStates;
    HashMap<Character, Integer> sigma;
    int trap;
    FSM1() {
        finalStates = new HashSet<>();
        sigma= new HashMap<>();
        sigma.put('i' , 0);
        sigma.put('n', 1);
        buildtable();
    }

    void buildtable() {
        transition_table = new int[3][sigma.size()];
        trap= transition_table.length;

        for (int i = 0; i < transition_table.length; i++)
            Arrays.fill(transition_table[i], trap);
        transition_table[0][sigma.get('i')] = 1;
        transition_table[1][sigma.get('n')] = 2;
        finalStates.add(2);
    }

    boolean recognize(String str) {
        int start = 0;
        int nextstate = 0;
        for (int i = 0; i < str.length(); i++) {
            if(sigma.containsKey(str.charAt(i)))//input alphabet
                nextstate = transition_table[start][sigma.get(str.charAt(i))];
            else
                nextstate = trap;

            if (nextstate == trap)
                return false;
            start = nextstate;
        }
        if (finalStates.contains(nextstate))
            return true;
        return false;
    }


    public static void main(String[] args) {
        FSM1 fsm1 = new FSM1();
        System.out.println(fsm1.recognize("in"));
        System.out.println(fsm1.recognize("inn"));
        System.out.println(fsm1.recognize("int"));
        System.out.println(fsm1.recognize("fin"));
        System.out.println(fsm1.recognize("i"));

    }
}
