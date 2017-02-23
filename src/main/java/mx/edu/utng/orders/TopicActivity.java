package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.TopicAdapter;
import mx.edu.utng.orders.model.Topic;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by user on 23/02/2017.
 */

public class TopicActivity extends AppCompatActivity {
    private EditText etTopicSubject;
    private EditText etTopicDate;
    private Button btSaveTopic;
    private DBOperations operations;
    private Topic topic = new Topic();
    private RecyclerView rvTopics;
    private List<Topic> topics = new ArrayList<Topic>();
    private TopicAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        operations= DBOperations.getDBOperations(getApplicationContext());
        topic.setIdTopic("");

        initComponents();
    }
    private void initComponents(){
        rvTopics=(RecyclerView)findViewById(R.id.rv_topic_list);
        rvTopics.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvTopics.setLayoutManager(layoutManeger);
        //
        getTopics();
        adapter=new TopicAdapter(topics);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_topic:
                        operations.deleteTopic(topics.get(rvTopics.getChildPosition((View)v.getParent().getParent())).getIdTopic());
                        getTopics();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_topic:


                        Cursor c = operations.getTopicById(topics.get(
                                rvTopics.getChildPosition(
                                        (View)v.getParent().getParent())).getIdTopic());
                        if (c!=null){
                            if (c.moveToFirst()){
                                topic = new Topic(c.getString(1),c.getString(2),c.getString(3));
                            }
                            etTopicSubject.setText(topic.getTopicSubject());
                            etTopicDate.setText(topic.getTopicDate());

                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvTopics.setAdapter(adapter);

        etTopicSubject=(EditText)findViewById(R.id.et_topic_subject);
        etTopicDate=(EditText)findViewById(R.id.et_topic_date);


        btSaveTopic=(Button)findViewById(R.id.bt_save_topic);

        btSaveTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!topic.getIdTopic().equals("")){
                    topic.setTopicSubject(etTopicSubject.getText().toString());
                    topic.setTopicDate(etTopicDate.getText().toString());

                    operations.updateTopic(topic);

                }else {
                    topic = new Topic("", etTopicSubject.getText().toString(), etTopicDate.getText().toString());
                    operations.insertTopic(topic);
                }
                getTopics();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getTopics(){
        Cursor c =operations.getTopics();
        topics.clear();
        if(c!=null){
            while (c.moveToNext()){
                topics.add(new Topic(c.getString(1),c.getString(2),c.getString(3)));
            }
        }

    }

    private void clearData(){
        etTopicSubject.setText("");
        etTopicDate.setText("");
        topic=null;
        topic= new Topic();
        topic.setIdTopic("");
    }

    }



