package com.mycompany.student.clubs;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;

/**
 *
 * @author eimwe
 */
public class ClubProvider {
    CollectionReference reference;
    
    static Firestore db;
    
    public static boolean saveClub
        (
            String collection ,
            String document,
            Map<String, Object> data
        ) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = 
                    db.collection(collection)
                      .document(document);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Club saved successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return false;
    }
        
    public static boolean updateClub
        (
            String collection ,
            String document,
            Map<String, Object> data
        ) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = 
                    db.collection(collection)
                      .document(document);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Club updated successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return false;
    }
        
    public static boolean deleteClub
        (
            String collection ,
            String document
        ) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = 
                    db.collection(collection)
                      .document(document);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Club deleted successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return false;
    }
}
