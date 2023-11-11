package com.example.tarea22;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityConsulta extends AppCompatActivity {

    private ListView listView;
    private RequestQueue requestQueue;
    private ArrayAdapter<String> postadapter;
    private String url = "https://jsonplaceholder.typicode.com/posts";
    private Button btnSalvar;
    private String url2;
    private EditText post;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listView = (ListView) findViewById(R.id.listpost);
        postadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(postadapter);
        post = (EditText) findViewById(R.id.txtpost);
        btnSalvar = findViewById(R.id.btnsalvar);


        requestQueue = Volley.newRequestQueue(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            try {
                                String Post = post.getText().toString();
                                if(Post.isEmpty()){
                                    Toast.makeText(ActivityConsulta.this, "Ingrese el Post", Toast.LENGTH_SHORT).show();
                                }else{

                                JSONObject jsonObject = response.getJSONObject(Integer.parseInt(Post));
                                String mensaje = jsonObject.getString("title");
                                postadapter.add(mensaje);}

                            }catch (Exception ex){
                                ex.printStackTrace();
                            }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonArrayRequest);
            }
        });

    }
}