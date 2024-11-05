package components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import components.exceptions.ResourceNotFoundException;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    public Checklist createChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    public Checklist getChecklistById(Long id) {
        return checklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist not found with id " + id));
    }

    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    public Checklist updateChecklist(Long id, Checklist checklistDetails) {
        Checklist checklist = getChecklistById(id);
        checklist.setName(checklistDetails.getName());
        checklist.setCategory(checklistDetails.getCategory());
        return checklistRepository.save(checklist);
    }

    public void deleteChecklist(Long id) {
        checklistRepository.deleteById(id);
    }
}
