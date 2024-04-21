package com.example.myapplicationforshit;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Person> people;
    private Context context;
    int pos;
    public MyAdapter(List<Person> people, Context context){
        this.people=people;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person p=people.get(position);
        TextView t1=holder.t1;
        t1.setText(p.job);
        TextView T=holder.T;
        T.setText(p.name);
        TextView t2=holder.t2;
        t2.setText(p.age);
        Button b=holder.B;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 // Default value if reading fails
                FileOutputStream fos = null;
                try {
                    // Open the file for writing in internal storage
                    fos = context.openFileOutput("key.txt", Context.MODE_PRIVATE);
                    // Write the number to the file as a string
                    String numberString = String.valueOf(holder.getLayoutPosition());
                    fos.write(numberString.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            // Close the file output stream
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent intent = new Intent(context, BasicDetails.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
