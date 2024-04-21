package com.example.myapplicationforshit;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Xml;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplicationforshit.databinding.ActivityCreateBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Create extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button b=findViewById(R.id.craft);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "people_basics.xml";
                Resources res = getResources();
                int resId = res.getIdentifier(fileName, "xml", getPackageName());
                List<Person> people = new ArrayList<>();

                if (resId != 0) {
                    try {
                        // Open the XML file as InputStream
                        InputStream inputStream = res.openRawResource(resId);

                        // Initialize XmlPullParser
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        XmlPullParser parser = factory.newPullParser();

                        // Set input stream for the parser
                        parser.setInput(inputStream, null);

                        // Parse the XML
                        int eventType = parser.getEventType();
                        String name = null, age = null, job = null;
                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            if (eventType == XmlPullParser.START_TAG) {
                                String tagName = parser.getName();
                                if (tagName.equals("name")) {
                                    name = parser.nextText();
                                } else if (tagName.equals("age")) {
                                    age = parser.nextText();
                                } else if (tagName.equals("job")) {
                                    job = parser.nextText();
                                }
                            } else if (eventType == XmlPullParser.END_TAG && parser.getName().equals("item")) {
                                // Create a new Person object and add it to the list
                                people.add(new Person(name, age, job));
                            }
                            // Move to the next XML event
                            eventType = parser.next();
                        }
                        // Close the InputStream
                        inputStream.close();

                    } catch (XmlPullParserException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // File not found or resource ID not valid
                }
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_create);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}