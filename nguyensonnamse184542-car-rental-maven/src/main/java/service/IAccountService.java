package service;

import pojo.Account;

public interface IAccountService {
    Account getAccountByRole(String role);
    // Add other service methods as needed, e.g., create, update, delete, etc.
}
