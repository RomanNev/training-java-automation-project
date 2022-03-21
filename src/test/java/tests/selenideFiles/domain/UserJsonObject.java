package tests.selenideFiles.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserJsonObject {
    public String name;
    public String surname;
    @SerializedName("favorite_parrot")
    public List<String> favorite_parrot;
    public Address address;

}
