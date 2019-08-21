package online.patologia.czaropedal.repo;

import online.patologia.czaropedal.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface UserRepo extends JpaRepository<MyUser,Long> {
    MyUser findByUsername(String username);

//   @Query(value = "INSERT INTO my_user_item_list VALUES (?1,?2)", nativeQuery = true)
//   void addProductToCart(Long user_id, Long product_id);
}
