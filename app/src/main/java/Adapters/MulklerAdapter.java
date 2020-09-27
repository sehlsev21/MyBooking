package Adapters;

import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mybooking.R;

import java.net.URI;
import java.util.List;


import Models.Kullanicilar;
import Models.Mulkler;


public class MulklerAdapter extends RecyclerView.Adapter<MulklerAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Mulkler> mulklerListe;
    private Uri uri;


    public MulklerAdapter(Context mContext, List<Mulkler> mulklerListe) {
        this.mContext = mContext;
        this.mulklerListe = mulklerListe;

    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mulkler,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        Mulkler mulkler = mulklerListe.get(position);
        holder.textViewAdres.setText("Adres: "+mulkler.getMulk_adres());
        holder.textViewBaslik.setText(mulkler.getMulk_baslik());
        holder.textViewUcret.setText(mulkler.getMulk_ucret() + " TL");
       // let this be your byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(mulkler.getMulk_resim() , 0, mulkler.getMulk_resim() .length);
        holder.imageView.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return mulklerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewBaslik, textViewAdres , textViewUcret;
        private ImageView imageView;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewBaslik = itemView.findViewById(R.id.textViewBaslik);
            textViewAdres = itemView.findViewById(R.id.textViewAdres);
            textViewUcret = itemView.findViewById(R.id.textViewUcret);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
