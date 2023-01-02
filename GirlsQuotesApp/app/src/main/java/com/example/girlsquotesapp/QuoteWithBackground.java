package com.example.girlsquotesapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girlsquotesapp.databinding.ActivityQuoteWithBackgroundBinding;

import java.util.ArrayList;

public class QuoteWithBackground extends AppCompatActivity {

    ActivityQuoteWithBackgroundBinding binding;
    String category,quotes;
    ArrayList<String> quotesList2;
    int galleryCode=100;
    int i=0;
    int image[]={R.drawable.quotes_back4,R.drawable.quotes_background1,R.drawable.quotes_back1,R.drawable.quotess_back2,R.drawable.quotes_back3,R.drawable.quote_background,R.drawable.quotes_back5,R.drawable.quotes6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding=ActivityQuoteWithBackgroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listing();
        onClickButton();
        quotesHorizontal();
    }

    private void quotesHorizontal() {
       ThirdRecycleviewAdapter adapter=new ThirdRecycleviewAdapter(this,quotesList2);
       binding.rcvHorizontalQuotes.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.rcvHorizontalQuotes.setLayoutManager(manager);
    }

    private void onClickButton() {
        binding.imgBack.setOnClickListener(view -> {
            Intent i=new Intent(QuoteWithBackground.this,QuoteActivity.class);
            startActivity(i);
            finish();
        });
        binding.imgCopy.setOnClickListener(v -> {

            ArrayList<String> copyQuote = quotesList2;
            ClipboardManager clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Text Copy", (CharSequence) copyQuote);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        });
        binding.imgLikeEmpty.setOnClickListener(v ->{
            binding.imgLikeEmpty.setVisibility(View.GONE);
            binding.imgLikeFill.setVisibility(View.VISIBLE);
            Toast.makeText(QuoteWithBackground.this, "Added to My Favourite", Toast.LENGTH_SHORT).show();
        });
        binding.imgLikeFill.setOnClickListener(v ->{
            binding.imgLikeEmpty.setVisibility(View.VISIBLE);
            binding.imgLikeFill.setVisibility(View.GONE);
        });
        binding.imgShare.setOnClickListener(v->{
            Dialog dialog=new Dialog(this);
            dialog.setContentView(R.layout.share_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView txtText=dialog.findViewById(R.id.txtText);
            TextView txtImage=dialog.findViewById(R.id.txtImage);

            txtText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody =quotes;
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
            });
            dialog.show();
        });
        binding.imgPencil.setOnClickListener(view -> {
            Dialog dialog=new Dialog(QuoteWithBackground.this);
            dialog.setContentView(R.layout.set_background_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView txtGallery=dialog.findViewById(R.id.txtGallery);
            TextView txtBackgrounds=dialog.findViewById(R.id.txtBackgrounds);

            txtGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(galleryIntent,"Select Picture"),galleryCode);
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
        binding.imageBtnView.setOnClickListener(v->{
            binding.imageBtnView.setImageResource(image[i]);
            i++;
            if(i==8){
                i=0;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==galleryCode){
            if (resultCode==-1 && data!=null ){
                Uri uri=data.getData();
                binding.imageBtnView.setImageURI(uri);
            }
        }
        else{
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void listing() {
       if (getIntent()!=null){
           category=getIntent().getStringExtra("category");
            quotes=getIntent().getStringExtra("quotes");
       }
       binding.txtHeading2.setText(category);
        int position = getIntent().getIntExtra( "position", 0 );
        QuotesModelClass quotesModelClass = new QuotesModelClass();
        quotesList2 = quotesModelClass.getList( position );


    }

}