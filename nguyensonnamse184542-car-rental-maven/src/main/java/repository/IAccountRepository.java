package repository;

import pojo.Account;

public interface IAccountRepository {
    Account findByRole(String role);
    // Add other methods as needed, e.g., save, update, delete, findAll, etc.
}
