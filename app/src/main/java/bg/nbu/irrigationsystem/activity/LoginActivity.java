package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import bg.nbu.irrigationsystem.ApiService;
import bg.nbu.irrigationsystem.SessionManager;
import bg.nbu.irrigationsystem.ApiClient;
import bg.nbu.irrigationsystem.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tv_register;
    Button btn_login;
    EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_register = findViewById(R.id.register);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);

        btn_login = findViewById(R.id.login);
        btn_login.setOnClickListener(v -> login(et_username.getText().toString(), et_password.getText().toString()));
    }

    // Login to the API and save the token to the session
    private void login(String username, String password) {

        SessionManager session = new SessionManager(LoginActivity.this);
        session.clearToken();

        JsonObject credentials = new JsonObject();
        credentials.addProperty("username", username);
        credentials.addProperty("password", password);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.login(credentials).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    String token = response.body().get("token").getAsString();

                    session.saveToken(token);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setAction(Intent.ACTION_VIEW);

                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}