package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Comparator;


public class PurchaseOrderComparator implements Comparator<PurchaseOrder>{

    @Override
    public int compare(PurchaseOrder purchase1, PurchaseOrder purchase2) {
        if (purchase1.getOrderAmount()>purchase2.getOrderAmount()){
            return -1;
        }
        if (purchase1.getOrderAmount()<purchase2.getOrderAmount()){
            return 1;
        }
        return 0;

    }
}
