import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class ProductEntity implements Serializable {

    private String productId;
    private String productName;
    private String categoryName;
    private List<MerchantEntity> merchantList;
    private String image;
    private Long orderCount;
    private String description;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    // 🔥 New Fields
    private Double rating;
    private Integer totalReviews;

    public ProductEntity(String productId,
                         String productName,
                         String categoryName,
                         Long orderCount,
                         String attribute1,
                         String attribute2,
                         String attribute3,
                         String attribute4,
                         String attribute5,
                         List<MerchantEntity> merchantList,
                         String description,
                         String image,
                         Double rating,
                         Integer totalReviews) {

        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.merchantList = merchantList;
        this.image = image;
        this.orderCount = orderCount;
        this.description = description;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.rating = rating;
        this.totalReviews = totalReviews;
    }

    // 🔥 BUSINESS LOGIC METHODS

    public boolean isPopularProduct() {
        return orderCount != null && orderCount > 100;
    }

    public boolean isHighlyRated() {
        return rating != null && rating >= 4.5;
    }

    public MerchantEntity getLowestPriceMerchant() {
        if (merchantList == null || merchantList.isEmpty()) {
            return null;
        }
        return merchantList.stream()
                .min(Comparator.comparing(MerchantEntity::getPrice))
                .orElse(null);
    }

    public double getLowestPrice() {
        MerchantEntity merchant = getLowestPriceMerchant();
        return merchant != null ? merchant.getPrice() : 0.0;
    }

    public String getDisplayName() {
        return productName + " (" + categoryName + ")";
    }

    public boolean hasAttributes() {
        return attribute1 != null ||
               attribute2 != null ||
               attribute3 != null ||
               attribute4 != null ||
               attribute5 != null;
    }

    // Standard getters & setters below...
}
