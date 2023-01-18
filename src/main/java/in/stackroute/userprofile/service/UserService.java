package in.stackroute.userprofile.service;

import in.stackroute.userprofile.exceptions.CredentialsMismatchException;
import in.stackroute.userprofile.exceptions.UserExistsException;
import in.stackroute.userprofile.model.User;
import in.stackroute.userprofile.model.UserCredentials;

public interface UserService {
    User registerUser(User newUser) throws UserExistsException;

    boolean authenticateUser(UserCredentials credentials) throws CredentialsMismatchException;
}
