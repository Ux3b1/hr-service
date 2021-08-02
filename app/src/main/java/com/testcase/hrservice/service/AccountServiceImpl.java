package com.testcase.hrservice.service;

import com.testcase.hrservice.exception.AccountAlreadyBlockedException;
import com.testcase.hrservice.exception.AccountNotFoundException;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.domain.Account;
import com.testcase.hrservice.model.domain.Post;
import com.testcase.hrservice.model.domain.Role;
import com.testcase.hrservice.model.logging.ErrorLog;
import com.testcase.hrservice.repository.AccountRepository;
import com.testcase.hrservice.repository.PostRepository;
import com.testcase.hrservice.repository.RoleRepository;
import com.testcase.hrservice.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoggingService loggingService;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(PasswordGenerator.generate());
        Post post = getPostFromDb(account.getPost());
        Role role = getRoleFromDb(account.getRole());

        account.setPost(post);
        account.setRole(role);

        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        try {
            Account accountFromDb = accountRepository.findById(account.getId()).orElseThrow();
            accountFromDb.setRole(getRoleFromDb(account.getRole()));
            accountFromDb.setPost(getPostFromDb(account.getPost()));
            accountFromDb.setFio(account.getFio());
            accountFromDb.setUserName(account.getUserName());
            return accountRepository.save(accountFromDb);
        } catch (NoSuchElementException exception) {
            String message = String.format("Не найдена учетная запись с данным id: %s", account.getId());

            loggingService.logIt(ErrorLog.builder()
                    .system(SystemType.HIBERNATE)
                    .message(message)
                    .build());

            throw new AccountNotFoundException(message, exception);
        }
    }

    @Override
    public void blockAccount(Long id) {

        try {
            Account account = accountRepository.findById(id).orElseThrow();
            if (Boolean.FALSE.equals(account.getIsActive())) {
                throw new AccountAlreadyBlockedException();
            }
            account.block();
            accountRepository.save(account);
        } catch (NoSuchElementException exception) {
            String message = String.format("Не найдена учетная запись с данным id: %s", id);

            loggingService.logIt(ErrorLog.builder()
                    .system(SystemType.HIBERNATE)
                    .message(message)
                    .build());

            throw new AccountNotFoundException(message, exception);
        } catch (AccountAlreadyBlockedException exception) {
            String message = String.format("Учетная запись с данным id уже заблокирована. id: %s", id);

            loggingService.logIt(ErrorLog.builder()
                    .system(SystemType.HIBERNATE)
                    .message(message)
                    .build());

            throw new AccountNotFoundException(message, exception);
        }
    }

    private Post getPostFromDb(Post post) {
        Post postFromDb = postRepository.findByName(post.getName());
        if (postFromDb == null) {
            postFromDb = postRepository.save(post);
        }
        return postFromDb;
    }

    private Role getRoleFromDb(Role role) {
        Role roleFromDb = roleRepository.findByName(role.getName());
        if (roleFromDb == null) {
            roleFromDb = roleRepository.save(role);
        }
        return roleFromDb;
    }
}
