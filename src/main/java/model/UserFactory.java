package model;

import utils.DataLoader;

public class UserFactory {
    public static User validUser() {
        return new User(
                DataLoader.loadProperty("correct_username"),
                DataLoader.loadProperty("correct_password"));
    }
    public static User invalidUser() {
        return new User(
                DataLoader.loadProperty("wrong_username"),
                DataLoader.loadProperty("wrong_password")
        );
    }
    public static User emptyUser() {
        return new User(
                DataLoader.loadProperty("empty_username"),
                DataLoader.loadProperty("empty_password"));
    }
}
