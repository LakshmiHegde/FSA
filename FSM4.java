import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FSM4 {
    int[][] transition_table;
    HashSet<Integer> finalStates;
    HashMap<Character, Integer> sigma;
    int trap;
    FSM4() {
        finalStates = new HashSet<>();
        sigma= new HashMap<>();
        sigma.put('i' , 0);
        sigma.put('n', 1);
        sigma.put('t', 2);
        buildtable();
    }

    void buildtable() {
        transition_table = new int[4][sigma.size()];
        trap = transition_table.length;
        for (int i = 0; i < transition_table.length; i++)
            Arrays.fill(transition_table[i], trap);
        transition_table[0][sigma.get('i')] = 1;
        transition_table[1][sigma.get('n')] = 2;
        transition_table[2][sigma.get('t')] = 3;
        transition_table[3][sigma.get('t')] = 3;

        finalStates.add(3);

    }

    Pair recognize(String str , int index) {
        int start = 0;
        int nextstate = 0;
        Pair res= new Pair();

        int i=index;
        for (i = index; i < str.length(); i++) {

            if(sigma.containsKey(str.charAt(i)))//input alphabet
                nextstate = transition_table[start][sigma.get(str.charAt(i))];
            else
                nextstate = trap;

            if (nextstate == trap)
            {
                return new Pair();
            }
            else if(finalStates.contains(nextstate))
                break;
            res.index= i+1;
            res.status=true;
            start = nextstate;
        }
        if(finalStates.contains(nextstate))
        {
            i++;
            while(i<str.length() && str.charAt(i) == 't')
                i++;
            res.status=true;
            res.index=i;
            return res;
        }
        return new Pair();

    }

    public static void main(String[] args)
    {
        FSM4 fsm4 = new FSM4();
        System.out.println(fsm4.recognize("int" , 0));
        System.out.println(fsm4.recognize("intt", 0));
        System.out.println(fsm4.recognize("finn" , 0));
        System.out.println(fsm4.recognize("finttttoot", 1));
        System.out.println(fsm4.recognize("fint", 0));
        System.out.println(fsm4.recognize("fint", 1));
    }
}
