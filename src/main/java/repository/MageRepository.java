package repository;

import entity.Mage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {

    private Collection<Mage> collection;

    public MageRepository() {
        collection = new ArrayList<Mage>();
    }

    public Optional<Mage> find(String name) {
        Optional<Mage> mage = collection.stream().filter(element -> element.getName().equals(name)).findFirst();
        return mage;
    }

    public void delete(String name) {
        Optional<Mage> mage = this.find(name);
        if (mage.isPresent())
            collection.remove(mage);
        else
            throw new IllegalArgumentException();
    }

    public void save(Mage mage) {
        if (collection.stream().anyMatch(element -> element.getName().equals(mage.getName())))
            throw new IllegalArgumentException();
        else
            collection.add(mage);
    }
}
