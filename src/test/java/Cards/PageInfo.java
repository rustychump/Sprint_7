package Cards;

public class PageInfo {
    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public int getLimit() {
        return limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private int page;
    private int total;
    private int limit;
}
