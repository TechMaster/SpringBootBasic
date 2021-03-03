package com.baeldung.persistence.dao;

import com.baeldung.persistence.model.NewLocationToken;
import com.baeldung.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
