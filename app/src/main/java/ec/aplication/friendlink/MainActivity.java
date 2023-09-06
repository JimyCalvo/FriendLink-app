package ec.aplication.friendlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import android.media.MediaPlayer;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private Uri videoUri;
    private Button loginButton,registerButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            getAdminFire(currentUser.getUid());
        }

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);




        videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fondo);
        videoView.setVideoURI(videoUri);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });

        // Text Color
        TextView textView =findViewById(R.id.appNameTextView);
        String textoCompleto = textView.getText().toString();
        SpannableString spannableString = new SpannableString(textoCompleto);
        int color1 = getResources().getColor(R.color.white);
        int color2 = getResources().getColor(R.color.color_rojo);

        // Aplica el color a una parte del texto (por ejemplo, los primeros 5 caracteres)
        spannableString.setSpan(new ForegroundColorSpan(color1), 0, 5, 0);

        // Aplica otro color a otra parte del texto (por ejemplo, desde el carácter 10 hasta el final)
        spannableString.setSpan(new ForegroundColorSpan(color2), 6, textoCompleto.length(), 0);

        // Establece la SpannableString en el TextView
        textView.setText(spannableString);




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });


    }

    private void getAdminFire(String uid) {
        DocumentReference df = firebaseFirestore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Tag", "onSuccess: " + documentSnapshot.getData());
                if (documentSnapshot.getBoolean("admin") == false) {
                    startActivity(new Intent(MainActivity.this, IndexActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, AdminActivity.class));
                    finish();
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

}