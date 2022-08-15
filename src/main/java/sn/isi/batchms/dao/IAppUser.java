package sn.isi.batchms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.batchms.entities.AppUser;

@Repository
public interface IAppUser extends JpaRepository<AppUser, Long> {
}
