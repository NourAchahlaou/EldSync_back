package tn.esprit.EldSync.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.esprit.EldSync.model.Item;
import tn.esprit.EldSync.model.ItemArchive;
import tn.esprit.EldSync.model.ItemHistory;
import tn.esprit.EldSync.repositoy.ImpItemArchiveHistory;
import tn.esprit.EldSync.repositoy.ImpItemHistoryRepository;
import tn.esprit.EldSync.repositoy.ImpItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Service

@RequiredArgsConstructor
public class ServiceItem {

      
    private final ImpItemRepository impItemRepository;
    private final ImpItemHistoryRepository impItemHistoryRepository;
    private final ImpItemArchiveHistory impItemArchiveHistory;


    public List <Item> retrieveAllItems(){return impItemRepository.findAll();}
    public List <Item> retrieveAllItemsForChart(){return impItemRepository.findAll();}



    public Item addItem (Item item)
    {
    Item savedItem = impItemRepository.save(item);
    recordHistory(savedItem.getId(), "CREATE", "Item Created Successfully");
    return savedItem;
    };
    
    public  void retrieveItem(Long id){ impItemRepository.findById(id);}
    
    public void removeItem(Long id)
    {
        Item item = impItemRepository.findById(id).orElseThrow(/* exception */);
        ItemArchive archivedItem = new ItemArchive();
        archivedItem.setIdarchive(item.getId());
        archivedItem.setName(item.getName());
        archivedItem.setDescription(item.getDescription());
        archivedItem.setQuantity(item.getQuantity());
        archivedItem.setUnitPrice(item.getUnitPrice());
        archivedItem.setExpiryDate(item.getExpiryDate());
        archivedItem.setCategory(item.getCategory());
        impItemArchiveHistory.save(archivedItem);
    impItemRepository.deleteById(id);
    recordHistory(id, "DELETE", "Item Deleted Successfully");
    }

    public void removeItemLog(Integer historyId){impItemHistoryRepository.deleteById(historyId);}


    @Transactional
    public void restoreItem(Long idarchive) throws EntityNotFoundException {
        ItemArchive archivedItem = impItemArchiveHistory.findByIdarchive(idarchive)
                .orElseThrow(() -> new EntityNotFoundException("Archived item not found."));

        Item item = new Item();
        item.setId(archivedItem.getIdarchive());
        item.setName(archivedItem.getName());
        item.setDescription(archivedItem.getDescription());
        item.setQuantity(archivedItem.getQuantity());
        item.setUnitPrice(archivedItem.getUnitPrice());
        item.setExpiryDate(archivedItem.getExpiryDate());
        item.setCategory(archivedItem.getCategory());

        impItemRepository.save(item);
        impItemArchiveHistory.deleteByIdarchive(archivedItem.getIdarchive());

        ItemHistory restorationHistory = new ItemHistory();
        restorationHistory.setAction("RESTORED");
        restorationHistory.setTimestamp(LocalDateTime.now());
        restorationHistory.setDetails("Item Restored From Archive");
        restorationHistory.setItemId(archivedItem.getIdarchive());

        impItemHistoryRepository.save(restorationHistory);
    }

    public Item updateItem( Long id,Item item) {
        return impItemRepository.findById(id).map(existingItem -> {
            existingItem.setId(item.getId());
            existingItem.setName(item.getName());
            existingItem.setDescription(item.getDescription());
            existingItem.setQuantity(item.getQuantity());
            existingItem.setUnitPrice(item.getUnitPrice());
            existingItem.setExpiryDate(item.getExpiryDate());
            existingItem.setCategory(item.getCategory());
            Item updatedItem = impItemRepository.save(existingItem);
            recordHistory(updatedItem.getId(), "UPDATE", "Updated Item Details.");
            return updatedItem;
        }).orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }
    
    public Item getItemDetails(Long id) {return impItemRepository.findById(id).orElse(null);}
    /*
    public List<Item> filterItemsByCategory(String category) {return impItemRepository.findByCategory(category);}
*/
    public void recordHistory(Long itemId, String action, String details) {
        ItemHistory history = new ItemHistory();
        history.setItemId(itemId);
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());
        history.setDetails(details);
        impItemHistoryRepository.save(history);
    }


    public Page<Item> findPaginatedItems(int page, int size) {
        return impItemRepository.findAll(PageRequest.of(page, size));
    }
    public List<ItemHistory> getAllItemHistory() {return impItemHistoryRepository.findAll();}


}
