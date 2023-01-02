package com.example.girlsquotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.girlsquotesapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> Category=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        Datalisting();

    }

    private void Datalisting() {

        Category.add( "Attitude" );
        Category.add( "Awesome" );
        Category.add( "Cool" );
        Category.add( "Friend" );
        Category.add( "Happiness" );
        Category.add( "Hurt" );
        Category.add( "Inspiritional" );
        Category.add( "Life" );
        Category.add( "Motivation" );
        Category.add( "Moving On" );
        Category.add( "Sad" );
        Category.add( "Self Love" );
        Category.add( "Single" );
        Category.add( "Success" );
        Category.add( "True" );

        Category_ListI_Interface categoryListIInterface=new Category_ListI_Interface() {
            @Override
            public void clicker(int position) {

                Intent go = new Intent( MainActivity.this, QuoteActivity.class );
                go.putExtra( "position", position );
                go.putExtra( "Categoty", Category );
                startActivity( go );


            }

            @Override
            public void galleryClicker(int position, String category) {

            }

        };
        LinearLayoutManager manager=new LinearLayoutManager( this, RecyclerView.VERTICAL,false );
        QuotesRecycleviewAdapter Category_list=new QuotesRecycleviewAdapter(this,Category,categoryListIInterface);
        binding.ryclCategory.setLayoutManager( manager );
        binding.ryclCategory.setAdapter( Category_list );
    }
}