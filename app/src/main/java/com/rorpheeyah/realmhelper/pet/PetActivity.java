package com.rorpheeyah.realmhelper.pet;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rorpheeyah.realmhelper.R;
import com.rorpheeyah.realmhelper.databinding.ActivityPetBinding;
import com.rorpheeyah.realmhelper.detail.PetDetailActivity;
import com.rorpheeyah.realmhelper.dragdrop.SimpleItemTouchHelperCallback;
import com.rorpheeyah.realmhelper.helper.RealmHelper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class PetActivity extends AppCompatActivity {

    private PetAdapter adapter;
    private ActivityPetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        setRecyclerView();

        // fake user
        RealmHelper.saveList(fakeUsers());

        // query local db
        ArrayList<Pet> pets;
        if(RealmHelper.findAll(Pet.class) == null || RealmHelper.findAll(Pet.class).size() == 0){
            RealmHelper.saveList(fakePets());
        }

        pets = new ArrayList<>(RealmHelper.findAll(Pet.class));
        adapter.setPets(pets);
    }

    /**
     * Fake pet data
     */
    private ArrayList<Pet> fakePets(){
        ArrayList<Pet> pets = new ArrayList<>();

        Pet pet = new Pet();
        pet.setId(1);
        pet.setOwnerId(1);
        pet.setName("Yabai");
        pet.setGender(false);
        pet.setBehavior("Pooping Everywhere");
        pet.setImgUrl("https://bit.ly/3xKJ3z6");
        pet.setDistance(12000);
        pet.setColor("Black");
        pet.setStory("Yabai are simply adorable! They are clean and quiet little creatures making an ideal pet. Yabai can extremely active and playful and take well when being handled correctly.");
        pet.setDob(getDateTimeStamp("2018.07.11"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(4);
        pets.add(pet);

        pet = new Pet();
        pet.setId(2);
        pet.setOwnerId(3);
        pet.setName("Daisuke");
        pet.setGender(true);
        pet.setBehavior("Playing Tug-of-War");
        pet.setImgUrl("https://bit.ly/2ZOz1R4");
        pet.setDistance(102.5);
        pet.setColor("Orange");
        pet.setStory("Daisuke are simply adorable! They are clean and quiet little creatures making an ideal pet. Daisuke can extremely active and playful and take well when being handled correctly.");
        pet.setDob(getDateTimeStamp("2017.09.11"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(5.4);
        pets.add(pet);

        pet = new Pet();
        pet.setId(3);
        pet.setOwnerId(2);
        pet.setName("Ara Ara");
        pet.setGender(false);
        pet.setBehavior("Walking in Circles");
        pet.setImgUrl("https://bit.ly/3phpNW1");
        pet.setDistance(3000);
        pet.setColor("Gray");
        pet.setStory("Ara Ara is stupid !");
        pet.setDob(getDateTimeStamp("2009.09.03"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(1);
        pets.add(pet);

        pet = new Pet();
        pet.setId(4);
        pet.setOwnerId(1);
        pet.setName("Uwu");
        pet.setGender(true);
        pet.setBehavior("Mounting and Masturbation");
        pet.setImgUrl("https://bit.ly/3G3yn1q");
        pet.setDistance(500);
        pet.setColor("Gray");
        pet.setStory("Uwu is slut !");
        pet.setDob(getDateTimeStamp("2013.03.21"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(3);
        pets.add(pet);

        pet = new Pet();
        pet.setId(5);
        pet.setOwnerId(3);
        pet.setName("Urusai");
        pet.setGender(false);
        pet.setBehavior("Licking - Compulsive");
        pet.setImgUrl("https://bit.ly/3Iarygv");
        pet.setDistance(1200);
        pet.setColor("Gray");
        pet.setStory("Urusai is slut !");
        pet.setDob(getDateTimeStamp("2020.02.13"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(5.4);
        pets.add(pet);


        pet = new Pet();
        pet.setId(5);
        pet.setOwnerId(2);
        pet.setName("Urusai");
        pet.setGender(true);
        pet.setBehavior("Licking - Compulsive");
        pet.setImgUrl("https://bit.ly/3lvfnRB");
        pet.setDistance(12.5);
        pet.setColor("Gray");
        pet.setStory("Urusai is known for their strong bond with their human family, meaning once they've formed a bond with you they can go almost anywhere outdoors without leaving your shoulder! They are extremely intelligent and could learn their name and new tricks. Sugar Gliders are very clean pets and require little to none maintenance.");
        pet.setDob(getDateTimeStamp("2015.10.21"));
        pet.setCreatedDate(getDateTimeStamp("2015.10.21"));
        pet.setWeight(5.4);
        pets.add(pet);

        pet = new Pet();
        pet.setId(6);
        pet.setOwnerId(1);
        pet.setName("Yamate");
        pet.setGender(false);
        pet.setBehavior("Focusing on You");
        pet.setImgUrl("https://n.pr/3xL25oS");
        pet.setDistance(1005);
        pet.setColor("Green");
        pet.setStory("Yamate is known for their strong bond with their human family, meaning once they've formed a bond with you they can go almost anywhere outdoors without leaving your shoulder! They are extremely intelligent and could learn their name and new tricks. Sugar Gliders are very clean pets and require little to none maintenance.");
        pet.setDob(getDateTimeStamp("2010.09.11"));
        pet.setCreatedDate(currentDateTimeStamp());
        pet.setWeight(2);
        pets.add(pet);

        return pets;
    }

    /**
     * Fake user data
     */
    private ArrayList<User> fakeUsers(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setCreatedDate(currentDateTimeStamp());
        user.setDob(getDateTimeStamp("1952.10.07"));
        user.setGender(false);
        user.setName("Vladimir Putin");
        user.setOccupation("Socialist & Developer");
        user.setImgUrl("https://bit.ly/3G3Hbo7");
        users.add(user);

        user = new User();
        user.setId(2);
        user.setCreatedDate(currentDateTimeStamp());
        user.setDob(getDateTimeStamp("1953.06.15"));
        user.setGender(false);
        user.setName("Xi Jinping");
        user.setOccupation("Communist & Developer");
        user.setImgUrl("https://bit.ly/3IdowYF");
        users.add(user);

        user = new User();
        user.setId(3);
        user.setCreatedDate(currentDateTimeStamp());
        user.setDob(getDateTimeStamp("1977.04.23"));
        user.setGender(false);
        user.setName("Zhong Zina");
        user.setOccupation("Democrat & Developer");
        user.setImgUrl("https://bit.ly/3EjX7SO");
        users.add(user);

        return users;
    }

    /**
     * Set recyclerview
     */
    private void setRecyclerView(){
        // adapter
        adapter = new PetAdapter() {

            @Override
            void onItemClicked(@NonNull View view, @NonNull Pet pet) {
                Intent intent = new Intent(PetActivity.this, PetDetailActivity.class);
                intent.putExtra("PET", pet);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(PetActivity.this, view, "robot");
                // start the new activity
                startActivity(intent, options.toBundle());
            }

            @Override
            void onDismissed(@NonNull Pet pet) {
                RealmHelper.deleteWhere(Pet.class, query -> {
                    Realm realm = Realm.getDefaultInstance();
                    return realm.where(Pet.class).equalTo("id", pet.getId());
                });
            }
        };

        // recyclerView
        RecyclerView recyclerView = binding.rcvPet;
        recyclerView.setAdapter(adapter);

        // set swipe
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        simpleItemTouchHelperCallback.setEnableSwipe(SimpleItemTouchHelperCallback.Direction.LEFT, true);
        simpleItemTouchHelperCallback.setLeftDecorator("Delete", R.color.color_EB5757, R.color.color_F4F7FD);
    }

    private long currentDateTimeStamp(){
        return System.currentTimeMillis();
    }

    private long getDateTimeStamp(@NonNull String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

        try {
            Date d = formatter.parse(date);
            if(d != null){
                Timestamp timestamp = new Timestamp(d.getTime());
                return timestamp.getTime();
            }

        } catch (ParseException e) {
            return 0;
        }

        return 0;
    }
}