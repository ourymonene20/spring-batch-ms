package sn.isi.batchms.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import sn.isi.batchms.entities.AppUser;

@Component
public class AppUserProcessor implements ItemProcessor<AppUser, AppUser> {
    @Override
    public AppUser process(AppUser appUser) throws Exception {

            return appUser;
    }
}
