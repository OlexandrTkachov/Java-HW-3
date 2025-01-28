package itstep.learning;

import java.util.Map;

public class Headwear extends Product {
    private String headSize;

    public Headwear(String name, double price, Map<String, String> fields) {
        super(name, price, null);
        this.headSize = fields.get("headSize");
    }

    public String getHeadSize() {
        return headSize;
    }

    @Override
    public String getProductInfo() {
        StringBuilder answer = new StringBuilder();
        answer.append(getName());
        answer.append("\n\t price: ").append(getPrice());
        answer.append("\n\t head size: ").append(getHeadSize());
        return answer.toString();
    }
}
