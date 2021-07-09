package techmaster.vn.jpa.demooneone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import techmaster.vn.jpa.demooneone.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
