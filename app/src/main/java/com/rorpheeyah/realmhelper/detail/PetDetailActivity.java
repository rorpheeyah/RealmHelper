package com.rorpheeyah.realmhelper.detail;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.rorpheeyah.realmhelper.databinding.ActivityPetDetailBinding;
import com.rorpheeyah.realmhelper.helper.RealmHelper;
import com.rorpheeyah.realmhelper.pet.Pet;
import com.rorpheeyah.realmhelper.pet.User;

import io.realm.Realm;

public class PetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PetDetailVM petDetailVM = new PetDetailVM();

        ActivityPetDetailBinding binding = ActivityPetDetailBinding.inflate(LayoutInflater.from(this));
        binding.setViewmodel(petDetailVM);

        Pet pet = getIntent().getParcelableExtra("PET");
        if(pet != null){

            Realm realm = Realm.getDefaultInstance();
            User user = RealmHelper.findFirst(User.class, query -> realm.where(User.class).equalTo("id", pet.getOwnerId()));

            if(user != null)
                binding.setOwner(user);

            binding.setPet(pet);
            binding.executePendingBindings();
        }

        setContentView(binding.getRoot());

        petDetailVM.getAction().observe(this, action ->{
            if("BACK".equalsIgnoreCase(action)){
                onBackPressed();
            }
        });
    }
}