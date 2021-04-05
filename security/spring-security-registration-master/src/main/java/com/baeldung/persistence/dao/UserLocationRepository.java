package com.baeldung.persistence.dao;

import com.baeldung.persistence.model.User;
import com.baeldung.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
