package repository;

import entity.Mage;
import repository.MageRepository;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTest {

    @Test
    public void find_notExistingObject_optionalNullObject() {
        MageRepository repository = new MageRepository();
        Optional<Mage> mage = repository.find("Rudolf");
        assertThat(mage).isEmpty();
    }

    @Test
    public void find_ExistingObject_optionalExistingObject() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Rudolf", 1));
        Optional<Mage> mage = repository.find("Rudolf");
        assertThat(mage).isNotEmpty();
    }

    @Test
    public void delete_notExistingObject_IllegalArgumentException() {
        MageRepository repository = new MageRepository();
        assertThatThrownBy(() -> {
            repository.delete("Rudolf");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void save_alreadyExistingObject_IllegalArgumentException() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Rudolf", 1));
        Mage mage = new Mage("Rudolf", 3);
        assertThatThrownBy(() -> {
            repository.save(mage);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
