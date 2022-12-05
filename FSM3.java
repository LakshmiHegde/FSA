import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FSM3 {
    int[][] transition_table;
    HashSet<Integer> finalStates;
    HashMap<Character, Integer> sigma;
    int trap;

    FSM3() {
        finalStates = new HashSet<>();
        sigma= new HashMap<>();
        sigma.put('i' , 0);
        sigma.put('n', 1);
        buildtable();
    }

    void buildtable() {
        transition_table = new int[3][sigma.size()];
        trap = transition_table.length;

        for (int i = 0; i < transition_table.length; i++)
            Arrays.fill(transition_table[i], trap);

        transition_table[0][sigma.get('i')] = 1;
        transition_table[1][sigma.get('n')] = 2;

        finalStates.add(2);
    }

    Pair recognize(String str , int index) {
        int start = 0;
        int nextstate = 0;
        Pair res= new Pair();

        for (int i = index; i < str.length(); i++) {

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
        FSM3 fsm3 = new FSM3();
        System.out.println(fsm3.recognize("in" , 0));
        System.out.println(fsm3.recognize("inn", 1));
        System.out.println(fsm3.recognize("inttttt" , 0));
        System.out.println(fsm3.recognize("fin", 1));
        System.out.println(fsm3.recognize("i", 0));
        System.out.println(fsm3.recognize("fit", 1));
        System.out.println(fsm3.recognize("fin", 0));


    }
}
