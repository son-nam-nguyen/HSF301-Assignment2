package service;


import dao.AccountDAO;
import pojo.Account;

public class AccountService implements IAccountService {
    private AccountDAO accountDAO;

    public AccountService(String persistenceName) {
        this.accountDAO = new AccountDAO(persistenceName);
    }
    @Override
    public Account getAccountByRole(String role) {
        return accountDAO.findByRole(role);
    }

    // Implement other service methods as needed
}
