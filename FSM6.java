import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class FSA_INTO{
    int[][] transition_table;
    HashSet<Integer> finalStates;
    HashMap<Character, Integer> sigma;
    int trap;

    FSA_INTO() {
        finalStates = new HashSet<>();
        sigma= new HashMap<>();
        sigma.put('i' , 0);
        sigma.put('n', 1);
        sigma.put('t', 2);
        sigma.put('o', 3);
        buildtable();
    }

    void buildtable() {
        transition_table = new int[5][sigma.size()];
        trap = transition_table.length;
        for (int i = 0; i < transition_table.length; i++)
            Arrays.fill(transition_table[i], trap);
        transition_table[0][sigma.get('i')] = 1;
        transition_table[1][sigma.get('n')] = 2;
        transition_table[2][sigma.get('t')] = 3;
        transition_table[3][sigma.get('o')] = 4;
        finalStates.add(4);
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
}

public class FSM6 {
    void recognize(String str, int index)
    {
        Pair inp = new FSM3().recognize(str,index);
        Pair intop= new FSA_INTO().recognize(str,index);

        if(inp.status && intop.status)
        {
            //both patterns matched, find the longest character matching
            if(inp.index > intop.index)
                System.out.println("( IN , "+inp.index+")");
            else
                System.out.println("( INTO , "+intop.index+")");
        }
        else if(inp.status)
            System.out.println("( IN , "+inp.index+")");
        else if(intop.status)
            System.out.println("( INTO , "+intop.index+")");
        else
            System.out.println("( - , - )");
    }
    public static void main(String args[])
    {
        new FSM6().recognize("finp",1);
        new FSM6().recognize("finto",1);
        new FSM6().recognize("fintp",1);

        new FSM6().recognize("int",0);
        new FSM6().recognize("in",0);
        new FSM6().recognize("into",0);
        new FSM6().recognize("innn",0);

    }
}
