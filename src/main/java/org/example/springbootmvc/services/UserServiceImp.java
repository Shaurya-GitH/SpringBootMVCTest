package org.example.springbootmvc.services;

import org.example.springbootmvc.entities.Users;
import org.example.springbootmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    @Autowired
    UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public boolean checkLogin(Users user) {
        Optional<Users> usersOptional= userRepository.findById(user.getName());
        if(usersOptional.isPresent()){
            Users dbUser= usersOptional.get();
           if(BCrypt.checkpw(user.getPassword(),dbUser.getPassword()))return true ;
           else return false;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean registerUser(Users user) {
        if(userRepository.existsById(user.getName()))return false;
        else{
            try {
                String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                Users dbUser = new Users();
                dbUser.setName(user.getName());
                dbUser.setEmail(user.getEmail());
                LocalDate dob=user.getDob();
                dbUser.setDob(dob);
                dbUser.setPassword(hashPw);
                userRepository.save(dbUser);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
