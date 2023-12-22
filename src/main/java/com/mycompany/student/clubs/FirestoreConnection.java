package com.mycompany.student.clubs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author eimwe
 */
public class FirestoreConnection {
    
    public static Firestore db;
    
    public static void connectToFirebase() {
        try {
            FileInputStream refreshToken = new FileInputStream("student-clubs.json");
            
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("Connected to the database");
        } catch (IOException e) {
            System.err.println( "Error: " + e.getMessage());
        }
    }
    
}
