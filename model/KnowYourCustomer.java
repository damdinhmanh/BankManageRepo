package model;

import java.util.List;

import utils.ConstantVars;

public class KnowYourCustomer {
    public static boolean verifyCustomer(String nationalID, List<AccountDetail> acccountList, AccountDetail newAccAdded) {
        Customer custemp;
        for (AccountDetail accountDetail : acccountList) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                custemp = (Customer) accountDetail;
                if (nationalID.equals(custemp.getNationalId())) {
                    if (custemp.getTrustLevel() >= 30) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
