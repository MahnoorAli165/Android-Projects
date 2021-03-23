package com.example.labsessional2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity  extends FragmentActivity implements Fragment1.OnFragmentInteractionListener, FragmentTwo.OnFragmentInteractionListener, View.OnClickListener {

    private Fragment1 Fragment1;
    private FragmentTwo FragmentTwo;
    private EditText editTextName;
    private EditText editTextReg;
    private EditText editTextPhone;
    private Button save,viewrec;
    private Student student;
    private ListView list;
    private StudentAdapter adapter;
    private ArrayList<Student> students;
    private String studentRaw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.frag1);
        editTextName = (EditText) Fragment1.getView().findViewById(R.id.editTextName);
        editTextReg = (EditText) Fragment1.getView().findViewById(R.id.editTextReg);
        editTextPhone = (EditText) Fragment1.getView().findViewById(R.id.editTextPhone);
        FragmentTwo = (FragmentTwo) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        viewrec = (Button) FragmentTwo.getView().findViewById(R.id.viewRecord);
        viewrec.setOnClickListener(this);
        list = (ListView) FragmentTwo.getView().findViewById(R.id.listViewStudents);
        students = new ArrayList<>();




        Intent intent = getIntent();
        student = new Student(intent.getStringExtra("name"),intent.getStringExtra("reg_no") ,intent.getIntExtra("phone",0));


        save = (Button) Fragment1.getView().findViewById(R.id.buttonSave);


        save.setOnClickListener(this);
    }
            @Override
            public void onClick(View view) {
                if (view.getId() == save.getId()) {
                    if (!editTextName.getText().toString().equals("") || !editTextPhone.getText().toString().equals("") || !editTextReg.getText().toString().equals("")) {
                        student.setName(editTextName.getText()+"");
                        student.setReg_no(editTextReg.getText()+"");
                        student.setPhone(Integer.parseInt(editTextPhone.getText()+""));


                        new AsyncTask<Void, Void, Void>() {

                            String Complete_line;

                            @Override
                            protected Void doInBackground(Void... voids) {
                                try {
                                    Log.e("CHECKING", "Async TasK");
                                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(student.getName(), "UTF-8") +
                                            "&" + URLEncoder.encode("reg_no", "UTF-8") + "=" + URLEncoder.encode(student.getReg_no(), "UTF-8") +
                                            "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(student.getPhone() + "", "UTF-8");
                                    Log.e("CHECKING", data);
                                    URL url = new URL("https://mahnooralicheema.000webhostapp.com/student.php");
                                    Log.e("CHECKING", "Connection");
                                    URLConnection con = (URLConnection) url.openConnection();
                                    con.setDoOutput(true);
                                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                                    wr.write(data);
                                    wr.flush();

                                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                    String line = "";
                                    Complete_line = "";
// Read Server Response
                                    while ((line = reader.readLine()) != null) {
                                        Complete_line = Complete_line + line;
                                        break;
                                    }
                                    Complete_line.trim();
                                    Log.e("CHECKING", Complete_line);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e("CHECKING", e.getMessage());
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                if (":".equals(Complete_line.charAt(Complete_line.length() - 1) + "")) {
                                    Toast.makeText(getApplicationContext(), "Record save successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "There was an error while saving record", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }.execute();

                    } else {
                        Toast.makeText(this, "Please enter name and phone.", Toast.LENGTH_SHORT).show();
                    }
                }
                if(view.getId()==viewrec.getId())
                {
                    new GetStudentsTask().execute();

                }


            }






    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public class GetStudentsTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            students = new ArrayList<>();
            Toast.makeText(MainActivity.this, "Fetching Records.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.e("CHECKING : ", "Entered getProducts()");
                URL url = new URL("https://mahnooralicheema.000webhostapp.com/getStudent.php");
                URLConnection con = (URLConnection) url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                studentRaw = "";
                while((line = reader.readLine()) != null) {
                    studentRaw = studentRaw + line;
                }

                String studentsRaw_[] = studentRaw.split("[$]");
                for(String s : studentsRaw_) {
                    String s_[] = s.split("[|]");
                    if(s_.length > 1) {
                        students.add(new Student(s));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            updateListView();
        }
    }

    public void updateListView() {
        adapter = new StudentAdapter(this, students);
        list.setAdapter(adapter);
    }

}











