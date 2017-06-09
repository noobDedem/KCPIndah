package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.UserAccount;

/**
 * Created by SpookyBastard on 6/3/2017.
 */

public interface UserAccountDAO {
    void saveUserAccount(UserAccount userAccount) throws Exception;
    UserAccount getUserAccount(String username) throws Exception;
}
