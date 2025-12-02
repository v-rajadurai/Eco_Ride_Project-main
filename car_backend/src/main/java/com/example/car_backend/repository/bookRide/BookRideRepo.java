package com.example.car_backend.repository.bookRide;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.car_backend.model.bookRide.BookRide;

/**
 * BookRide
 */
@Repository
public interface BookRideRepo extends JpaRepository<BookRide,Integer> {
    @Query("select e from BookRide e where e.leaving in :leaving")
    List<BookRide>filterdRidesLeavingFrom(@Param("leaving")List<String>leaving);    
    
    @Query("select e from BookRide e where e.going in :going")
    List<BookRide>filterdRidesGoingTo(@Param("going")List<String>going);    
    
    @Query("select e from BookRide e where e.rideCompletionStatus='no'")
    List<BookRide> findAllCompleted();    
    @Query("select e from BookRide e where e.email = :email")
    List<BookRide> findByEmail(@Param("email")String email);    
    
    @Query("select e from BookRide e where e.Id in :allId")
    List<BookRide> findByRideIds(@Param("allId")List<Integer> allId);    
    
    @Query("select e from BookRide e where e.leaving in :leaving OR e.going in :going")
    List<BookRide>filterdRides(@Param("leaving")List<String>leaving,@Param("going")List<String>going);    
}