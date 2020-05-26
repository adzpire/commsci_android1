package com.example.commsci_android1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.commsci_android1.model.DataGalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN_URL = "https://api.commsci.psu.ac.th/rest/auth";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public EditText textusername;
    public EditText textpassword;
    public Button buttonlogin;

    public String username;
    public String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textusername = findViewById(R.id.editTextusername);
        textpassword = findViewById(R.id.editTextpassword);
        buttonlogin = findViewById(R.id.buttonlogin);

        buttonlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        userLogin();
    }

    private void userLogin(){
        username = textusername.getText().toString().trim();
        password = textpassword.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loging In...");
        progressDialog.show();

//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                LOGIN_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            jsonObject.getString("status");
//                            Toast.makeText(getApplicationContext(),  jsonObject.getString("status"), Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        progressDialog.dismiss();
////                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
////                    }
////                }
////                protected Map<String, String> getParams throe
//        ){
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
////                map.put(KEY_USERNAME, username);
////                map.put(KEY_PASSWORD, password);
//                map.put("username", "abdul-aziz.d");
//                map.put("password", "blkak@9a");
//                return map;
//            }
//        };

        StringRequest jsonObjRequest = new StringRequest(

                Request.Method.POST,
//                getResources().getString(R.string.base_url),
                LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            jsonObject.getString("status");
                            Toast.makeText(getApplicationContext(),  jsonObject.getString("status"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        String body = null;
                        //get status code here
                        String statusCode = String.valueOf(error.networkResponse.statusCode);
                        //get response body and parse with appropriate encoding
                        if(error.networkResponse.data!=null) {
                            try {
                                body = new String(error.networkResponse.data,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }
                        //do stuff with the body...
                        Toast.makeText(getApplicationContext(),  body, Toast.LENGTH_LONG).show();
//                        if(error.networkResponse.statusCode == 401){
//                            Toast.makeText(getApplicationContext(),  "Authorization fail", Toast.LENGTH_LONG).show();
//                        }else if(error.networkResponse.statusCode == 403) {
//                            Toast.makeText(getApplicationContext(), error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
//                        }
//                        VolleyLog.d("volley", "Error: " + error.getMessage());
//                        error.printStackTrace();
//                        MyFunctions.croutonAlert(LoginActivity.this,
//                                MyFunctions.parseVolleyError(error));
//                        loading.setVisibility(View.GONE);
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", textusername.getText().toString().trim());
                params.put("password", textpassword.getText().toString().trim());
                return params;
            }

        };
//        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjRequest);
    }
}
