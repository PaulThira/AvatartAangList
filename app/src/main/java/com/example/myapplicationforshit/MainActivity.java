package com.example.myapplicationforshit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    List<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvpeople=(RecyclerView) findViewById(R.id.recycler_view);
        people=Person.createPeople(10);
        MyAdapter adapter=new MyAdapter(people,MainActivity.this);
        rvpeople.setAdapter(adapter);
        rvpeople.setLayoutManager(new LinearLayoutManager(this));
        XmlResourceParser parser = getResources().getXml(R.xml.people_basics);

        try {
            parseXML(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        } finally {
            parser.close();
        }
        Button b=findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        String name = "";
        String age = "";
        String job = "";
        int i=0;
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equals("item")) {
                    name = "";
                    age = "";
                    job = "";
                } else if (parser.getName().equals("name")) {
                    parser.next(); // Move to the text inside <name> tag
                    name = parser.getText();
                } else if (parser.getName().equals("age")) {
                    parser.next(); // Move to the text inside <age> tag
                    age = parser.getText();
                } else if (parser.getName().equals("job")) {
                    parser.next(); // Move to the text inside <job> tag
                    job = parser.getText();
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equals("item")) {
                    // Process the data for each item
                    people.get(i).name=name;
                    people.get(i).age=age;
                    people.get(i).job=job;
                    i++;
                }
            }
            eventType = parser.next(); // Move to the next tag
        }
    }
}