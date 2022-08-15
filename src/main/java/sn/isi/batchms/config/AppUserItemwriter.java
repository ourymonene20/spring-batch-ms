package sn.isi.batchms.config;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.isi.batchms.dao.IAppUser;
import sn.isi.batchms.entities.AppUser;

import java.util.List;
@Component
public class AppUserItemwriter implements ItemWriter<AppUser> {

    @Autowired private IAppUser userRepository;
    @Override
    public void write(List<? extends AppUser> list) throws Exception {
        userRepository.saveAll(list);

    }
}
