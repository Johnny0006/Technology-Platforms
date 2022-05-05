package controller;

import entity.Mage;
import repository.MageRepository;

import java.util.Optional;

public class MageController {

    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        String message;
        Optional<Mage> mage = repository.find(name);
        if (mage.isPresent())
            message = mage.toString();
        else
            message = "not found";

        return message;
    }

    public String delete(String name) {
        String message;
        try {
            repository.delete(name);
            message = "done";
        } catch (IllegalArgumentException e) {
            message = "not found";
        }

        return message;
    }

    public String save(String name, String level) {
        Mage mage = new Mage(name, Integer.parseInt(level));
        String message;
        try {
            repository.save(mage);
            message = "done";
        } catch (IllegalArgumentException e) {
            message = "bad request";
        }

        return message;
    }
}
