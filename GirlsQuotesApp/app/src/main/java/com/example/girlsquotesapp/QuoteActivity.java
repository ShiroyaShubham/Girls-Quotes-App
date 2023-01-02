package com.example.girlsquotesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.girlsquotesapp.databinding.ActivityQuoteBinding;

import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {

    String TAG = "-----";
    ActivityQuoteBinding binding;
    ArrayList<String> Category2;
    ArrayList<String> Quotes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityQuoteBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        initView();
        listing();
    }

    private void initView() {
        binding.btnBackArrow.setOnClickListener(v ->{
            Intent i=new Intent(QuoteActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void listing() {

        Category2 = (ArrayList<String>) getIntent().getSerializableExtra( "Categoty" );
        int position = getIntent().getIntExtra( "position", 0 );
        binding.txtHeading.setText( Category2.get( position ) );
        QuotesModelClass quotesModelClass = new QuotesModelClass();
        Quotes2 = quotesModelClass.getList( position );

        String category=binding.txtHeading.getText().toString();
        Category_ListI_Interface categoryListIInterface=new Category_ListI_Interface() {
            @Override
            public void clicker(int position) {

            }

            @Override
            public void galleryClicker(int position,String quotes) {
                Intent i=new Intent(QuoteActivity.this,QuoteWithBackground.class);
                i.putExtra("category",category);
                i.putExtra("quotes",quotes);
                startActivity(i);
            }
        };

        SecondRecycleviewAdapter secondRecycleviewAdapter = new SecondRecycleviewAdapter( this, Quotes2,categoryListIInterface );
        binding.ryclQuotesList.setAdapter( secondRecycleviewAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        binding.ryclQuotesList.setLayoutManager( linearLayoutManager );


    }


}