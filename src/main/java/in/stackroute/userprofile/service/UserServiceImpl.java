package in.stackroute.userprofile.service;

import in.stackroute.userprofile.exceptions.CredentialsMismatchException;
import in.stackroute.userprofile.exceptions.UserExistsException;
import in.stackroute.userprofile.model.User;
import in.stackroute.userprofile.model.UserCredentials;
import in.stackroute.userprofile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserProfileRepository repository;
    @Override
    public User registerUser(User newUser) throws UserExistsException {
        if(repository.existsByEmail(newUser.getEmail())){
            throw new UserExistsException("User with the email already Exixts");
        }
        User user = repository.save(newUser);
        return user;

    }

    @Override
    public boolean authenticateUser(UserCredentials credentials) throws CredentialsMismatchException {
        Optional<User> userByEmail = repository.getUserByEmail(credentials.getEmail());
        if(userByEmail.isEmpty()){
            throw new CredentialsMismatchException("InValid credentials");
        }
        User user = userByEmail.get();
        if (user.getPassword().equals(credentials.getPassword())){
            return true;
        }else{
            throw new CredentialsMismatchException("InValid credentials");
        }


    }
}
