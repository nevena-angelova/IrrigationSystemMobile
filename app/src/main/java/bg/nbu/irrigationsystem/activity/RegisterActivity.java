package bg.nbu.irrigationsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    EditText et_username, et_password, et_email, et_first_name, et_last_name;

    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        et_email = findViewById(R.id.email);
        et_first_name = findViewById(R.id.firstName);
        et_last_name = findViewById(R.id.lastName);

        btn_register = findViewById(R.id.register);
        btn_register.setOnClickListener(v ->  register(
                et_username.getText().toString(), et_password.getText().toString(),
                et_email.getText().toString(), et_first_name.getText().toString(), et_last_name.getText().toString())
        );
    }

    private void register(String username, String password, String email, String firstName, String lastName) {

        JsonObject credentials = new JsonObject();
        credentials.addProperty("username", username);
        credentials.addProperty("password", password);
        credentials.addProperty("email", email);
        credentials.addProperty("firstName", firstName);
        credentials.addProperty("lastName", lastName);


        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.register(credentials).enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    String token = response.body().get("token").getAsString();

                    SessionManager session = new SessionManager(RegisterActivity.this);
                    session.saveToken(token);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.setAction(Intent.ACTION_VIEW);
                    startActivity(intent);

                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}