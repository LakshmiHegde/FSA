public class FSM5 {
    void recognize(String str, int index)
    {
        Pair inp = new FSM3().recognize(str,index);
        Pair intp= new FSM4().recognize(str,index);

        if(inp.status && intp.status)
        {
            //both patterns matched, find the longest character matching
            if(inp.index > intp.index)
                System.out.println("( IN , "+inp.index+")");
            else
                System.out.println("( INTP , "+intp.index+")");
        }
        else if(inp.status)
            System.out.println("( IN , "+inp.index+")");
        else if(intp.status)
            System.out.println("( INTP , "+intp.index+")");
        else
            System.out.println("( - , - )");
    }
    public static void main(String args[])
    {
        new FSM5().recognize("finp",1);
        new FSM5().recognize("fintttto",1);
        new FSM5().recognize("finp",0);
        new FSM5().recognize("int",0);
        new FSM5().recognize("in",0);
        new FSM5().recognize("iint",0);
        new FSM5().recognize("innn",0);
    }
}
