package cards;

import java.util.List;

public class OrdersListCard {
    List<Orders> orders;
    PageInfo pageInfo;
    List<AvailableStations> availableStations;

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }
}
