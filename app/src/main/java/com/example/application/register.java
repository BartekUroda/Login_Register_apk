package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText etEmail, etName, etUsername, etPassword;
    Button bRegister;
    FirebaseAuth fAuth;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //tworzymy zmienne o danej nazwie o danym typie i przypisujemy do szukania
        //find szuka danego wcześniej zadeklarowanego typu w danym layout
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final TextView tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);

        fAuth = FirebaseAuth.getInstance();

        //sprawdzamy czy użytkownik nie jest już zalogowany
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),logout.class));
            finish();
        }
        //to będzie się działo po kliknięciu przycisku
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //deklarujemy skąd weżmiemiey dane z jakiego et
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                //pierwsza pętla jesli pole emaila jest puste to wyśwwietli się wiadomość
                if(TextUtils.isEmpty(email)){
                    etEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    etPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    etPassword.setError("Password must be longer than 6 characters");
                    return;
                }
                //rejestrujemy użytkownika w bazie danych, wybieramy metode email&password
                // onCompleteListnere mówi czy rejestracaj powiodła się
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //jesli wszystko git to przenosi nas do klasy logout
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),logout.class));
                         // w przypadku nie powodzenia pojawia sie napis error
                        }else{
                            Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
            }

        });
        //po kliknieciu w tv przenosi użutkowinka do klasy logowania
        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });
    }
}