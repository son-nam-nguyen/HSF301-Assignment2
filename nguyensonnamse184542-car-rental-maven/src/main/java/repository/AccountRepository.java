package repository;

import dao.AccountDAO;
import pojo.Account;

public class AccountRepository implements IAccountRepository {
    private AccountDAO accountDAO;

    public AccountRepository(String persistenceName) {
        this.accountDAO = new AccountDAO(persistenceName);
    }

    @Override
    public Account findByRole(String role) {
        return accountDAO.findByRole(role);
    }

    // Implement other methods as needed, forwarding calls to accountDAO
}
