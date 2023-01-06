package derry.club.todo.repository;

import derry.club.todo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.userId=:id")
    List<Item> findAllByUserId(@Param("id") long id);

    @Query("SELECT i FROM Item i WHERE i.userId=:id AND i.isCompleted=false")
    List<Item> findUnFinishedItemsByUserId(@Param("id") long userId);

    @Query("SELECT i FROM Item i WHERE i.userId=:id AND i.isCompleted=true")
    List<Item> findFinishedItemsByUserId(@Param("id") long userId);

}
