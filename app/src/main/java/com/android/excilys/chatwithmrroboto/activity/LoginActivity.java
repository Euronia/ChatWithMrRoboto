package com.android.excilys.chatwithmrroboto.activity;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.excilys.chatwithmrroboto.ChatApplication;
import com.android.excilys.chatwithmrroboto.R;
import com.android.excilys.chatwithmrroboto.dao.UserDao;
import com.android.excilys.chatwithmrroboto.entity.User;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    TextView error_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        error_message = (TextView) findViewById(R.id.error_message);

    }

    private void showError ()
    {
        error_message.setVisibility(View.VISIBLE);
        error_message.setSaveEnabled(true);
    }

    private void hideError ()
    {
        error_message.setVisibility(View.GONE);
        error_message.setSaveEnabled(false);
    }

    public void connect(View view)
    {
        new ConnectionAsyncTask(this).execute();
    }


    class ConnectionAsyncTask extends AsyncTask<String,Void,Boolean> {

        ProgressDialog progressDialog = null;
        final EditText usernameEditText = (EditText) findViewById(R.id.connection_username_edit);
        final EditText passwordEditText = (EditText) findViewById(R.id.connection_password_edit);
        User user;
        private LoginActivity parent;
        boolean network;

        public ConnectionAsyncTask(LoginActivity parent)
        {
            this.parent = parent;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.progress_message));
            progressDialog.show();

            user = new User.Builder(usernameEditText.getText().toString()).password(passwordEditText.getText().toString()).build();

            network = isNetworkAvailable();
            if (!network) {
                Toast.makeText(LoginActivity.this,R.string.connection_noNetwork,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(String... params) {

            // Gets our user's data and transform them into JSon
            if (network) {
                UserDao dao = new UserDao();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String url = ((ChatApplication) getApplicationContext()).getUrl();
                return dao.connect(url, user);
            } else {
                return false;
            }
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            if (aBoolean) {
                hideError();
                Toast.makeText(LoginActivity.this,R.string.connection_connected,Toast.LENGTH_SHORT).show();
            } else {
                showError();
            }
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }



}
