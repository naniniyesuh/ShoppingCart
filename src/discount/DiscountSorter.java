package discount;

import java.util.Comparator;

public class DiscountSorter implements Comparator<Campaign>{

    @Override
    public int compare(Campaign c1, Campaign c2) {
        return c2.getTotalDiscount().compareTo(c1.getTotalDiscount());
    }
}
