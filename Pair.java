public class Pair {
        int index;
        boolean status;
        Pair(){
            index=0;
            status=false;
        }
        Pair(int index, boolean status)
        {
            this.status=status;
            this.index=index;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "index=" + index +
                    ", status=" + status +
                    '}';
        }

}
