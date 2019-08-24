package online.patologia.czaropedal.service;

import online.patologia.czaropedal.model.MyUser;
import online.patologia.czaropedal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public MyUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public MyUser save(MyUser myUser) {
        return userRepo.save(myUser);
    }


}
