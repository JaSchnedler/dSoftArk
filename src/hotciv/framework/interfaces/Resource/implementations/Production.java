package hotciv.framework.interfaces.Resource.implementations;

import hotciv.framework.interfaces.Resource.Resource;

/**
 * Created by Jacob on 30-11-2015.
 */
public class Production implements Resource {

    private final int productionYield;
    private final int foodYield;
    private final boolean allowsMovement;

    public Production(int foodYield, int productionYield, boolean allowsMovement) {
        this.foodYield = foodYield;
        this.productionYield = productionYield;
        this.allowsMovement = allowsMovement;
    }

    @Override
    public int getFoodYield() {
        return foodYield;
    }

    @Override
    public int getProductionYield() {
        return productionYield;
    }
}
