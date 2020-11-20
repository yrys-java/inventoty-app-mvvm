package com.example.myapplication.ViewModel.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication.Model.Data.InventoryDao;
import com.example.myapplication.Model.Data.InventoryDatabase;
import com.example.myapplication.Model.Pojo.Inventory;

import java.util.List;

public class InventoryRepository {

    private InventoryDao inventoryDao;
    private LiveData<List<Inventory>> allInventory;

    public InventoryRepository(Application application) {
        InventoryDatabase inventoryDatabase = InventoryDatabase.getInstance(application);
        inventoryDao = inventoryDatabase.inventoryDao();
        allInventory = inventoryDao.getAllInventory();
    }

    public void insert(Inventory inventory) {
        new InsertInventoryAsyncTask(inventoryDao).execute(inventory);
    }

    public void update(Inventory inventory) {
        new UpdateInventoryAsyncTask(inventoryDao).execute(inventory);
    }

    public void delete(Inventory inventory) {
        new DeleteInventoryAsyncTask(inventoryDao).execute(inventory);
    }

    public void deleteAll() {
        new DeleteAllInventoryAsyncTask(inventoryDao).execute();
    }

    public LiveData<List<Inventory>> getAllInventory() {
        return allInventory;
    }

    private static class InsertInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {

        private InventoryDao inventoryDao;

        public InsertInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.insert(inventories[0]);
            return null;
        }
    }

    private static class UpdateInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {
        private InventoryDao inventoryDao;

        public UpdateInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }


        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.update(inventories[0]);
            return null;
        }
    }

    private static class DeleteInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {

        private InventoryDao inventoryDao;

        public DeleteInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.delete(inventories[0]);
            return null;
        }
    }

    private static class DeleteAllInventoryAsyncTask extends AsyncTask<Void, Void, Void> {

        private InventoryDao inventoryDao;

        public DeleteAllInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            inventoryDao.deleteAll();
            return null;
        }
    }
}
