package model;

import java.util.List;

public class KnowYourCustomer {
    public static boolean verifyCustomer(String nationalID, List<AccountDetail> acccountList, Customer customer) {
        Customer custemp;
        for (AccountDetail accountDetail : acccountList) {
            custemp = (Customer) accountDetail;
            if (nationalID.equals(custemp.getNationalId())) {
                // (custemp.getTrustLevel() >= 30) {
                //    return true;
                //}
            }
        }

        //customer.setTrustLevel(50);//new customer have no Trust data -> set default = 50

        return true;
    }
}
