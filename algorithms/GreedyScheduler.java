package algorithms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import models.StandAssignment;
import models.Tenant;

public class GreedyScheduler {

    public List<StandAssignment> schedule(List<Tenant> tenants) {
        List<StandAssignment> assignments = new ArrayList<>();
        if (tenants == null || tenants.isEmpty()) {
            return assignments;
        }

        // 1. SORT tenants BY endTime ASCENDING
        List<Tenant> sortedTenants = new ArrayList<>(tenants);
        sortedTenants.sort(Comparator.comparingInt(Tenant::getEndTime));

        // 2. ADD tenants[0] TO selectedTenants
        Tenant firstTenant = sortedTenants.get(0);
        assignments.add(new StandAssignment(firstTenant, 1)); // Diplot ke Zona 1 (Zona A)
        int lastFinish = firstTenant.getEndTime();

        // 3. FOR i ← 1 TO tenants.length - 1 DO
        for (int i = 1; i < sortedTenants.size(); i++) {
            Tenant current = sortedTenants.get(i);
            
            // IF tenants[i].startTime >= lastFinish THEN
            if (current.getStartTime() >= lastFinish) {
                assignments.add(new StandAssignment(current, 1));
                lastFinish = current.getEndTime(); // lastFinish ← tenants[i].endTime
            } else {
                System.out.println(">>> [Greedy] Tenant Tereliminasi (Bentrok): " + current.getName());
            }
        }

        return assignments;
    }
}