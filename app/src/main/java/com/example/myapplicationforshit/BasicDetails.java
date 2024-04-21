package com.example.myapplicationforshit;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplicationforshit.databinding.ActivityBasicDetailsBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BasicDetails extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityBasicDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        XmlResourceParser parser1 = getResources().getXml(R.xml.details_people);
        String address=" ";
        String details=" ";
        List<Details> detailsList=Details.GenerateDetails();
        int number = -1;
        try {
            // Get the XML resource parser
             // Default value if reading fails
            FileInputStream fis = null;
            BufferedReader reader = null;
            try {
                // Open the file for reading from internal storage
                fis = BasicDetails.this.openFileInput("key.txt");
                reader = new BufferedReader(new InputStreamReader(fis));
                // Read the first line from the file
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    // Parse the number from the first line
                    number = Integer.parseInt(line.trim());
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        // Close the buffered reader
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        // Close the file input stream
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            // Start parsing
            int eventType = parser1.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser1.getName().equals("item")) {


                    // Parse details and address of each item
                    while (!(eventType == XmlPullParser.END_TAG && parser1.getName().equals("item"))) {
                        if (eventType == XmlPullParser.START_TAG) {
                            if (parser1.getName().equals("detail")) {
                                parser1.next();
                                details = parser1.getText();
                            } else if (parser1.getName().equals("address")) {
                                parser1.next();
                                address = parser1.getText();
                            }
                        }

                        eventType = parser1.next();
                    }

                    // Log or use the extracted information
                    Details d=new Details();
                    d.setAddress(address);
                    d.setText(details);
                    detailsList.add(d);
                }

                eventType = parser1.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = findViewById(R.id.thing);

        switch (number) {
            case 0:
                imageView.setImageResource(R.drawable.zuko);
                break;
            case 1:
                imageView.setImageResource(R.drawable.katara);
                break;
            case 2:
                imageView.setImageResource(R.drawable.toph);
                break;
            case 3:
                imageView.setImageResource(R.drawable.iroh);
                break;
            case 4:
                imageView.setImageResource(R.drawable.azula);
                break;
            case 5:
                imageView.setImageResource(R.drawable.aang);
                break;
            case 6:
                imageView.setImageResource(R.drawable.suki);
                break;
            case 7:
                imageView.setImageResource(R.drawable.mai);
                break;
            case 8:
                imageView.setImageResource(R.drawable.tailee);
                break;
            case 9:
                imageView.setImageResource(R.drawable.ozai);
                break;
            default:
                imageView.setImageResource(R.drawable.appa);
        }



        TextView textView=findViewById(R.id.detailed);
        textView.setText(detailsList.get(number).getText());
        Button b=findViewById(R.id.update);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasicDetails.this, Update_Person.class);
                BasicDetails.this.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_basic_details);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}