import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FSM2 {
    int[][] transition_table;
    HashSet<Integer> finalStates;
    HashMap<Character, Integer> sigma;
    int trap;
    FSM2() {
        finalStates = new HashSet<>();
        sigma= new HashMap<>();
        sigma.put('i' , 0);
        sigma.put('n', 1);
        buildtable();
    }

    void buildtable() {
        transition_table = new int[3][sigma.size()];
        trap=transition_table.length;
        for (int i = 0; i < transition_table.length; i++)
            Arrays.fill(transition_table[i], trap);
        transition_table[0][sigma.get('i')] = 1;
        transition_table[1][sigma.get('n')] = 2;
        finalStates.add(2);
    }

    Pair recognize(String str) {
        int start = 0;
        int nextstate = 0;
        Pair res= new Pair();

        for (int i = 0; i < str.length(); i++) {

            if(sigma.containsKey(str.charAt(i)))//input alphabet
                nextstate = transition_table[start][sigma.get(str.charAt(i))];
            else
                nextstate = trap;

            if (nextstate == trap)
            {
                return new Pair();
            }
            else if(finalStates.contains(nextstate))
                return new Pair(res.index+1, res.status);
            res.index= i+1;
            res.status=true;
            start = nextstate;
        }
        if (finalStates.contains(nextstate))
            return res;
        return new Pair();
    }

    public static void main(String[] args)
    {
        FSM2 fsm2 = new FSM2();
        System.out.println(fsm2.recognize("in"));
        System.out.println(fsm2.recognize("inn"));
        System.out.println(fsm2.recognize("inttttt"));
        System.out.println(fsm2.recognize("fin"));
        System.out.println(fsm2.recognize("i"));
    }

}
