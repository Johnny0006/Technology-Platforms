package controller;

import entity.Mage;
import repository.MageRepository;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MageControllerTest {

    @Test
    public void find_existingObject_optionalExistingObjectToString() {
        Mage mage = new Mage("Rudolf", 1);
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find("Rudolf")).thenReturn(Optional.ofNullable(mage));
        MageController mageController = new MageController(mageRepository);

        String message = mageController.find("Rudolf");

        assertThat(message).isEqualTo(Optional.ofNullable(mage).toString());
    }

    @Test
    public void find_notExistingObject_NotFoundString() {
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find("Rudolf")).thenReturn(Optional.ofNullable(null));
        MageController mageController = new MageController(mageRepository);

        String message = mageController.find("Rudolf");

        assertThat(message).isEqualTo("not found");
    }

    @Test
    public void delete_existingObject_doneString() {
        MageRepository mageRepository = mock(MageRepository.class);
        doNothing().when(mageRepository).delete("Rudolf");
        MageController mageController = new MageController(mageRepository);

        String message = mageController.delete("Rudolf");

        assertThat(message).isEqualTo("done");
    }

    @Test
    public void delete_notExistingObject_NotFoundString() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mageRepository).delete("Rudolf");
        MageController mageController = new MageController(mageRepository);

        String message = mageController.delete("Rudolf");

        assertThat(message).isEqualTo("not found");
    }

    @Test
    public void save_newObjectWithNewPrimaryKey_doneString() {
        Mage mage = new Mage("Rudolf", 5);
        MageRepository mageRepository = mock(MageRepository.class);
        doNothing().when(mageRepository).save(mage);
        MageController mageController = new MageController(mageRepository);

        String message = mageController.save(mage.getName(), Integer.toString(mage.getLevel()));

        assertThat(message).isEqualTo("done");
    }

    @Test
    public void save_newObjectWithAlreadyUsedPrimaryKey_badRequestString() {
        Mage mage = new Mage("Rudolf", 10);
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mageRepository).save(mage);
        MageController mageController = new MageController(mageRepository);

        String message = mageController.save(mage.getName(), Integer.toString(mage.getLevel()));

        assertThat(message).isEqualTo("bad request");
    }
}
