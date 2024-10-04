package org.aiteam.code;

public class Pour implements Operator<Integer> {

    @Override
    public Integer apply(Object... args) {
        Bottle i = (Bottle) args[0];
        Bottle j = (Bottle) args[1];

        if (i == null || j == null)
            return -1;




    }
}
