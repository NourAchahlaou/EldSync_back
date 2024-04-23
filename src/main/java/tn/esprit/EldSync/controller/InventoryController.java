package tn.esprit.EldSync.controller;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.esprit.EldSync.model.Item;
import tn.esprit.EldSync.model.ItemHistory;
import tn.esprit.EldSync.service.ServiceItem;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("EldSync/Inventory")
@RequiredArgsConstructor
public class InventoryController {


      private final ServiceItem serviceItem;

      @PostMapping("/addItem")
      public Item addItem(@RequestBody Item item){return serviceItem.addItem(item);}

      @GetMapping("/retrieveAllItems")
      public List<Item> retrieveAllItems(){return serviceItem.retrieveAllItems();}

      @DeleteMapping("/removeItemLog/{historyId}")
      public void removeItemLog(@PathVariable("historyId") Integer historyId){serviceItem.removeItemLog(historyId);}


      @PutMapping("/updateItem/{id}")
      public Item updateItem(@PathVariable Long id, @RequestBody Item item) {return serviceItem.updateItem(id, item);}
  
      @GetMapping("/retrieveItem/{id}")
      public  void retrieveItem(@PathVariable("id") Long id){serviceItem.retrieveItem(id);}

      @GetMapping("/getItemDetails/{id}")
      public Item getItemDetails(@PathVariable("id") Long id) {return serviceItem.getItemDetails(id);}


      @DeleteMapping("/removeItem/{id}")
      public void removeItem(@PathVariable("id") Long id){serviceItem.removeItem(id);}

      @PostMapping("/restore/{idarchive}")
      public ResponseEntity<?> restoreItem(@PathVariable Long idarchive) {
            try {
                  serviceItem.restoreItem(idarchive);
                  return ResponseEntity.ok().build();
            } catch (EntityNotFoundException e) {
                  return ResponseEntity.notFound().build();
            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
      }


/*

      @GetMapping("/Item-by-category")
      public ResponseEntity<List<Item>> filterItemsByCategory(@RequestParam String category) {List<Item> items = serviceItem.filterItemsByCategory(category);
            if (!items.isEmpty()) {return ResponseEntity.ok(items);} 
            else {return ResponseEntity.notFound().build();}}

*/
      @GetMapping("/items/history")
      public ResponseEntity<List<ItemHistory>> getAllItemHistory() {List<ItemHistory> historyLogs = serviceItem.getAllItemHistory();
            if (historyLogs.isEmpty()) {return ResponseEntity.noContent().build();}
      return ResponseEntity.ok(historyLogs);}



}
