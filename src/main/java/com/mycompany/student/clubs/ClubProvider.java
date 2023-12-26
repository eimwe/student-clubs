package com.mycompany.student.clubs;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
        
    public static void loadClubTable(JTable table) {
        DefaultTableModel tableModel = new DefaultTableModel();
        TableRowSorter tableSorter = new TableRowSorter(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Description");
        tableModel.addColumn("Participants");
        tableModel.addColumn("Chairperson");
        tableModel.addColumn("E-mail");
        
        try {
            CollectionReference clubs = FirestoreConnection.db.collection("Club");
            ApiFuture<QuerySnapshot> querySnap = clubs.get();
            
            for(DocumentSnapshot document: querySnap.get().getDocuments()) {
                tableModel.addRow(new Object[]{
                    document.getId(),
                    document.getString("title"),
                    document.getString("description"),
                    document.getString("participants"),
                    document.getString("chairperson"),
                    document.getString("email")
                });
            }
        } catch(InterruptedException | ExecutionException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        table.setModel(tableModel);
        table.setRowSorter(tableSorter);
    }
}
