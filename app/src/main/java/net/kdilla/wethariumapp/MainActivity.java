package net.kdilla.wethariumapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.PopupMenu;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    List<String> elements;
    ListView listView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            elements.add("Element " + i);
        }

        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, elements);
        listView.setAdapter(adapter);
        //        registerForContextMenu(listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                List files = getSelectedFiles();
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                switch (menuItem.getItemId()) {
                    case R.id.menu_edit:
                        editElement(files);
                        return true;
                    case R.id.menu_delete:
                        deleteElement(files);
                        return true;
                    default:
                        actionMode.finish();
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }


    private List<Integer> getSelectedFiles() {
        List<Integer> selectedFiles = new ArrayList<Integer>();
        // AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();

        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            if (sparseBooleanArray.valueAt(i)) {
                //  Integer selectedItem = (Integer) listView.getItemAtPosition(sparseBooleanArray.keyAt(i));
                //   selectedFiles.add("#" + Integer.toHexString(selectedItem).replaceFirst("ff", ""));
                selectedFiles.add(sparseBooleanArray.keyAt(i));
            }
        }
        return selectedFiles;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.menu_edit:
//                editElement(info.position);
//                return true;
//            case R.id.menu_delete:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.mymenu);
        popupMenu.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
//                editElement(info.position);
                return true;
            case R.id.menu_delete:
                //deleteElement();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void menuAdd(MenuItem item) {
        int a = 1 + (int) (Math.random() * 100);
        int b = 1 + (int) (Math.random() * 100);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(),
                "add menu element.\n a= " + a + ", b= " + b + "\na+b=" + (a + b),
                duration);
        toast.show();
    }

    public void menuClear(MenuItem item) {
        int a = 1 + (int) (Math.random() * 100);
        int b = 1 + (int) (Math.random() * 100);

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(),
                "clear menu element.\n a= " + a + ", b= " + b + "\na+b-a/2=" + (a + b - a / 2),
                duration);
        toast.show();
    }

    public void editElement(List<Integer> files) {
//    private void editElement(int id) {
        String selected = "(";
        for (int i = 0; i < files.size(); i++) {
            selected += files.get(i);
            if (i < files.size() - 1) selected += ", ";
        }
        selected += "), ";
        int a = 1 + (int) (Math.random() * 100);
        int b = 1 + (int) (Math.random() * 100);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(),
                "edit element" + selected + "\na= " + a + ", b= " + b + "\na*b=" + (a * b),
                duration);
        toast.show();
    }

    public void deleteElement(List<Integer> files) {
        String selected = "(";
        for (int i = 0; i < files.size(); i++) {
            selected += files.get(i);
            if (i < files.size() - 1) selected += ", ";
        }
        selected += "), ";

        int a = 1 + (int) (Math.random() * 100);
        int b = 1 + (int) (Math.random() * 100);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(),
                "delete element\n" + selected + "a= " + a + ", b= " + b + "\na-b=" + (a - b),
                duration);
        toast.show();
    }


}

